package use_case.translate;

import interface_adapter.api.TranslateService;

import java.io.IOException;

public class TranslateInteractor implements TranslateInputBoundary {

    private final TranslateService translateService;

    private final TranslateOutputBoundary translatePresenter;
    public TranslateInteractor(TranslateService translateService, TranslateOutputBoundary translatePresenter) {
        this.translateService = translateService;
        this.translatePresenter = translatePresenter;
    }

    @Override
    public void execute(TranslateInputData translateInputData) throws IOException {
        String original = translateInputData.getWord();
        String translated = translateService.performTranslate(original);

        TranslateOutputData translation = new TranslateOutputData(original, translated);

        // TODO add translation to WordHistory using a DAO

        translatePresenter.prepareSuccessView(translation);

    }
}
