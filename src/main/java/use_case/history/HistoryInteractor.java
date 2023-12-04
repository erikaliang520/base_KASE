package use_case.history;

public class HistoryInteractor implements HistoryInputBoundary{

    final HistoryDataAccessInterface historyDataAccessObject;
    final HistoryOutputBoundary historyPresenter;

    public HistoryInteractor(HistoryDataAccessInterface historyDataAccessInterface,
                           HistoryOutputBoundary historyOutputBoundary){
        this.historyDataAccessObject = historyDataAccessInterface;
        this.historyPresenter = historyOutputBoundary;

    }

    @Override
    public void execute(){
        HistoryOutputData historyOutputData = new HistoryOutputData(historyDataAccessObject.get());

        if (historyOutputData.getWordHistory().isEmpty()){
            historyPresenter.prepareFailView("Translation history is currently empty or unavailable.");
        } else {
            historyPresenter.prepareSuccessView(historyOutputData);
        }
    }

}
