package view;


import interface_adapter.textspeech.TextSpeechViewModel;


public class TextSpeechView {
    private TextSpeechViewModel viewModel;


    public TextSpeechView(TextSpeechViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void displayAudioContent() {
        System.out.println(viewModel.getAudioContent());
    }
}

