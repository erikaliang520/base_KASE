package use_case.translate;

public class TranslateOutputData {

    private final String original;

    private final String translated;
    public TranslateOutputData(String original, String translated) {
        this.original = original;
        this.translated = translated;
    }
}
