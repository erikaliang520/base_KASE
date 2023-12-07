package entity.services;

import java.io.IOException;

public interface TextSpeechService {
    String performTextToSpeech(String text) throws IOException;
}

