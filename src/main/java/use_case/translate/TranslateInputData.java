package use_case.translate;

public class TranslateInputData {
    final private String word;
    public TranslateInputData(String word) {
        this.word = word;
    }

    String getWord() {
        return this.word;
    }
}
