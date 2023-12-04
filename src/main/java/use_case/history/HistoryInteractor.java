package use_case.history;

public class HistoryInteractor implements HistoryInputBoundary{

    final HistoryDataAccessInterface historyDataAccessObject;
    final HistoryOutputBoundary historyPresenter;

    public HistoryInteractor(HistoryDataAccessInterface historyDataAccessInterface,
                           HistoryOutputBoundary historyOutputBoundary){
        this.historyDataAccessObject = historyDataAccessInterface;
        this.historyPresenter = historyOutputBoundary;

    }

    public void execute(){
        HistoryOutputData historyOutputData = new HistoryOutputData(historyDataAccessObject.get());
        historyPresenter.prepareSuccessView(historyOutputData);
    }
}
