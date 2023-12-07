package use_case.related_words.related_words_generate;

public class RelatedInputData {
    final private String word;
    final private String language;

    public RelatedInputData(String word, String language) {
        this.word = word;
        this.language = language;
    }

    public String getWord() {
        return word;
    }

    public String getLanguage(){
        return language;
    }

}
