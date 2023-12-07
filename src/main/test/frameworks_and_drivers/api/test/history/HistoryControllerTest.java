package frameworks_and_drivers.api.test.history;

import interface_adapter.history.HistoryController;
import org.junit.Test;
import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoryControllerTest {
    @Test
    public void testExecute() {
        // Arrange
        HistoryInputBoundary mockUseCaseInteractor = new MockHistoryUseCaseInteractor();
        HistoryController historyController = new HistoryController(mockUseCaseInteractor);

        // Act
        historyController.execute();

        // Assert
        assertTrue(((MockHistoryUseCaseInteractor) mockUseCaseInteractor).isExecuteCalled());

    }

    private static class MockHistoryUseCaseInteractor implements HistoryInputBoundary {
        private boolean executeCalled = false;
        static HistoryInputData input;

        @Override
        public void execute(){
            executeCalled = true;
        }

        public boolean isExecuteCalled(){
            return executeCalled;
        }
    }
}
