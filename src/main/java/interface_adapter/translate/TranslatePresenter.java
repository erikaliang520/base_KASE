package interface_adapter.translate;

import use_case.translate.TranslateOutputBoundary;
import use_case.translate.TranslateOutputData;

public class TranslatePresenter implements TranslateOutputBoundary {

    @Override
    public void prepareSuccessView(TranslateOutputData translation) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
