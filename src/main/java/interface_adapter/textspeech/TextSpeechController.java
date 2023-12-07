package interface_adapter.textspeech;
import use_case.textspeech.TextSpeechInputBoundary;
import use_case.textspeech.TextSpeechInputData;


public class TextSpeechController {
    private TextSpeechInputBoundary interactor;

    public TextSpeechController(TextSpeechInputBoundary interactor) {
        this.interactor = interactor;
    }


    public void execute(String text) {
        TextSpeechInputData inputData = new TextSpeechInputData();
        interactor.execute(inputData);
    }
}

