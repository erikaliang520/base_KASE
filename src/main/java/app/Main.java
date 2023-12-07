package app;

import data_access.FileWordDataAccessObject;
import data_access.history.RelatedWordsHistoryDataAccessObject;
import data_access.history.WordHistoryDataAccessObject;
import entity.factories.OriginalWordFactory;
import entity.factories.TranslatedWordFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.related_words.RelatedViewModel;
import interface_adapter.textspeech.TextSpeechViewModel;
import interface_adapter.translate.TranslateViewModel;
import view.HistoryView;
import view.TranslateView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("base_KASE Translate");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        TranslateViewModel translateViewModel = new TranslateViewModel();
        WordHistoryDataAccessObject wordHistoryDataAccessObject;

        HistoryViewModel historyViewModel = new HistoryViewModel();
        // wordHistoryDataAccessObject already can act as a DAO for History in addition to Translate

        RelatedViewModel relatedViewModel = new RelatedViewModel();
        RelatedWordsHistoryDataAccessObject relatedWordsHistoryDataAccessObject;

        TextSpeechViewModel textSpeechViewModel = new TextSpeechViewModel();
        // wordHistoryDataAccessObject already can act as a DAO for TextSpeech in addition to Translate


        // TODO add pathname for csv file, using test as placeholder
        try {
            wordHistoryDataAccessObject = new WordHistoryDataAccessObject("test",
                    new OriginalWordFactory(),
                    new TranslatedWordFactory());

            relatedWordsHistoryDataAccessObject = new RelatedWordsHistoryDataAccessObject("relatedWords");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Object> listOfViews = AllUseCaseFactory.create(viewManagerModel, historyViewModel,
                relatedViewModel, translateViewModel, textSpeechViewModel,
                wordHistoryDataAccessObject, wordHistoryDataAccessObject, wordHistoryDataAccessObject,
                relatedWordsHistoryDataAccessObject);

        assert listOfViews != null;
        TranslateView translateView = (TranslateView) listOfViews.get(0);
        views.add(translateView, translateView.viewName);

        HistoryView historyView = (HistoryView) listOfViews.get(1);
        views.add(historyView, historyView.viewName);


//        TranslateView translateView = TranslateUseCaseFactory.create(viewManagerModel,
//                translateViewModel, wordHistoryDataAccessObject);
//        views.add(translateView, translateView.viewName);

        viewManagerModel.setActiveView(translateView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setSize(1000, 300);
        application.setVisible(true);
    }
}