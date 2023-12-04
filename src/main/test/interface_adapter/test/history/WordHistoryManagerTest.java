package interface_adapter.test.history;
import entity.OriginalWord;
import entity.TranslatedWord;
import entity.Word;
import entity.history.WordHistoryManager;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordHistoryManagerTest {
    private WordHistoryManager historyManager;
    private Word originalWord1;
    private Word originalWord2;
    private Word translatedWord1;
    private Word translatedWord2;

    @BeforeEach
    void setUp() {
        // Adjust the file path based on your project structure
        String csvFilePath = "path/to/word_small.csv";

        try {
            historyManager = new WordHistoryManager(csvFilePath);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        // Initialize some words for testing
        originalWord1 = new OriginalWord("apple", "eng");
        originalWord2 = new OriginalWord("cat", "eng");
        translatedWord1 = new TranslatedWord("pomme", "fr");
        translatedWord2 = new TranslatedWord("chat", "fr");
    }

    @org.junit.Test
    public void saveAndRetrieveWord() {
        historyManager.save(originalWord1, translatedWord1);
        Word retrievedTranslation = historyManager.getTranslatedWord(originalWord1);
        assertEquals(translatedWord1.getWord(), retrievedTranslation.getWord());
        assertEquals(translatedWord1.getLanguage(), retrievedTranslation.getLanguage());
    }

    @org.junit.Test
    public void saveAndClearHistory() {
        historyManager.save(originalWord2, translatedWord2);
        historyManager.clearWordHistory();
        Word retrievedTranslation = historyManager.getTranslatedWord(originalWord2);
        assertEquals("", retrievedTranslation.getWord()); // Empty string as default for not found
    }

    // Add more test cases as needed to cover different scenarios
}
