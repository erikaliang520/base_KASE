package app;

import entity.factories.OriginalRelatedWordFactory;
import entity.factories.OriginalWordFactory;
import entity.factories.TranslatedWordFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.api.datamuse4J.src.datamuse.SynonymStrategy;
import interface_adapter.api.google_translate.ExternalTranslateService;
import interface_adapter.api.google_translate.TranslateApiClient;
import interface_adapter.api.textspeech.ExternalTextSpeechService;
import interface_adapter.api.textspeech.TextSpeechApiClient;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.related_words.RelatedController;
import interface_adapter.related_words.RelatedPresenter;
import interface_adapter.related_words.RelatedViewModel;
import interface_adapter.textspeech.TextSpeechController;
import interface_adapter.textspeech.TextSpeechPresenter;
import interface_adapter.textspeech.TextSpeechViewModel;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslatePresenter;
import interface_adapter.translate.TranslateViewModel;
import use_case.history.HistoryDataAccessInterface;
import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInteractor;
import use_case.history.HistoryOutputBoundary;
import use_case.related_words.related_words_generate.RelatedInputBoundary;
import use_case.related_words.related_words_generate.RelatedInteractor;
import use_case.related_words.related_words_generate.RelatedOutputBoundary;
import use_case.related_words.related_words_generate.RelatedWordDataAccessInterface;
import use_case.textspeech.TextSpeechDataAccessInterface;
import use_case.textspeech.TextSpeechInputBoundary;
import use_case.textspeech.TextSpeechInteractor;
import use_case.textspeech.TextSpeechOutputBoundary;
import use_case.translate.TranslateDataAccessInterface;
import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInteractor;
import use_case.translate.TranslateOutputBoundary;
import view.HistoryView;
import view.TranslateView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllUseCaseFactory {

    private AllUseCaseFactory() {}

    // returns all controllers in a list

    // uses same View, since text speech and generated related words uses same panel
    public static List<Object> create(
            ViewManagerModel viewManagerModel,

            // ViewModels that may or may not take over the Translate View
            HistoryViewModel historyViewModel,
            RelatedViewModel relatedViewModel,
            TranslateViewModel translateViewModel,
            TextSpeechViewModel textSpeechViewModel,

            // DAOs to keep track of depending on which info's saved
            TranslateDataAccessInterface translateDataAccessObject,
            HistoryDataAccessInterface historyDataAccessInterface,
            TextSpeechDataAccessInterface textSpeechDataAccessObject,
            RelatedWordDataAccessInterface relatedWordDataAccessObject
    ) {
        try {
            RelatedController relatedController = createRelatedUseCase(viewManagerModel, relatedViewModel, relatedWordDataAccessObject);
            TranslateController translateController = createTranslateUseCase(viewManagerModel, translateViewModel, translateDataAccessObject);
            TextSpeechController textSpeechController = createTextSpeechUseCase(viewManagerModel, textSpeechViewModel, textSpeechDataAccessObject);
            HistoryController historyController = createHistoryUseCase(viewManagerModel, historyViewModel, historyDataAccessInterface);

            // TODO might have to make ViewModels and Controllers for
            // TODO speechtext , translate and relatedwords implement same interface / class
            // TODO so can be used to make new View or should it still be the same?

            // TODO: below would be the returned view based on the use case, everything else shuld be same / implemented

            // return new TranslateView(relatedViewModel, relatedController); // View for this use case?
            List<Object> allViews = new ArrayList<>();

            TranslateView translateView = new TranslateView(translateViewModel, translateController,
                    relatedViewModel, relatedController,
                    textSpeechViewModel, textSpeechController,
                    historyViewModel, historyController);


            HistoryView historyView = new HistoryView(historyViewModel, historyController);

            // index: 0 - translate, 1 - history
            allViews.add(translateView);
            allViews.add(historyView);

            return allViews;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open word data file.");
        }

        return null;
    }

    // making the controllers / interactors for all other use cases

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

    private static HistoryController createHistoryUseCase(
            ViewManagerModel viewManagerModel,
            HistoryViewModel historyViewModel,
            HistoryDataAccessInterface historyDataAccessObject) throws IOException {

        HistoryOutputBoundary historyPresenter = new HistoryPresenter(viewManagerModel, historyViewModel);

        HistoryInputBoundary historyInteractor = new HistoryInteractor(historyDataAccessObject, historyPresenter);

        return new HistoryController(historyInteractor);
    }

    private static RelatedController createRelatedUseCase(
            ViewManagerModel viewManagerModel,
            RelatedViewModel relatedViewModel,
            RelatedWordDataAccessInterface relatedWordDataAccessInterface
    ) throws IOException {

        RelatedOutputBoundary relatedOutputBoundary = new RelatedPresenter(relatedViewModel, viewManagerModel);
        OriginalRelatedWordFactory originalRelatedWordFactory = new OriginalWordFactory();

        // here use case factory knows about the api and strategy:
        SynonymStrategy generateWordsStrategy =  new SynonymStrategy(3);

        RelatedInputBoundary relatedInteractor = new RelatedInteractor(
                relatedWordDataAccessInterface,
                relatedOutputBoundary,
                generateWordsStrategy,
                originalRelatedWordFactory
                );

        return new RelatedController(relatedInteractor);
    }


    private static TextSpeechController createTextSpeechUseCase(
            ViewManagerModel viewManagerModel,
            TextSpeechViewModel textSpeechViewModel,
            TextSpeechDataAccessInterface textSpeechDataAccessObject) throws IOException {

        TextSpeechOutputBoundary textSpeechOutputBoundary = new TextSpeechPresenter(viewManagerModel, textSpeechViewModel);

        ExternalTextSpeechService speechService = new ExternalTextSpeechService(new TextSpeechApiClient());

        TextSpeechInputBoundary textSpeechInteractor = new TextSpeechInteractor(textSpeechDataAccessObject,
                textSpeechOutputBoundary, speechService);

        return new TextSpeechController(textSpeechInteractor);
    }


}
