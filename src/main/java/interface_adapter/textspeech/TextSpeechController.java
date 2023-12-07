package interface_adapter.textspeech;
import use_case.textspeech.TextSpeechInputBoundary;
import use_case.textspeech.TextSpeechInputData;


public class TextSpeechController {
    private TextSpeechInputBoundary textSpeechinteractor;

    public TextSpeechController(TextSpeechInputBoundary textSpeechinteractor) {
        this.textSpeechinteractor = textSpeechinteractor;
    }


    public void execute(String text) {
        TextSpeechInputData inputData = new TextSpeechInputData();
        textSpeechinteractor.execute(inputData);
    }
}

