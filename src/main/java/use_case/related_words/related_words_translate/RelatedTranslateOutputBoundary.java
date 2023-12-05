package use_case.related_words.related_words_translate;

public interface RelatedTranslateOutputBoundary {
    // only one view to prepare cos by this point, there was a generated related word
    // for the client to choose, the fail view would've come automatically from the translation service api
    void prepareSuccessView(RelatedTranslateOutputData wordToTranslate);
}
