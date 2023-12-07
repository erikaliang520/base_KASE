package interface_adapter.textspeech;
import use_case.textspeech.TextSpeechInputBoundary;
import use_case.textspeech.TextSpeechInputData;

import java.io.IOException;


public class TextSpeechController {
    private TextSpeechInputBoundary textSpeechinteractor;

    public TextSpeechController(TextSpeechInputBoundary textSpeechinteractor) {
        this.textSpeechinteractor = textSpeechinteractor;
    }


    public void execute(String text) throws IOException {
        TextSpeechInputData inputData = new TextSpeechInputData();
        textSpeechinteractor.execute(inputData);
    }
}

