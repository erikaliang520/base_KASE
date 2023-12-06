package interface_adapter.textspeech;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TextSpeechViewModel {

    private TextSpeechState state = new TextSpeechState();

    public TextSpeechViewModel() {
        super();
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public TextSpeechState getState() {
        return state;
    }

    public void setState(TextSpeechState state) {
        this.state = state;
    }
}


