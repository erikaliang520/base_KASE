package interface_adapter.history;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HistoryViewModel extends ViewModel {

    private HistoryState state = new HistoryState();

    public final String BACK_BUTTON_LABEL = "Back";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public HistoryViewModel(){
        super("history");
    }

    public void setState(HistoryState state) {this.state = state;}
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public HistoryState getState(){return state;}
}
