package interface_adapter.translate;

import interface_adapter.ViewManagerModel;
import use_case.translate.TranslateOutputBoundary;
import use_case.translate.TranslateOutputData;

public class TranslatePresenter implements TranslateOutputBoundary {


    private ViewManagerModel viewManagerModel;
    private final TranslateViewModel translateViewModel;


    public TranslatePresenter(ViewManagerModel viewManagerModel, TranslateViewModel translateViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.translateViewModel = translateViewModel;
    }

    @Override
    public void prepareSuccessView(TranslateOutputData translation) {
        TranslateState translateState = translateViewModel.getState();
        translateState.setTranslatedText(translation.getTranslated().getWord());
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
