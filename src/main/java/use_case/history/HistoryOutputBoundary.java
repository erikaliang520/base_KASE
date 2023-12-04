package use_case.history;

public interface HistoryOutputBoundary {
    void prepareSuccessView(HistoryOutputData data);

    void prepareFailView(String error);
}
