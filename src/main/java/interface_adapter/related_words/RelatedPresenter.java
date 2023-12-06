package interface_adapter.related_words;

import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryViewModel;
import use_case.related_words.related_words_generate.RelatedOutputBoundary;
import use_case.related_words.related_words_generate.RelatedOutputData;


public class RelatedPresenter implements RelatedOutputBoundary {

    // should be very similar to translate view model, since it's same panel
    private final RelatedViewModel relatedViewModel;

    // we actually don't care about history model since it's a pop up and not switch between
    //private final HistoryViewModel historyViewModel;
    private ViewManagerModel viewManagerModel;

    public RelatedPresenter(RelatedViewModel relatedViewModel, ViewManagerModel viewManagerModel) {
        this.relatedViewModel = relatedViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RelatedOutputData generatedWords) {

        RelatedState relatedState = relatedViewModel.getState();
        relatedState.setRelatedWordsGenerated(generatedWords.getRelatedWords());
        this.relatedViewModel.setState(relatedState);
        relatedViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(relatedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        RelatedState relatedState = relatedViewModel.getState();
        relatedState.setRelatedWordsError(error);
        relatedViewModel.firePropertyChanged();
    }
}
