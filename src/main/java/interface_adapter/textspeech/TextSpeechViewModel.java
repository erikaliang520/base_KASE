package interface_adapter.textspeech;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TextSpeechViewModel extends ViewModel {

    private TextSpeechState state = new TextSpeechState();

    public TextSpeechViewModel() {
        super("textspeech");
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
        if(state == null) {
            throw new IllegalArgumentException("state cannot be null");
        }
        this.state = state;
    }
}



