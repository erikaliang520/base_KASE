package app;

import entity.factories.OriginalWordFactory;
import entity.factories.TranslatedWordFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.api.google_translate.ExternalTranslateService;
import interface_adapter.api.google_translate.TranslateApiClient;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslatePresenter;
import interface_adapter.translate.TranslateViewModel;
import use_case.translate.TranslateDataAccessInterface;
import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInteractor;
import use_case.translate.TranslateOutputBoundary;
import view.TranslateView;

import javax.swing.*;
import java.io.IOException;

public class TranslateUseCaseFactory {

    /** Prevent instantiation */
    private TranslateUseCaseFactory() {}

    public static TranslateView create(
            ViewManagerModel viewManagerModel,
            TranslateViewModel translateViewModel,
            TranslateDataAccessInterface translateDataAccessObject) {

        try {
            TranslateController translateController = createTranslateUseCase(viewManagerModel, translateViewModel,
                    translateDataAccessObject);
            return new TranslateView(translateViewModel, translateController,
                    relatedViewModel, relatedController,
                    textSpeechViewModel, textSpeechController,
                    historyViewModel, historyController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static TranslateController createTranslateUseCase(
            ViewManagerModel viewManagerModel,
            TranslateViewModel translateViewModel,
            TranslateDataAccessInterface translateDataAccessObject) throws IOException {

        TranslateOutputBoundary translateOutputBoundary = new TranslatePresenter(viewManagerModel,
                translateViewModel);

        OriginalWordFactory originalWordFactory = new OriginalWordFactory();
        TranslatedWordFactory translatedWordFactory = new TranslatedWordFactory();

        ExternalTranslateService translateService = new ExternalTranslateService(new TranslateApiClient());


        TranslateInputBoundary translateInteractor = new TranslateInteractor(translateService,
                translateDataAccessObject, translateOutputBoundary, originalWordFactory, translatedWordFactory);

        return new TranslateController(translateInteractor);
    }

}
