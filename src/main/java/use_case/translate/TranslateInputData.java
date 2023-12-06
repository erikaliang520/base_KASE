package use_case.translate;

public class TranslateInputData {
    final private String word;

    private boolean saveToHistory;
    public TranslateInputData(String word, boolean saveToHistory) {
        this.word = word;
        this.saveToHistory = saveToHistory;
    }

    String getWord() {
        return this.word;
    }

    boolean isSaveToHistory() {
        return  saveToHistory;
    }
}
