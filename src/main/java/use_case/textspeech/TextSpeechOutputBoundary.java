package use_case.textspeech;


public interface TextSpeechOutputBoundary {
    void prepareTextSpeechSuccessView(TextSpeechOutputData outputData);

    void prepareTextSpeechFailView(String error);

}
