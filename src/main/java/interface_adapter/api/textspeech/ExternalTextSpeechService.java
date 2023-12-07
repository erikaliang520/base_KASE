package interface_adapter.api.textspeech;

import use_case.ports.api.TextSpeechService;

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

