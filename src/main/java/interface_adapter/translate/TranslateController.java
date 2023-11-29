package interface_adapter.translate;

import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInputData;

import java.io.IOException;

public class TranslateController {
    final TranslateInputBoundary translateInputBoundary;

    public TranslateController(TranslateInputBoundary translateInputBoundary) {
        this.translateInputBoundary = translateInputBoundary;
    }

    public void execute(String word) throws IOException {
        TranslateInputData translateInputData = new TranslateInputData(word);
        translateInputBoundary.execute(translateInputData);
    }
}
