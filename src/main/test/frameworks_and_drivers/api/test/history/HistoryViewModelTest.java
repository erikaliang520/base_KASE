package frameworks_and_drivers.api.test.history;

import interface_adapter.ViewModel;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryViewModelTest {
    private HistoryState state = new HistoryState();
    private HistoryViewModel viewModel = new HistoryViewModel();

    @Test
    public void getAndSetStateTest(){
        viewModel.setState(state);
        assertEquals(viewModel.getState(), state);

    }

    @Test
    public void firePropertyChangedAndAddPropertyChangeTest(){
        InMemoryHistoryViewModel newViewModel = new InMemoryHistoryViewModel("History");
        PropertyChangeListener listener = new MyPropertyChangeListener();
        newViewModel.addPropertyChangeListener(listener);
        newViewModel.firePropertyChanged();

        assertEquals(newViewModel.APCL, true);
        assertEquals(newViewModel.FPC, true);
    }

    private static class InMemoryHistoryViewModel extends ViewModel {
        private boolean APCL = false;
        private boolean FPC = false;

        public InMemoryHistoryViewModel(String viewName) {
            super(viewName);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener){
            APCL = true;
        }

        public void firePropertyChanged(){
            FPC = true;
        }


    }

    public class MyPropertyChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            // Handle the property change event here
            String propertyName = evt.getPropertyName();
            Object oldValue = evt.getOldValue();
            Object newValue = evt.getNewValue();

            System.out.println("Property changed: " + propertyName);
            System.out.println("Old value: " + oldValue);
            System.out.println("New value: " + newValue);
        }
    }


}
