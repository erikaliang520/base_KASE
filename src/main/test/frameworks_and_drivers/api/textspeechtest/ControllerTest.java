package frameworks_and_drivers.api.textspeechtest;

import interface_adapter.textspeech.TextSpeechController;

import org.junit.jupiter.api.Test;
import use_case.textspeech.TextSpeechInputBoundary;
import use_case.textspeech.TextSpeechInputData;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest {

    private static class TextSpeechInputBoundaryStub implements TextSpeechInputBoundary {
        private boolean wasExecuteCalled = false;

        @Override
        public void execute(TextSpeechInputData inputData){
            wasExecuteCalled = true;
        }

        public boolean wasExecuteCalled() {
            return wasExecuteCalled;
        }
    }

    @Test
    public void testConvertTextToSpeech() throws IOException {
        // Given
        TextSpeechInputBoundaryStub interactor = new TextSpeechInputBoundaryStub();
        TextSpeechController controller = new TextSpeechController(interactor);
        String text = "Hello, World!";

        // When
        controller.execute(text);

        // Then
        assertTrue(interactor.wasExecuteCalled());
    }

    @Test
    public void testConvertTextToSpeechWithEmptyText() throws IOException {
        // Given
        TextSpeechInputBoundaryStub interactor = new TextSpeechInputBoundaryStub();
        TextSpeechController controller = new TextSpeechController(interactor);
        String text = "";

        // When
        controller.execute(text);

        // Then
        assertTrue(interactor.wasExecuteCalled());
    }

    @Test
    public void testConvertTextToSpeechWithNullText() throws IOException {
        // Given
        TextSpeechInputBoundaryStub interactor = new TextSpeechInputBoundaryStub();
        TextSpeechController controller = new TextSpeechController(interactor);
        String text = null;

        // When
        controller.execute(text);

        // Then
        assertTrue(interactor.wasExecuteCalled());
    }



}

