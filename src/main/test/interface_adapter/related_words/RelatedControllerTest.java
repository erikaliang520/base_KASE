package interface_adapter.related_words;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.related_words.related_words_generate.RelatedInputBoundary;
import use_case.related_words.related_words_generate.RelatedInputData;

import static org.junit.jupiter.api.Assertions.*;

class RelatedControllerTest {


    @Test
    public void testExecute() {
        // Arrange
        RelatedInputBoundary mockUseCaseInteractor = new MockRelatedUseCaseInteractor();
        RelatedController relatedController = new RelatedController(mockUseCaseInteractor);

        String word = "banana";
        String language = "english";

        // Act
        relatedController.execute(word, language);

        // Assert
        assertTrue( ((MockRelatedUseCaseInteractor) mockUseCaseInteractor).isExecuteCalled());
    }

    // A simple mock implementation of RelatedInputBoundary for testing
    private static class MockRelatedUseCaseInteractor implements RelatedInputBoundary {
        private boolean executeCalled = false;
        static RelatedInputData lastInputData;

        @Override
        public void execute(RelatedInputData inputData) {
            lastInputData = inputData;
            executeCalled = true;
            // Perform any necessary mock behavior or assertions here
        }

        public boolean isExecuteCalled() {
            return executeCalled;
        }


    }
}