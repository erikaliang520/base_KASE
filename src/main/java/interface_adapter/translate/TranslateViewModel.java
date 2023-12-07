package interface_adapter.translate;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TranslateViewModel extends ViewModel {

    public final String TITLE_LABEL = "Translate View";
    public final String INPUT_LABEL = "Enter text"; // this will change when user types , still static?
    public final String DISPLAY_LABEL_OUTPUT = "Translated text";
    public final String DISPLAY_LANGUAGE_LABEL = "Language";
    public final String DISPLAY_ORIGINAL_LABEL = "English"; // future feature, would give client option to choose different languages
    public final String DISPLAY_TRANSLATED_LABEL = "French";
    // Generate related words UseCase:
    public static final String GENERATE_RELATED_WORDS_BUTTON_LABEL = "Translate similar words";
    // Text-to-speech UseCase:
    public static final String AUDIO_BUTTON_LABEL = "Listen";
    // History UseCase:
    public static final String HISTORY_BUTTON_LABEL = "History";



    // public static final String TRANSLATE_BUTTON_LABEL = "Translate";

    private TranslateState state = new TranslateState();
    public TranslateViewModel() {
        super("translate");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("translate", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public TranslateState getState() {
        return state;
    }

    public void setState(TranslateState state) {
        this.state = state;
    }
}
