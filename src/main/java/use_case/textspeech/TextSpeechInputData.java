package use_case.textspeech;


public class TextSpeechInputData {
    private String text;

    public TextSpeechInputData(String text) {
        this.text = text;
    }


    public void InputData(String text) {
        this.text = text;
    }



    public String getText() {
        return text;
    }
}

