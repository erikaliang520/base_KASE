package use_case.related_words;

import entity.related_words.RelatedWordsSelectionStrategy;

public class RelatedInputData {
    final private String word;
    final private String language;
    private final RelatedWordsSelectionStrategy strategy;

    public RelatedInputData(String word, String language, RelatedWordsSelectionStrategy developerStrategy) {
        this.word = word;
        this.language = language;
        this.strategy = developerStrategy;
    }

    String getWord() {
        return word;
    }

    String getLanguage(){
        return language;
    }

    RelatedWordsSelectionStrategy getStrategy(){
        return strategy;
    }

}
