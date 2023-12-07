package use_case.translate;

import entity.Word;
import entity.factories.OriginalWordFactory;
import entity.factories.TranslatedWordFactory;
import entity.translate.TranslateService;

import java.io.IOException;

public class TranslateInteractor implements TranslateInputBoundary {

    private final TranslateService translateService;

    final TranslateDataAccessInterface wordDataAccessObject;

    private final TranslateOutputBoundary translatePresenter;

    final OriginalWordFactory originalWordFactory;
    final TranslatedWordFactory translatedWordFactory;
    public TranslateInteractor(TranslateService translateService,
                               TranslateDataAccessInterface translateDataAccessObject,
                               TranslateOutputBoundary translatePresenter,
                               OriginalWordFactory originalWordFactory,
                               TranslatedWordFactory translatedWordFactory) {
        this.translateService = translateService;
        this.wordDataAccessObject = translateDataAccessObject;
        this.translatePresenter = translatePresenter;
        this.originalWordFactory = originalWordFactory;
        this.translatedWordFactory = translatedWordFactory;
    }

    @Override
    public void execute(TranslateInputData translateInputData) throws IOException {
        String original = translateInputData.getWord();
        String translated = translateService.performTranslate(original);
        Word originalWord = originalWordFactory.createWord(original, "English");
        Word translatedWord = translatedWordFactory.createWord(translated, "French");

        TranslateOutputData translateOutputData = new TranslateOutputData(originalWord, translatedWord);

        if (translateInputData.isSaveToHistory()) {
            wordDataAccessObject.save(originalWord, translatedWord);
        }

        translatePresenter.prepareSuccessView(translateOutputData);

    }
}
