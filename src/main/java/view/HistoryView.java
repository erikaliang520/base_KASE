package view;
import entity.TranslatedWord;
import entity.Word;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;

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
import java.util.ArrayList;
import java.util.HashMap;
public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {public final String viewName = "Word History";
    private final HistoryViewModel historyViewModel;
    final JLabel originalWordField = new JLabel();
    final JLabel translatedWordField = new JLabel();
    private final JTextArea historyTextArea;

    final JButton back;


    private final HistoryController historyController;

    public HistoryView(HistoryViewModel historyViewModel, HistoryController historyController){
        this.historyController = historyController;
        this.historyViewModel = historyViewModel;
        this.historyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Word History");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false); // Sets as read-only
        JScrollPane scrollPane = new JScrollPane(historyTextArea);


        JPanel originalInfo = new JPanel();
        JLabel originalLabel = new JLabel("Original Word");
        JPanel translatedInfo = new JPanel();
        JLabel translatedLabel = new JLabel("Translated Word");

        back = new JButton(historyViewModel.BACK_BUTTON_LABEL);
        //        back.addActionListener(this);
        JPanel buttons = new JPanel();
        buttons.add(back);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(scrollPane);
        this.add(back);

        back.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            HistoryState currentState = historyViewModel.getState();

                            historyController.execute();
                        }
                    }
                }
        );

    }

//    public void handleBackButtonClick(ActionEvent evt){
//        if (evt.getSource() == back) {
//            // Handles back button click
//            historyController.navigateBackToTranslate();
//        }
//    }

    public void actionPerformed(ActionEvent evt)
    {System.out.println("Click " + evt.getActionCommand());}

    public void propertyChange(PropertyChangeEvent evt){
        HistoryState state = (HistoryState) evt.getNewValue();
//        setFields(state);

    }

    private void setFields(HistoryState state){}

    //    private void updateHistoryTextArea(ArrayList<Word> insertionOrder, HashMap<Word, Word> wordHistory){
    //        historyTextArea.setText(""); // Clears existing text
    //
    //        //use wordhistoryDAO.insertionOrder for the arraylist and .wordHistory for the hashmap
    //        for (Word word: insertionOrder){ // Assume wordHistory is of format [org1, trs1, org2, trs2, org3, trs3...]
    //            String originalWord = word.getWord();
    //            String translatedWord = wordHistory.getOrDefault(word, new TranslatedWord("", "fr")).getWord();
    //            historyTextArea.append(originalWord + " - " + translatedWord + "\n");
    //
    //        }
    //    }
}
