package interface_adapter.related_words;

import entity.related_words.RelatedWordsSelectionStrategy;
import use_case.related_words.related_words_generate.RelatedInputBoundary;
import use_case.related_words.related_words_generate.RelatedInputData;

public class RelatedController {

    final RelatedInputBoundary wordRelatedUseCaseInteractor;

    public RelatedController(RelatedInputBoundary relatedInputBoundary){
        this.wordRelatedUseCaseInteractor = relatedInputBoundary;
    }

    public void execute( String word,
                         String language,
                         RelatedWordsSelectionStrategy developerStrategy){

        RelatedInputData relatedInputData = new RelatedInputData(word, language, developerStrategy);

        wordRelatedUseCaseInteractor.execute(relatedInputData);

    }

}
