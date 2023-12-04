package use_case.related_words;

import entity.related_words.RelatedWordsSelectionStrategy;

import java.util.List;

public class RelatedOutputData {
    final private String word;
    final private String language;
    private RelatedWordsSelectionStrategy strategy;
    private List<String> generatedWords;

    public RelatedOutputData(String word, String language, RelatedWordsSelectionStrategy developerStrategy) {
        this.word = word;
        this.language = language;
        this.strategy = developerStrategy;

        // inject my string into whatever strategy pattern was chosen:
        this.generatedWords =  strategy.selectTopWordsStrategy(this.word);
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

    void setGeneratedWordsNewStrategy(RelatedWordsSelectionStrategy newStrategy){
        this.generatedWords = newStrategy.selectTopWordsStrategy(this.word);
        this.strategy = newStrategy;
    }

}
