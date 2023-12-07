package use_case.textspeech;


import entity.text_to_speech.TextSpeechService;

import java.io.IOException;

public class TextSpeechInteractor implements TextSpeechInputBoundary {
    private TextSpeechDataAccessInterface repository;
    private TextSpeechOutputBoundary textSpeechPresenter;

    private TextSpeechService textSpeechService;

    public TextSpeechInteractor(TextSpeechDataAccessInterface repository, TextSpeechOutputBoundary presenter, TextSpeechService speechService) {
        this.repository = repository;
        this.textSpeechPresenter = presenter;
        this.textSpeechService = speechService;
    }

    @Override
    public void execute(TextSpeechInputData inputData) throws IOException {
        // Implement your text-to-speech logic here
        // Use the repository to access necessary data
        // Use the presenter to communicate the output
        String word = inputData.getText();
        String audioPath = textSpeechService.performTextToSpeech(word);
        TextSpeechOutputData textSpeechOutputData = new TextSpeechOutputData(audioPath);

        textSpeechPresenter.prepareTextSpeechSuccessView(textSpeechOutputData);
    }
}










