package use_case.translate;

public interface TranslateOutputBoundary {
    void prepareSuccessView(TranslateOutputData translation);

    void prepareFailView(String error);
}

