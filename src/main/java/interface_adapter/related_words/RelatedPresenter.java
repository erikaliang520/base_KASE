package interface_adapter.related_words;

import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryViewModel;
import use_case.related_words.RelatedOutputBoundary;
import use_case.related_words.RelatedOutputData;


public class RelatedPresenter implements RelatedOutputBoundary {

    private final RelatedViewModel relatedViewModel; // TODO technically this is same view model as translate though

    private final HistoryViewModel historyViewModel;

    private ViewManagerModel viewManagerModel;

    public RelatedPresenter(RelatedViewModel relatedViewModel, HistoryViewModel historyViewModel, ViewManagerModel viewManagerModel) {
        this.relatedViewModel = relatedViewModel;
        this.historyViewModel = historyViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RelatedOutputData generatedWords) {

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
