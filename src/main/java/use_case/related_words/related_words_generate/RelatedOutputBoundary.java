package use_case.related_words.related_words_generate;

public interface RelatedOutputBoundary {
    void prepareSuccessView(RelatedOutputData generatedWords);
    void prepareFailView(String error);

}
