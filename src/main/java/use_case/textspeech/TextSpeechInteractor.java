package use_case.textspeech;


public class TextSpeechInteractor {
    private TextSpeechDataAccessInterface repository;
    private TextSpeechOutputBoundary presenter;


    public TextSpeechInteractor(TextSpeechDataAccessInterface repository, TextSpeechOutputBoundary
            presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }






}

