//package app;
//
//import data_access.history.WordHistoryDataAccessObject;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.history.HistoryController;
//import interface_adapter.history.HistoryPresenter;
//import interface_adapter.history.HistoryViewModel;
//import use_case.history.HistoryDataAccessInterface;
//import use_case.history.HistoryInputBoundary;
//import use_case.history.HistoryInteractor;
//import use_case.history.HistoryOutputBoundary;
//import view.HistoryView;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class HistoryUseCaseFactory {
//
//    private HistoryUseCaseFactory(){}
//
//    public static HistoryView create(ViewManagerModel viewManagerModel,
//                                     HistoryViewModel historyViewModel,
//                                     HistoryDataAccessInterface historyDataAccessObject){
//        try {
//            HistoryController historyController = createHistoryUseCase(viewManagerModel,
//                    historyViewModel,
//                    historyDataAccessObject);
//            return new HistoryView();
//        } catch (IOException e){
//            JOptionPane.showMessageDialog(null, "Unable to show history.");
//        }
//        return null;
//    }
//
//    public static HistoryController createHistoryUseCase(
//            ViewManagerModel viewManagerModel,
//            HistoryViewModel historyViewModel,
//            HistoryDataAccessInterface historyDataAccessObject) throws IOException {
//        HistoryOutputBoundary historyPresenter = new HistoryPresenter(viewManagerModel, historyViewModel);
//
//        HistoryInputBoundary historyInteractor = new HistoryInteractor(historyDataAccessObject, historyPresenter);
//
//        return new HistoryController(historyInteractor);
//    }
//}
