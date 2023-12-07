package interface_adapter.textspeech;

public class TextSpeechState {
    private String originalText = "";
    private String spokenText = "";

    public TextSpeechState(TextSpeechState copy) {
        originalText = copy.originalText;
        spokenText = copy.spokenText == null ? "" : copy.spokenText;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public TextSpeechState() {}

    public String getOriginalText() {
        return originalText;
    }

    public String getSpokenText() {
        return spokenText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public void setSpokenText(String spokenText) {
        this.spokenText = spokenText == null ? "" : spokenText;
    }
}


