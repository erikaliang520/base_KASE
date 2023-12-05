package app;

import data_access.FileWordDataAccessObject;
import data_access.history.WordHistoryDataAccessObject;
import entity.factories.OriginalWordFactory;
import entity.factories.TranslatedWordFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.translate.TranslateViewModel;
import view.TranslateView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
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


        // TODO add pathname for csv file, using test as placeholder
        try {
            wordHistoryDataAccessObject = new WordHistoryDataAccessObject("test",
                    new OriginalWordFactory(),
                    new TranslatedWordFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TranslateView translateView = TranslateUseCaseFactory.create(viewManagerModel,
                translateViewModel, wordHistoryDataAccessObject);
        views.add(translateView, translateView.viewName);

        viewManagerModel.setActiveView(translateView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
