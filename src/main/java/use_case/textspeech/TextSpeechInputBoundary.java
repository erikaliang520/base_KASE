package use_case.textspeech;
import use_case.textspeech.TextSpeechInputData;

import java.io.IOException;


public interface TextSpeechInputBoundary {
    void execute(TextSpeechInputData inputData) throws IOException;

}

