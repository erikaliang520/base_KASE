package use_case.related_words;

import entity.Word;
import entity.factories.WordFactory;

public class RelatedInteractor implements RelatedInputBoundary {
    final RelatedWordDataAccessInterface relatedDataAccessObject;
    final RelatedOutputBoundary relatedPresenter;
    final WordFactory relatedWordFactory;

    public RelatedInteractor(RelatedWordDataAccessInterface relatedDataAccessObject, RelatedOutputBoundary relatedPresenter, WordFactory relatedWordFactory) {
        this.relatedDataAccessObject = relatedDataAccessObject;
        this.relatedPresenter = relatedPresenter;
        this.relatedWordFactory = relatedWordFactory;
    }

    @Override
    public void execute(RelatedInputData relatedInputData) {

        // TODO idk if this is necessary since the translation already happened before this use case can pop off:
        Word word = relatedWordFactory.createWord(relatedInputData.getWord(), relatedInputData.getLanguage());

        // TODO how to make this go into DAO history properly? or if its even needed?
        relatedDataAccessObject.save(word);

        RelatedOutputData relatedOutputData = new RelatedOutputData(relatedInputData.getWord(), relatedInputData.getLanguage(),relatedInputData.getStrategy());
        relatedPresenter.prepareView(relatedOutputData);


    }
}
