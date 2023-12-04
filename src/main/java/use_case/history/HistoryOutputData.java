package use_case.history;

import entity.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryOutputData {
    //outputs a list of organized strings, [original_word, translated_word, original_word, translated_word...]
    private ArrayList<String> outputWords = new ArrayList<>();

    public HistoryOutputData(ArrayList<String> originalWordAndTranslatedWord){
        this.outputWords = originalWordAndTranslatedWord;
    }

    public ArrayList<String> getWordHistory(){
        return this.outputWords;
    }

}
