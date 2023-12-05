package use_case.related_words.related_words_generate;

import entity.Word;
import entity.factories.OriginalRelatedWordFactory;
import entity.related_words.RelatedWordsSelectionStrategy;

import java.util.List;

public class RelatedInteractor implements RelatedInputBoundary {
    final RelatedWordDataAccessInterface relatedDataAccessObject;
    final RelatedOutputBoundary relatedPresenter;
    final RelatedWordsSelectionStrategy strategy;
    final OriginalRelatedWordFactory relatedWordsFactory;

    public RelatedInteractor(RelatedWordDataAccessInterface relatedDataAccessObject, RelatedOutputBoundary relatedPresenter, RelatedWordsSelectionStrategy developerStrategy, OriginalRelatedWordFactory relatedWordsFactory) {
        this.relatedDataAccessObject = relatedDataAccessObject;
        this.relatedPresenter = relatedPresenter;
        this.strategy = developerStrategy;
        this.relatedWordsFactory = relatedWordsFactory;
    }

    @Override
    public void execute(RelatedInputData relatedInputData) {
        List<String> generatedWords = strategy.selectTopWordsStrategy(relatedInputData.getWord());
        RelatedOutputData relatedOutputData = new RelatedOutputData(relatedInputData.getWord(), relatedInputData.getLanguage(), generatedWords);
        Word word = relatedWordsFactory.createWord(relatedInputData.getWord(), relatedInputData.getLanguage(), generatedWords);
        relatedDataAccessObject.save(word);

        // when the list of generated words is NOT empty:
        if (!relatedOutputData.getRelatedWords().isEmpty()){
            relatedPresenter.prepareSuccessView(relatedOutputData);
        } else {
            relatedPresenter.prepareFailView("No suggestions available for your word(s) at this moment.");
        }

    }
}
