package entity;

import java.util.ArrayList;

public class OriginalWord implements Word{

    private final String word;

    private final String language;

    private ArrayList<String> relatedWords;

    OriginalWord(String userWord, String userLanguage){
        this.word = userWord;
        this.language = userLanguage;
        this.relatedWords = new ArrayList<>();
    }

    @Override
    public String getWord() {
        return this.word;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    public void addRelatedWords(ArrayList<String> relatedList) {
        this.relatedWords = relatedList;
    }
}
