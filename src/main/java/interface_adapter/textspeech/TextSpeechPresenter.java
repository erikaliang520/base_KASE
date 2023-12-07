package interface_adapter.textspeech;

import interface_adapter.ViewManagerModel;
import use_case.textspeech.TextSpeechOutputBoundary;
import use_case.textspeech.TextSpeechOutputData;

public class TextSpeechPresenter implements TextSpeechOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private final TextSpeechViewModel textSpeechViewModel;

    public TextSpeechPresenter(ViewManagerModel viewManagerModel,
                               TextSpeechViewModel textSpeechViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.textSpeechViewModel = textSpeechViewModel;
    }

    @Override
    public void prepareTextSpeechSuccessView(TextSpeechOutputData speechData) {
        TextSpeechState textSpeechState = textSpeechViewModel.getState();
        textSpeechState.setSpokenText(speechData.getAudioPath());
        this.textSpeechViewModel.setState(textSpeechState);
        this.textSpeechViewModel.firePropertyChanged();

        // If any view depends on the state of the TextSpeechViewModel,
        // it would be necessary to notify them about the state change.
        // If not, this line can be omitted.
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareTextSpeechFailView(String error) {
        // Handle failure view preparation logic if needed
    }
}


