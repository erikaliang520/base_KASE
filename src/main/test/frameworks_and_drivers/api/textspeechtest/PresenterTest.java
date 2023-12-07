package frameworks_and_drivers.api.textspeechtest;

import org.junit.Test;
import static org.junit.Assert.*;

import interface_adapter.ViewManagerModel;
import use_case.textspeech.TextSpeechOutputBoundary;
import use_case.textspeech.TextSpeechOutputData;
import interface_adapter.textspeech.TextSpeechViewModel;
import interface_adapter.textspeech.TextSpeechState;
import interface_adapter.textspeech.TextSpeechPresenter;

public class PresenterTest {

    @Test
    public void testPrepareTextSpeechSuccessView() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        TextSpeechViewModel textSpeechViewModel = new TextSpeechViewModel();
        TextSpeechPresenter presenter = new TextSpeechPresenter(viewManagerModel, textSpeechViewModel);
        TextSpeechOutputData speechData = new TextSpeechOutputData("Test Audio Content");

        // Act
        presenter.prepareTextSpeechSuccessView(speechData);

        // Assert
        TextSpeechState textSpeechState = textSpeechViewModel.getState();
        assertEquals("Test Audio Content", textSpeechState.getSpokenText());
    }

    @Test
    public void testPrepareTextSpeechFailView() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        TextSpeechViewModel textSpeechViewModel = new TextSpeechViewModel();
        TextSpeechPresenter presenter = new TextSpeechPresenter(viewManagerModel, textSpeechViewModel);
        String error = "Conversion Failed";

        // Act
        presenter.prepareTextSpeechFailView(error);

        // Assert
        // Here, you can add the logic to check whether the failure view preparation logic works as expected.
        // This can depend on how the prepareTextSpeechFailView() method is implemented in the TextSpeechPresenter class.
    }
}
