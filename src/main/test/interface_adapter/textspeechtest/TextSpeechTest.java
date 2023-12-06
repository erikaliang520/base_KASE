package interface_adapter.textspeechtest;

import interface_adapter.api.textspeech.ExternalTextSpeechService;
import interface_adapter.api.textspeech.TextSpeechApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextSpeechTest {
    private TextSpeechApiClient textSpeechApiClient;
    private ExternalTextSpeechService externalTextSpeechService;

    @BeforeEach
    public void setUp() {
        textSpeechApiClient = new TextSpeechApiClient(){
            @Override
            public void synthesizeText(String text) {
                // Simulate the behavior of the TextSpeechApiClient
                // For this example, we're just assuming it does nothing.
            }
        };
        externalTextSpeechService = new ExternalTextSpeechService(textSpeechApiClient);
    }

    @Test
    public void testPerformTextToSpeech() throws IOException {
        String inputText = "Hello, world!";
        String outputText = externalTextSpeechService.performTextToSpeech(inputText);
        assertEquals(inputText, outputText);
    }
}

