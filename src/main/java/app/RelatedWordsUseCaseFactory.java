package app;

import data_access.history.RelatedWordsHistoryDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.related_words.RelatedController;
import interface_adapter.related_words.RelatedViewModel;
import interface_adapter.textspeech.TextSpeechViewModel;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslateViewModel;
import use_case.history.HistoryDataAccessInterface;
import use_case.related_words.related_words_generate.RelatedWordDataAccessInterface;
import use_case.textspeech.TextSpeechDataAccessInterface;
import use_case.translate.TranslateDataAccessInterface;
import view.TranslateView;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class RelatedWordsUseCaseFactory {

    public static TranslateView create(
            ViewManagerModel viewManagerModel,

            // ViewModels that may or may not take over the Translate View
            HistoryViewModel historyViewModel,
            RelatedViewModel relatedViewModel,
            TranslateViewModel translateViewModel,
            TextSpeechViewModel textSpeechViewModel,

            // DAOs to keep track of depending on which info's saved
            TranslateDataAccessInterface translateDataAccessInterface,
            HistoryDataAccessInterface historyDataAccessInterface,
            TextSpeechDataAccessInterface textSpeechDataAccessInterface,
            RelatedWordDataAccessInterface relatedWordDataAccessInterface,
            RelatedWordsHistoryDataAccessObject relatedWordsHistoryDataAccessObject)
    {
        try {

            List<Object> listOfControllers = CreateAllUseCaseControllers.create(
                     viewManagerModel,
                    // ViewModels that may or may not take over the Translate View
                     historyViewModel,
                     relatedViewModel,
                     translateViewModel,
                     textSpeechViewModel,

                    // DAOs to keep track of depending on which info's saved
                     translateDataAccessInterface,
                     historyDataAccessInterface,
                     textSpeechDataAccessInterface,
                     relatedWordDataAccessInterface,
                    relatedWordsHistoryDataAccessObject);


            assert listOfControllers != null; // null pointer exception?
            RelatedController relatedController = (RelatedController) listOfControllers.get(2);

            return new TranslateView(relatedViewModel, relatedController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;

    }

}
