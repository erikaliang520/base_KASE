package use_case.related_words.related_words_generate;

public class RelatedInputData {
    final private String word;
    final private String language;
//    private final RelatedWordsSelectionStrategy strategy;

    public RelatedInputData(String word, String language) {
        this.word = word;
        this.language = language;
    }

    String getWord() {
        return word;
    }

    String getLanguage(){
        return language;
    }

}
