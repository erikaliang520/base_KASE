package interface_adapter.history;

import java.util.ArrayList;
public class HistoryState {
    private ArrayList<String> wordHistory;
    public String historyError = null;
    public HistoryState(HistoryState copy){
        this.wordHistory = copy.wordHistory;

    }

    public HistoryState(){
    }

    public ArrayList<String> getWordHistory(){
        return wordHistory;
    }

    public void setWordHistory(ArrayList<String> wordList){
        this.wordHistory = wordList;
    }

    public void setHistoryError(String error)
    {this.historyError = error;}}
