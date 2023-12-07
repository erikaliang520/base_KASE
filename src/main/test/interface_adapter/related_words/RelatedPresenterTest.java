package interface_adapter.related_words;

import data_access.testing_DAOs.InMemoryRelatedWordsDAO;
import entity.factories.OriginalRelatedWordFactory;
import entity.factories.OriginalWordFactory;
import entity.related_words.RelatedWordsSelectionStrategy;
import interface_adapter.ViewManagerModel;
import interface_adapter.api.datamuse4J.src.datamuse.SynonymStrategy;
import org.junit.jupiter.api.Test;
import use_case.related_words.related_words_generate.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelatedPresenterTest {
    RelatedViewModel relatedViewModel = new RelatedViewModel();
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    RelatedOutputData relatedOutputData;
    RelatedOutputBoundary thePresenter;

    @Test
    void successTest(){

        RelatedWordDataAccessInterface relatedWordsRepo = new InMemoryRelatedWordsDAO();
        RelatedOutputBoundary successPresenter = new RelatedPresenter(relatedViewModel, viewManagerModel);

        RelatedInputData relatedInputData  = new RelatedInputData("apple", "english");

        thePresenter = successPresenter;

        RelatedWordsSelectionStrategy theStrategy = new SynonymStrategy(3);
        OriginalRelatedWordFactory factory = new OriginalWordFactory();
        RelatedInputBoundary interactor = new RelatedInteractor(relatedWordsRepo, successPresenter, theStrategy, factory);

        interactor.execute(relatedInputData);

        List<String> generatedWords = theStrategy.selectTopWordsStrategy(relatedInputData.getWord());

        relatedOutputData = new RelatedOutputData(relatedInputData.getWord(), relatedInputData.getLanguage(), generatedWords);

    }

    @Test
    void prepareSuccessView() {
        RelatedOutputData word = relatedOutputData;
        thePresenter.prepareSuccessView(relatedOutputData);
        assertEquals(word.getWord(), "apple");
        assertEquals(word.getLanguage(), "english");
        assertFalse(word.getRelatedWords().isEmpty());
    }

    @Test
    void prepareFailView() {
        fail("Use Case failure is expected");
        thePresenter.prepareFailView("ViewFailed");
    }

}