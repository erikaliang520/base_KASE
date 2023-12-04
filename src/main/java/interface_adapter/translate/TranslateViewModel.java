package interface_adapter.translate;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TranslateViewModel extends ViewModel {

    public final String TITLE_LABEL = "Translate View";
    public final String TEXT_LABEL = "Enter text";
    public final String DISPLAY_LABEL = "Translated text";

    // public static final String TRANSLATE_BUTTON_LABEL = "Translate";

    private TranslateState state = new TranslateState();
    public TranslateViewModel() {
        super("translate");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
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
