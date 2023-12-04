package interface_adapter.related_words;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class RelatedViewModel extends ViewModel {

    public final String TITLE_LABEL = "Translate View";
    public final String TEXT_LABEL = "Enter text";
    public final String DISPLAY_LABEL = "Translated text";
    public final ArrayList<String> GENERATED_TEXT_LABELS;

    public RelatedViewModel(ArrayList<String> generatedTextLabels) {
        super();
        GENERATED_TEXT_LABELS = generatedTextLabels;
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
