//package app;
//
//import data_access.history.RelatedWordsHistoryDataAccessObject;
//import data_access.history.WordHistoryDataAccessObject;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.history.HistoryController;
//import interface_adapter.history.HistoryViewModel;
//import interface_adapter.related_words.RelatedController;
//import interface_adapter.related_words.RelatedViewModel;
//import interface_adapter.textspeech.TextSpeechController;
//import interface_adapter.textspeech.TextSpeechViewModel;
//import interface_adapter.translate.TranslateController;
//import interface_adapter.translate.TranslateViewModel;
//import use_case.history.HistoryDataAccessInterface;
//import use_case.textspeech.TextSpeechDataAccessInterface;
//import use_case.translate.TranslateDataAccessInterface;
//import view.TranslateView;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class GenerateRelatedWordsUseCaseFactory {
//
//    private GenerateRelatedWordsUseCaseFactory() {}
//
//    public static TranslateView create(
//            ViewManagerModel viewManagerModel, HistoryViewModel historyViewModel, RelatedViewModel relatedViewModel,
//            TranslateViewModel translateViewModel, TextSpeechViewModel textSpeechViewModel,
//            TranslateDataAccessInterface translateDataAccessInterface, HistoryDataAccessInterface historyDataAccessInterface,
//            TextSpeechDataAccessInterface textSpeechDataAccessInterface,
//            RelatedWordsHistoryDataAccessObject relatedWordsHistoryDataAccessObject
//    ) {
//        try {
//            RelatedController relatedController = createRelatedUseCase();
//            TranslateController translateController = createTranslatorUseCase();
//            TextSpeechController textSpeechController = createTextSpeechUseCase();
//            HistoryController historyController = createHistoryUseCase();
//
//            return new TranslateView();
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not open word data file.");
//        }
//
//        return null;
//    }
//
//    private static RelatedController;
//
//    private static TranslateController;
//
//    private static TextSpeechController;
//
//    private static HistoryController;
//
//
//
//
//}
