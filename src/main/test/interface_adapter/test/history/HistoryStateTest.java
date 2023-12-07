package interface_adapter.test.history;

import entity.OriginalWord;
import entity.TranslatedWord;
import entity.Word;
import interface_adapter.history.HistoryState;
import org.junit.Test;
import use_case.history.HistoryOutputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryStateTest {
    private HistoryState historyState = new HistoryState();
    @Test
    public void getAndSetWordHistoryTest(){
        Word or1 = new OriginalWord("apple", "eng");
        Word or2 = new OriginalWord("cat", "eng");
        Word tr1 = new TranslatedWord("pomme", "fr");
        Word tr2 = new TranslatedWord("chat", "fr");
        ArrayList<String> exampleWord = new ArrayList<>();
        exampleWord.add(or1.getWord());
        exampleWord.add(tr1.getWord());
        exampleWord.add(or2.getWord());
        exampleWord.add(tr2.getWord());

        historyState.setWordHistory(exampleWord);

        HistoryOutputData output = new HistoryOutputData(exampleWord);
        assertEquals(historyState.getWordHistory(), exampleWord);
    }

    @Test
    public void setHistoryErrorTest(){
        String error = "Error";
        historyState.setHistoryError(error);
        assertEquals(historyState.historyError, error);

    }
}
