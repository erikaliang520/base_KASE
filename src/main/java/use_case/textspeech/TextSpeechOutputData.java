package use_case.textspeech;


public class TextSpeechOutputData {
    private String audioContent;


    public TextSpeechOutputData(String audioContent) {
        this.audioContent = audioContent;
    }



    public String getAudioContent() {
        return audioContent;
    }
}

