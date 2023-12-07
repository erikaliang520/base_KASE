package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import interface_adapter.translate.TranslateViewModel;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;

public class HistoryPresenter implements HistoryOutputBoundary {

    private final TranslateViewModel translateViewModel;
    private final HistoryViewModel historyViewModel;
    private ViewManagerModel viewManagerModel;


    public HistoryPresenter(TranslateViewModel translateViewModel, ViewManagerModel viewManagerModel, HistoryViewModel historyViewModel){
        this.translateViewModel = translateViewModel;
        this.historyViewModel = historyViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(HistoryOutputData output){
        HistoryState historyState = historyViewModel.getState();
        historyState.setWordHistory(output.getWordHistory());
        this.historyViewModel.setState(historyState);
        historyViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(historyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String missingHistoryError){
        HistoryState historyState = historyViewModel.getState();
        historyState.setHistoryError(missingHistoryError);
        historyViewModel.firePropertyChanged();
    }
}
