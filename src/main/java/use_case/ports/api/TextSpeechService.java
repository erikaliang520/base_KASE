package use_case.ports.api;

import java.io.IOException;

public interface TextSpeechService {
    String performTextToSpeech(String text) throws IOException;
}

