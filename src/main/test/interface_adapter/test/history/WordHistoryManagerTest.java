package interface_adapter.test.history;
import entity.OriginalWord;
import entity.TranslatedWord;
import entity.Word;
import data_access.history.WordHistoryDataAccessObject;

import java.io.IOException;

import entity.factories.OriginalWordFactory;
import entity.factories.TranslatedWordFactory;
import entity.factories.WordFactory;
import org.junit.jupiter.api.BeforeEach;
import use_case.history.HistoryDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordHistoryManagerTest {
    private Word originalWord1 = new OriginalWord("apple", "eng");
    private Word originalWord2 = new OriginalWord("cat", "eng");
    private Word translatedWord1 = new TranslatedWord("pomme", "fr");
    private Word translatedWord2 = new TranslatedWord("chat", "fr");

    @BeforeEach
    void setUp() {
        // Adjust the file path based on your project structure
        String csvFilePath = "main/word_small.csv";

        OriginalWordFactory originalWordFactory = new OriginalWordFactory();
        TranslatedWordFactory translatedWordFactory = new TranslatedWordFactory();

//        try {
//            this.historyManager = new WordHistoryDataAccessObject(csvFilePath,
//                    originalWordFactory,
//                    translatedWordFactory);
//        } catch (IOException e) {
//            e.printStackTrace(); // Handle the exception appropriately
//            System.out.println("failed");
//        }

        // Initialize some words for testing
        originalWord1 = new OriginalWord("apple", "eng");
        originalWord2 = new OriginalWord("cat", "eng");
        translatedWord1 = new TranslatedWord("pomme", "fr");
        translatedWord2 = new TranslatedWord("chat", "fr");
    }

    @org.junit.Test
    public void saveAndRetrieveWord() throws IOException {
        String csvFilePath = "/Users/erikaliang/IdeaProjects/base_KASE/src/main/word_file_small.csv" ;
        OriginalWordFactory originalWordFactory = new OriginalWordFactory();
        TranslatedWordFactory translatedWordFactory = new TranslatedWordFactory();
        WordHistoryDataAccessObject historyManager = new WordHistoryDataAccessObject(csvFilePath,
                originalWordFactory,
                translatedWordFactory);

        historyManager.save(originalWord1, translatedWord1);
        Word retrievedTranslation = historyManager.getTranslatedWord(originalWord1);
        assertEquals(translatedWord1.getWord(), retrievedTranslation.getWord());
        assertEquals(translatedWord1.getLanguage(), retrievedTranslation.getLanguage());
    }

    @org.junit.Test
    public void saveAndClearHistory() throws IOException{
        String csvFilePath = "/Users/erikaliang/IdeaProjects/base_KASE/src/main/word_file_small.csv" ;
        OriginalWordFactory originalWordFactory = new OriginalWordFactory();
        TranslatedWordFactory translatedWordFactory = new TranslatedWordFactory();
        WordHistoryDataAccessObject historyManager = new WordHistoryDataAccessObject(csvFilePath,
                originalWordFactory,
                translatedWordFactory);

        historyManager.save(originalWord2, translatedWord2);
        historyManager.clearWordHistory();
        Word retrievedTranslation = historyManager.getTranslatedWord(originalWord2);
        assertEquals("", retrievedTranslation.getWord()); // Empty string as default for not found
    }

    @org.junit.Test
    public void saveMultipleWords() throws IOException{
        String csvFilePath = "/Users/erikaliang/IdeaProjects/base_KASE/src/main/word_file_small.csv" ;
        OriginalWordFactory originalWordFactory = new OriginalWordFactory();
        TranslatedWordFactory translatedWordFactory = new TranslatedWordFactory();
        WordHistoryDataAccessObject historyManager = new WordHistoryDataAccessObject(csvFilePath,
                originalWordFactory,
                translatedWordFactory);

        historyManager.save(originalWord1, translatedWord1);
        historyManager.save(originalWord2, translatedWord2);
        Word retrievedTranslation1 = historyManager.getTranslatedWord(originalWord1);
        Word retrievedTranslation2 = historyManager.getTranslatedWord(originalWord2);
        assertEquals(translatedWord1.getWord(), retrievedTranslation1.getWord());
        assertEquals(translatedWord2.getWord(), retrievedTranslation2.getWord());
    }

    @org.junit.Test
    public void retrieveNonExistentWord() throws IOException{
        String csvFilePath = "/Users/erikaliang/IdeaProjects/base_KASE/src/main/word_file_small.csv" ;
        OriginalWordFactory originalWordFactory = new OriginalWordFactory();
        TranslatedWordFactory translatedWordFactory = new TranslatedWordFactory();
        WordHistoryDataAccessObject historyManager = new WordHistoryDataAccessObject(csvFilePath,
                    originalWordFactory,
                    translatedWordFactory);

        Word nonExistentWord = new OriginalWord("nonexistent", "eng");
        Word retrievedTranslation = historyManager.getTranslatedWord(nonExistentWord);
        assertEquals("", retrievedTranslation.getWord()); // Non-existent word should return empty string
    }

}
