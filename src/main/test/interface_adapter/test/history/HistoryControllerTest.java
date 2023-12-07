package interface_adapter.test.history;

import interface_adapter.history.HistoryController;
import org.junit.Test;
import use_case.history.HistoryInputBoundary;
import static org.mockito.Mockito.*;
public class HistoryControllerTest {
    @Test
    public void execute_CallsUseCaseInteractor() {
        // Arrange
        HistoryInputBoundary mockInteractor = mock(HistoryInputBoundary.class);
        HistoryController historyController = new HistoryController(mockInteractor);

        // Act
        historyController.execute();

        // Assert
        verify(mockInteractor, times(1)).execute();
    }
}
