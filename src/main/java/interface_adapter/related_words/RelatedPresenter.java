package interface_adapter.related_words;

import use_case.related_words.RelatedOutputBoundary;
import use_case.related_words.RelatedOutputData;

public class RelatedPresenter implements RelatedOutputBoundary {

    private final RelatedViewModel relatedViewModel;

    // todo , add history view model here
    //private final RelatedViewModel relatedViewModel;

    //private ViewManagerModel viewManagerModel;

    public RelatedPresenter(RelatedViewModel relatedViewModel) {
        this.relatedViewModel = relatedViewModel;
    }

    @Override
    public void prepareView(RelatedOutputData generatedWords) {

    }
}
