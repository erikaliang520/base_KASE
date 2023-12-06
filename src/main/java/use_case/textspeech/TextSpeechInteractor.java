package use_case.textspeech;


public class TextSpeechInteractor implements TextSpeechInputBoundary {
    private TextSpeechDataAccessInterface repository;
    private TextSpeechOutputBoundary presenter;

    public TextSpeechInteractor(TextSpeechDataAccessInterface repository, TextSpeechOutputBoundary presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(TextSpeechInputData inputData) {
        // Implement your text-to-speech logic here
        // Use the repository to access necessary data
        // Use the presenter to communicate the output
    }
}










