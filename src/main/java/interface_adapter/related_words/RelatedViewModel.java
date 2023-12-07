package interface_adapter.related_words;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RelatedViewModel extends ViewModel {

    public final String TITLE_LABEL = "Translate View";
    public final String INPUT_LABEL = "Enter text";
    public final String DISPLAY_LABEL_OUTPUT = "Translated text";
    public final String DISPLAY_LANGUAGE_LABEL = "Language";
    public final String DISPLAY_ORIGINAL_LABEL = "English";
    public final String DISPLAY_TRANSLATED_LABEL = "French";

    // this button is replaced by however many generated words there are.
    //public static final String GENERATE_RELATED_WORDS_BUTTON_LABEL = "Translate similar words";

    public static final String AUDIO_BUTTON_LABEL = "Listen";
    public static final String HISTORY_BUTTON_LABEL = "History";
    private RelatedState state = new RelatedState();

    public RelatedViewModel() {
        super("generate related words");
    }

    public void setState(RelatedState state) {
        this.state=state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Related Presenter will call to let the ViewModel know to alert the View
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("related", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RelatedState getState() {
        return state;
    }
}
