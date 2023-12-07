package entity;

import java.util.List;

public class OriginalWordRelated implements WordRelated{

    private final String word;

    private final String language;

    private List<String> relatedWords;

    public OriginalWordRelated(String userWord, String userLanguage, List<String> computedRelatedWords){
        this.word = userWord;
        this.language = userLanguage;
        this.relatedWords = computedRelatedWords;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    public List<String> getRelatedWords(){
        return relatedWords;
    }

    public void setRelatedWords(List<String> relatedList) {
        this.relatedWords = relatedList;
    }

}
