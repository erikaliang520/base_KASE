package use_case.related_words;

public interface RelatedOutputBoundary {
    void prepareSuccessView(RelatedOutputData generatedWords);
    void prepareFailView(String error);

}
