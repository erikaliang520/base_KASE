package frameworks_and_drivers.api.textspeech;

import entity.text_to_speech.TextSpeechService;

import java.io.IOException;

public class ExternalTextSpeechService implements TextSpeechService {
    private final TextSpeechApiClient textSpeechApiClient;

    public ExternalTextSpeechService(TextSpeechApiClient textSpeechApiClient) {
        this.textSpeechApiClient = textSpeechApiClient;
    }

    @Override
    public String performTextToSpeech(String text) throws IOException {
        String audioPath = textSpeechApiClient.synthesizeText(text);
        return audioPath;
    }
}

