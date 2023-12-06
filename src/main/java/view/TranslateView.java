package view;

import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslateState;
import interface_adapter.translate.TranslateViewModel;

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
import java.io.IOException;

public class TranslateView extends JPanel implements ActionListener, PropertyChangeListener, DocumentListener {


    public final String viewName = "translate";
    private final TranslateViewModel translateViewModel;

    final JTextField wordInputField = new JTextField(15);

    // final JButton translate;
    private final TranslateController translateController;

    public TranslateView(TranslateViewModel translateViewModel,
                         TranslateController translateController) {
        this.translateViewModel = translateViewModel;

        this.translateController = translateController;

        this.translateViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Translate");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel wordInfo = new LabelTextPanel(
                new JLabel("Enter word"), wordInputField);

        // only need a display panel for translation...
        JPanel displayInfo = new JPanel();
        JLabel displayLabel = new JLabel("Display Text: ");
        displayInfo.add(displayLabel);


        JPanel buttons = new JPanel();
        // translate = new JButton(translateViewModel.TRANSLATE_BUTTON_LABEL);
        // buttons.add(translate);

        // adding document listeners for the wordInputField for real time updates
        wordInputField.getDocument().addDocumentListener(new DocumentListener() {
            TranslateState currentState = translateViewModel.getState();
            private boolean saveToHistory = false;

            private Timer timer;
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    handleTextChange(currentState.getOriginalText());
//                    translateController.execute(currentState.getOriginalText());
//                    displayLabel.setText(translateViewModel.getState().getTranslatedText());
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
//                    translateController.execute(currentState.getOriginalText());
//                    displayLabel.setText(translateViewModel.getState().getTranslatedText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    handleTextChange(currentState.getOriginalText());
//                    translateController.execute(currentState.getOriginalText());
//                    displayLabel.setText(translateViewModel.getState().getTranslatedText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // TODO add action listeners for new buttons here...

        // adding key listener for wordInputField to update the state if key is typed
        // not sure if this key listener is necessary, or if should just directly
        // access the wordInputField in the document listener above
        wordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                TranslateState currentState = translateViewModel.getState();
                currentState.setOriginalText(wordInputField.getText() + e.getKeyChar());
                translateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(wordInfo);
        this.add(displayInfo);
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
        }
    }
    private void setFields(TranslateState state) {
        wordInputField.setText(state.getOriginalText());
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
