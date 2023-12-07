package interface_adapter.history;

import use_case.history.HistoryInputBoundary;

public class HistoryController {
    final HistoryInputBoundary historyUseCaseInteractor;

    public HistoryController(HistoryInputBoundary historyUseCaseInteractor){
        this.historyUseCaseInteractor = historyUseCaseInteractor;
    }

    public void execute(){ historyUseCaseInteractor.execute();

    }

    public void navigateBackToTranslate() {
    }
}
