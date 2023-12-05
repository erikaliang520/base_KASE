package use_case.related_words.related_words_generate;

import entity.factories.OriginalRelatedWordFactory;

public class RelatedInteractor implements RelatedInputBoundary {
    final RelatedWordDataAccessInterface relatedDataAccessObject;
    final RelatedOutputBoundary relatedPresenter;
    final OriginalRelatedWordFactory relatedWordFactory;

    public RelatedInteractor(RelatedWordDataAccessInterface relatedDataAccessObject, RelatedOutputBoundary relatedPresenter, OriginalRelatedWordFactory relatedWordFactory) {
        this.relatedDataAccessObject = relatedDataAccessObject;
        this.relatedPresenter = relatedPresenter;
        this.relatedWordFactory = relatedWordFactory;
    }

    @Override
    public void execute(RelatedInputData relatedInputData) {

        RelatedOutputData relatedOutputData = new RelatedOutputData(relatedInputData.getWord(), relatedInputData.getLanguage(), relatedInputData.getStrategy());

        // when the list of generated words is NOT empty:
        if (!relatedOutputData.getRelatedWords().isEmpty()){
            relatedPresenter.prepareSuccessView(relatedOutputData);
        } else {
            relatedPresenter.prepareFailView("No suggestions available for your word(s) at this moment.");
        }

    }
}
