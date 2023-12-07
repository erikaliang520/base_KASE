package frameworks_and_drivers.api.related_words;

import entity.OriginalWord;
import entity.Word;
import interface_adapter.related_words.RelatedState;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RelatedStateTest { // TODO simple test where just the state copy is the state copy
    RelatedState copy = new RelatedState();

    @Test
    public void getAndSetOriginalTextTest(){
        copy.setOriginalText("Hello");
        assertEquals("Hello", copy.getOriginalText());
    }

    @Test
    public void getAndSetRelatedWordsGeneratedWordsTest(){
        String word1 = "cat";
        String word2 = "kitten";
        List<String> lst = new ArrayList<>();
        lst.add(word1);
        lst.add(word2);
        copy.setRelatedWordsGenerated(lst);
        assertEquals(lst, copy.getRelatedWordsGenerated());
    }

    @Test
    public void getAndSetRelatedWordsErrorTest(){
        String error = "Error";
        copy.setRelatedWordsError(error);
        assertEquals(error, copy.getGeneratedWordsError());
    }

    @Test
    public void getAndSetTranslatedTextTest(){
        copy.setTranslatedText("Bonjour");
        assertEquals("Bonjour", copy.getTranslatedText());
    }

    @Test
    public void toStringTest(){
        String error = "Error";
        String word1 = "cat";
        String word2 = "kitten";
        List<String> lst = new ArrayList<>();
        lst.add(word1);
        lst.add(word2);

        copy.setRelatedWordsError(error);
        copy.setTranslatedText("Bonjour");
        copy.setRelatedWordsGenerated(lst);

        assertEquals("RelatedState{" +
                "original text='" + copy.getOriginalText() + '\'' +
                ", translated text='" + copy.getTranslatedText() + '\'' +
                ", generated related words to translate next='" + copy.getRelatedWordsGenerated().toString() + '\'' +
                '}' , copy.toString());

    }
}