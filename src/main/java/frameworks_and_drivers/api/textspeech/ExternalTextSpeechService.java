package frameworks_and_drivers.api.textspeech;

import entity.services.TextSpeechService;

import java.io.IOException;

public class ExternalTextSpeechService implements TextSpeechService {
    private final TextSpeechApiClient textSpeechApiClient;

    public ExternalTextSpeechService(TextSpeechApiClient textSpeechApiClient) {
        this.textSpeechApiClient = textSpeechApiClient;
    }

    @Override
    public String performTextToSpeech(String text) throws IOException {
        textSpeechApiClient.synthesizeText(text);
        return text;
    }
}

