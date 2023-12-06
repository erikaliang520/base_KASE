package view;


import interface_adapter.textspeech.TextSpeechController;
import interface_adapter.textspeech.TextSpeechViewModel;


public class TextSpeechView {
    private TextSpeechViewModel viewModel;

    private final TextSpeechController textSpeechController;
    public TextSpeechView(TextSpeechViewModel viewModel, TextSpeechController textSpeechController) {
        this.viewModel = viewModel;
        this.textSpeechController = textSpeechController;
    }
//    public void displayAudioContent() {
//        System.out.println(viewModel.getAudioContent());
//    }
}

