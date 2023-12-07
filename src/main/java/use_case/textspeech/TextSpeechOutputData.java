package use_case.textspeech;


public class TextSpeechOutputData {
    private String audioPath;


    public TextSpeechOutputData(String audioPath) {
        this.audioPath = audioPath;
    }



    public String getAudioPath() {
        return audioPath;
    }
}

