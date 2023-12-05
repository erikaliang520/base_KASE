package interface_adapter.translate;

import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInputData;

import java.io.IOException;

public class TranslateController {
    final TranslateInputBoundary translateUseCaseInteractor;

    public TranslateController(TranslateInputBoundary translateUseCaseInteractor) {
        this.translateUseCaseInteractor = translateUseCaseInteractor;
    }

    public void execute(String word, boolean saveToHistory) throws IOException {
        TranslateInputData translateInputData = new TranslateInputData(word, saveToHistory);
        translateUseCaseInteractor.execute(translateInputData);
    }
}
