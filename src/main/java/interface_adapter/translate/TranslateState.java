package interface_adapter.translate;

public class TranslateState {
    private String originalText = "";
    private String translatedText = null;

    public TranslateState(TranslateState copy) {
        originalText = copy.originalText;
        translatedText = copy.translatedText;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public TranslateState() {}

    public String getOriginalText() {
        return originalText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

}
