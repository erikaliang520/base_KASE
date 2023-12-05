package use_case.related_words.related_words_generate;

import java.util.List;

public class RelatedOutputData {
    final private String word;
    final private String language;
    private List<String> generatedWords;

    public RelatedOutputData(String word, String language, List<String> generatedWords) {
        this.word = word;
        this.language = language;
        this.generatedWords = generatedWords;
    }

    public List<String> getRelatedWords(){
        return generatedWords;
    }

    public String getWord() {
        return word;
    }

    public String getLanguage() {
        return language;
    }


}
