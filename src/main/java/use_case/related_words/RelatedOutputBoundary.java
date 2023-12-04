package use_case.related_words;

public interface RelatedOutputBoundary {
    void prepareView(RelatedOutputData generatedWords);

//    todo idk if this would be optional or not
//    void prepareSuccessView(SignupOutputData user);
//
//    depends on implementation, does the user ask for
//    generated related words or is it a given (then wouldve been empty string anyways)
//    void prepareFailView(String error);

}
