package frameworks_and_drivers.api.textspeechtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import interface_adapter.textspeech.TextSpeechState;

public class StateTest {

    @Test
    public void testDefaultConstructor() {
        TextSpeechState state = new TextSpeechState();

        assertEquals("", state.getOriginalText());
        assertEquals("", state.getSpokenText());
    }

    @Test
    public void testSetOriginalText() {
        TextSpeechState state = new TextSpeechState();
        state.setOriginalText("Test Original Text");

        assertEquals("Test Original Text", state.getOriginalText());
    }

    @Test
    public void testSetSpokenText() {
        TextSpeechState state = new TextSpeechState();
        state.setSpokenText("Test Spoken Text");

        assertEquals("Test Spoken Text", state.getSpokenText());
    }

    @Test
    public void testCopyConstructor() {
        TextSpeechState originalState = new TextSpeechState();
        originalState.setOriginalText("Test Original Text");
        originalState.setSpokenText("Test Spoken Text");

        TextSpeechState copiedState = new TextSpeechState(originalState);

        assertEquals("Test Original Text", copiedState.getOriginalText());
        assertEquals("Test Spoken Text", copiedState.getSpokenText());
    }
}

