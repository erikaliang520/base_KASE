package view;

import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.related_words.RelatedController;
import interface_adapter.related_words.RelatedState;
import interface_adapter.related_words.RelatedViewModel;
import interface_adapter.textspeech.TextSpeechController;
import interface_adapter.textspeech.TextSpeechState;
import interface_adapter.textspeech.TextSpeechViewModel;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslateState;
import interface_adapter.translate.TranslateViewModel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class TranslateView extends JPanel implements ActionListener, PropertyChangeListener, DocumentListener {
    public final String viewName = "translate";
    private final TranslateViewModel translateViewModel;
    final JTextField wordInputField = new JTextField(15);
    final JButton history;
    final JButton relatedWords;
    final JButton listen;
    private final TranslateController translateController;
    private final RelatedViewModel relatedViewModel;
    private final RelatedController relatedController;
    private final TextSpeechViewModel textSpeechViewModel;
    private final TextSpeechController textSpeechController;

    private final HistoryViewModel historyViewModel;

    private final HistoryController historyController;

    private JPanel relatedPanel = new JPanel(new FlowLayout());


    public TranslateView(TranslateViewModel translateViewModel, TranslateController translateController,
                         RelatedViewModel relatedViewModel, RelatedController relatedController,
                         TextSpeechViewModel textSpeechViewModel, TextSpeechController textSpeechController,
                         HistoryViewModel historyViewModel, HistoryController historyController) {
        this.translateViewModel = translateViewModel;
        this.translateController = translateController;
        this.relatedViewModel = relatedViewModel;
        this.relatedController = relatedController;
        this.textSpeechViewModel = textSpeechViewModel;
        this.textSpeechController = textSpeechController;
        this.historyViewModel = historyViewModel;
        this.historyController = historyController;

        translateViewModel.addPropertyChangeListener(this);
        relatedViewModel.addPropertyChangeListener(this);
        historyViewModel.addPropertyChangeListener(this);
        textSpeechViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Translate");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel wordInfo = new LabelTextPanel(
                new JLabel("Enter word"), wordInputField);

        // only need a display panel for translation...
        JPanel displayInfo = new JPanel();
        JLabel displayLabel = new JLabel("Translation: ");
        displayInfo.add(displayLabel);



        JPanel buttons = new JPanel();
        relatedWords = new JButton(TranslateViewModel.GENERATE_RELATED_WORDS_BUTTON_LABEL);
        buttons.add(relatedWords);
        listen = new JButton(TranslateViewModel.AUDIO_BUTTON_LABEL);
        buttons.add(listen);
        history = new JButton(TranslateViewModel.HISTORY_BUTTON_LABEL);
        buttons.add(history);

        // adding document listeners for the wordInputField for real time updates
        wordInputField.getDocument().addDocumentListener(new DocumentListener() {
            TranslateState currentState = translateViewModel.getState();
            private boolean saveToHistory = false;
            private Timer timer;
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    handleTextChange(currentState.getOriginalText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            private void handleTextChange(String changedText) throws IOException {

                // Cancel existing timer, if any
                if (timer != null) {
                    timer.stop();
                }

                String filteredText = changedText.replaceAll("[^a-zA-Z]", "");

                saveToHistory = false; // Reset the flag on each text change
                translateController.execute(filteredText, saveToHistory);
                // Schedule a delayed check for inactivity after 5 seconds
                timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!saveToHistory) {
                            // No activity in the last 5 seconds, set the flag to true
                            try {
                                translateController.execute(filteredText, true);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
                timer.setRepeats(false);
                timer.start();

                displayLabel.setText("Translation: " + translateViewModel.getState().getTranslatedText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    handleTextChange(currentState.getOriginalText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    handleTextChange(currentState.getOriginalText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        // adding key listener for wordInputField to update the state if key is typed
        // not sure if this key listener is necessary, or if should just directly
        // access the wordInputField in the document listener above
        wordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                updateState(e);
            }

            private void updateState(KeyEvent e) {
                TranslateState currentState = translateViewModel.getState();
                currentState.setOriginalText(wordInputField.getText() + e.getKeyChar());
                translateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    handleBackspace();
                } else {
                    updateState(e);
                }
            }

            private void handleBackspace() {
                TranslateState currentState = translateViewModel.getState();
                String currentText = wordInputField.getText();
                if (!currentText.isEmpty()) {
                    currentState.setOriginalText(currentText.substring(0, currentText.length() - 1));
                    translateViewModel.setState(currentState);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        relatedWords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(relatedWords)) {
                    // RelatedState currentState = relatedViewModel.getState();
                    TranslateState currentState = translateViewModel.getState();

                    relatedController.execute(currentState.getOriginalText(),
                            translateViewModel.DISPLAY_ORIGINAL_LABEL);


                }
            }
        });

        listen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(listen)) {
                    //TextSpeechState currentState = textSpeechViewModel.getState();
                    TranslateState currentState = translateViewModel.getState();

                    try {
                        textSpeechController.execute(currentState.getOriginalText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(history)) {
                    HistoryState currentState = historyViewModel.getState();

                    historyController.execute();
                }
            }
        });



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel infoPanel = new JPanel(new FlowLayout());

        // Add components to the infoPanel (on the same line)
        infoPanel.add(wordInfo);
        infoPanel.add(displayInfo);

        this.add(title);
        this.add(infoPanel);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("translate")) {
            TranslateState state = (TranslateState) evt.getNewValue();
            setFields(state);
        } if (evt.getPropertyName().equals("related")) {
            RelatedState state = (RelatedState) evt.getNewValue();
            setFields(state);
        } if (evt.getPropertyName().equals("speech")) {
            TextSpeechState state = (TextSpeechState) evt.getNewValue();
            playAudio(state.getSpokenText());
        }
    }

    private void playAudio(String filePath) {
        System.out.println(filePath);
        new JFXPanel(); // Initialize JavaFX toolkit

        Platform.runLater(() -> {
            Media media = new Media(new File(filePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        });

//        SwingUtilities.invokeLater(() -> {
//            try {
//                FileInputStream fileInputStream = new FileInputStream(filePath);
//                Bitstream bitstream = new Bitstream(fileInputStream);
//
//
//                int frames = bitstream.readFrame().max_number_of_frames(fileInputStream);
//                int durationInSeconds = frames / bitstream.readFrame().max_number_of_frames(fileInputStream);
//
//                JFrame frame = new JFrame("MP3 Player");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setSize(300, 100);
//                frame.setLocationRelativeTo(null);
//
//                JButton playButton = new JButton("Play");
//                JButton stopButton = new JButton("Stop");
//
//                playButton.addActionListener(e -> play(filePath, 2)); // duration in seconds at most is 2
//                stopButton.addActionListener(e -> stop());
//
//                JPanel panel = new JPanel();
//                panel.add(playButton);
//                panel.add(stopButton);
//
//                frame.getContentPane().add(panel);
//                frame.setVisible(true);
//            } catch (IOException | JavaLayerException ex) {
//                ex.printStackTrace();
//            }
//        });
    }
//
//    private static void play(String filePath, int durationInSeconds) {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                FileInputStream fileInputStream = new FileInputStream(filePath);
//                AdvancedPlayer player = new AdvancedPlayer(fileInputStream);
//
//                player.setPlayBackListener(new PlaybackListener() {
//                    @Override
//                    public void playbackFinished(PlaybackEvent evt) {
//                        // Stop playback after the specified duration
//                        if (player.getTotalFrames() > durationInSeconds) {
//                            stop();
//                        }
//                    }
//                });
//
//                new Thread(() -> {
//                    try {
//                        player.play();
//                    } catch (JavaLayerException e) {
//                        e.printStackTrace();
//                    }
//                }).start();
//            } catch (IOException | JavaLayerException ex) {
//                ex.printStackTrace();
//            }
//        });
//    }
//
//    private static void stop() {
//        System.exit(0);
//    }

    private void setFields(TranslateState state) {
        //wordInputField.setText(state.getOriginalText());
    }

    private void setFields(RelatedState state) {
        relatedPanel.removeAll();
        relatedPanel.revalidate();
        relatedPanel.repaint();

        if (!state.getGeneratedWordsError().equals("")){
            JLabel error = new JLabel(state.getGeneratedWordsError());
            relatedPanel.add(error);
        } else {
            for (String word : state.getRelatedWordsGenerated()) {
                JLabel relatedWord = new JLabel(word);
                relatedPanel.add(relatedWord);
            }
        }
        this.add(relatedPanel);

    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
