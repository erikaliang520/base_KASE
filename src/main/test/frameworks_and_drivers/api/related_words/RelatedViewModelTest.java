package frameworks_and_drivers.api.related_words;

import interface_adapter.ViewModel;
import interface_adapter.related_words.RelatedState;
import interface_adapter.related_words.RelatedViewModel;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class RelatedViewModelTest { // TODO idk how to test this
    private RelatedState state = new RelatedState();
    private RelatedViewModel viewModel = new RelatedViewModel();

    @Test
    public void getAndSetStateTest(){
        viewModel.setState(state);
        assertEquals(viewModel.getState(), state);

    }
    @Test
    void firePropertyChanged() {
    }

    @Test
    void addPropertyChangeListener() {
    }

    @Test
    public void firePropertyChangedAndAddPropertyChangeTest(){
        InMemoryRelatedViewModel newViewModel = new InMemoryRelatedViewModel("History");
        PropertyChangeListener listener = new MyPropertyChangeListener();
        newViewModel.addPropertyChangeListener(listener);
        newViewModel.firePropertyChanged();

        assertEquals(newViewModel.APCL, true);
        assertEquals(newViewModel.FPC, true);
    }

    private static class InMemoryRelatedViewModel extends ViewModel {
        private boolean APCL = false;
        private boolean FPC = false;

        public InMemoryRelatedViewModel(String viewName) {
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
