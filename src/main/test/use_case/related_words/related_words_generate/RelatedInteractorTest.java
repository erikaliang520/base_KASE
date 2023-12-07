package use_case.related_words.related_words_generate;

import entity.WordRelated;
import entity.factories.OriginalRelatedWordFactory;
import entity.factories.OriginalWordFactory;
import entity.related_words.RelatedWordsSelectionStrategy;
import interface_adapter.api.datamuse4J.src.datamuse.SynonymStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelatedInteractorTest {

    @Test
    void successTest(){

        RelatedInputData relatedInputData = new RelatedInputData("apple", "english");
        List<WordRelated> saver = new ArrayList<WordRelated>();
        RelatedWordDataAccessInterface wordRepository = new RelatedWordDataAccessInterface() {
            @Override
            public void save(WordRelated word) {
                saver.add(word);
            }
        };
        RelatedWordsSelectionStrategy strategy = new SynonymStrategy(3);
        OriginalRelatedWordFactory factory = new OriginalWordFactory();

        RelatedOutputBoundary successPresenter = new RelatedOutputBoundary() {
            @Override
            public void prepareSuccessView(RelatedOutputData generatedWords) {
                assertEquals(generatedWords.getWord(), "apple");
                assertEquals(generatedWords.getLanguage(), "english");
                assertFalse(generatedWords.getRelatedWords().isEmpty());

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use Case Failure is unexpected");

            }
        };

        RelatedInteractor relatedInteractor = new RelatedInteractor(wordRepository, successPresenter, strategy, factory);
        relatedInteractor.execute(relatedInputData);

    }



    @Test
    void failTestWhenNoGeneratedWordsExist() {
        RelatedInputData inputData = new RelatedInputData("abcdefjswfrih", "english");
        List<WordRelated> saver = new ArrayList<WordRelated>();
        RelatedWordDataAccessInterface wordRepository = new RelatedWordDataAccessInterface() {
            @Override
            public void save(WordRelated word) {
                saver.add(word);
            }
        };

        RelatedWordsSelectionStrategy strategy = new SynonymStrategy(3);


        OriginalRelatedWordFactory factory = new OriginalWordFactory();
        WordRelated word = factory.createWord("abcdefjswfrih", "english", strategy.selectTopWordsStrategy("abcdefjswfrih"));
        wordRepository.save(word);

        //This creates a presenter that tests whether the test case is as we expect.
        RelatedOutputBoundary failurePresenter = new RelatedOutputBoundary() {

            @Override
            public void prepareSuccessView(RelatedOutputData generatedWords) {
                fail("Use Case Sucess is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No suggestions available for your word(s) at this moment.", error);            }
        };

        RelatedInputBoundary interactor = new RelatedInteractor(wordRepository, failurePresenter, strategy, factory);
        interactor.execute(inputData);
    }
}