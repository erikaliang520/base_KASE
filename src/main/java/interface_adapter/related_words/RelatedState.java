package interface_adapter.related_words;

import java.util.List;

public class RelatedState {
    private String originalText = "";
    private String translatedText = "";
    public List<String> relatedWordsGenerated = null;

    public RelatedState(RelatedState copy) {
        this.originalText = copy.originalText;
        this.translatedText = copy.translatedText;
        this.relatedWordsGenerated = copy.relatedWordsGenerated;
    }

    public RelatedState(){}

    public String getOriginalText() {
        return originalText;
    }

    public String getTranslatedText(){
        return translatedText;
    }

    public List<String> getRelatedWordsGenerated() {
        return relatedWordsGenerated;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public void setRelatedWordsGenerated(List<String> relatedWordsGenerated) {
        this.relatedWordsGenerated = relatedWordsGenerated;
    }
}
