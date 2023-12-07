package entity.text_to_speech;

import java.io.IOException;

public interface TextSpeechService {
    String performTextToSpeech(String text) throws IOException;
}

