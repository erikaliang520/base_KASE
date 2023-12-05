package use_case.translate;

import entity.Word;

public class TranslateOutputData {

    private final Word original;

    private final Word translated;
    public TranslateOutputData(Word original, Word translated) {
        this.original = original;
        this.translated = translated;
    }

    public Word getTranslated() {
        return translated;
    }
}
