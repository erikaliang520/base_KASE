package interface_adapter.translate;

import interface_adapter.ViewManagerModel;
import use_case.translate.TranslateOutputBoundary;
import use_case.translate.TranslateOutputData;

public class TranslatePresenter implements TranslateOutputBoundary {


    private final TranslateViewModel translateViewModel;
    private ViewManagerModel viewManagerModel;

    public TranslatePresenter(TranslateViewModel translateViewModel, ViewManagerModel viewManagerModel) {
        this.translateViewModel = translateViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(TranslateOutputData translation) {
        TranslateState translateState = translateViewModel.getState();
        translateState.setTranslatedText(translation.getTranslated());
        this.translateViewModel.setState(translateState);
        this.translateViewModel.firePropertyChanged();

        // is this necessary? since we are not changing to a
        // different view, only updating
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
