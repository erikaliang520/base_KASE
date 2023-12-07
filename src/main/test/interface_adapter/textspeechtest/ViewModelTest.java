package interface_adapter.textspeechtest;

import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import interface_adapter.textspeech.TextSpeechViewModel;
import interface_adapter.textspeech.TextSpeechState;

public class ViewModelTest {

    @Test
    public void testDefaultConstructor() {
        TextSpeechViewModel viewModel = new TextSpeechViewModel();

        assertEquals("", viewModel.getState().getOriginalText());
        assertEquals("", viewModel.getState().getSpokenText());
    }

    @Test
    public void testSetState() {
        TextSpeechViewModel viewModel = new TextSpeechViewModel();
        TextSpeechState state = new TextSpeechState();
        state.setOriginalText("Test Original Text");
        state.setSpokenText("Test Spoken Text");

        viewModel.setState(state);

        assertEquals("Test Original Text", viewModel.getState().getOriginalText());
        assertEquals("Test Spoken Text", viewModel.getState().getSpokenText());
    }

    @Test
    public void testSetStateNull() {
        TextSpeechViewModel viewModel = new TextSpeechViewModel();

        assertThrows(IllegalArgumentException.class, () -> viewModel.setState(null));
    }

    @Test
    public void testFirePropertyChanged() {
        TextSpeechViewModel viewModel = new TextSpeechViewModel();
        TextSpeechState state = new TextSpeechState();
        state.setOriginalText("Test Original Text");
        state.setSpokenText("Test Spoken Text");

        viewModel.setState(state);

        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                TextSpeechState newState = (TextSpeechState) evt.getNewValue();

                assertEquals("Test Original Text", newState.getOriginalText());
                assertEquals("Test Spoken Text", newState.getSpokenText());
            }
        });

        viewModel.firePropertyChanged();
    }
}
