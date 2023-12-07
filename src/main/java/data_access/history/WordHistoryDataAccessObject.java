package data_access.history;

//import com.opencsv.CSVWriter;

import entity.OriginalWord;
import entity.TranslatedWord;
import entity.Word;
import entity.factories.WordFactory;
import use_case.history.HistoryDataAccessInterface;
import use_case.translate.TranslateDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordHistoryDataAccessObject implements HistoryDataAccessInterface, TranslateDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<Word, Word>  wordHistory = new LinkedHashMap<>();
    private final ArrayList<Word> insertionOrder = new ArrayList<>();
    private WordFactory wordFactoryInput;
    private WordFactory wordFactoryOutput;

    public WordHistoryDataAccessObject(String csvpath,
                                       WordFactory wordFactoryInput,
                                       WordFactory wordFactoryOutput) throws IOException {

        this.wordFactoryInput = wordFactoryInput;
        this.wordFactoryOutput = wordFactoryOutput;

        this.csvFile = new File(csvpath);
        headers.put("original_word", 0);
        headers.put("translated_word", 1);

        if (csvFile.length() == 0) {
            save(); // Creates an empty file if it doesn't already exist
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String originalString = String.valueOf(col[headers.get("original_word")]);
                    String translatedString = String.valueOf(col[headers.get("translated_word")]);
                    // Load word history
                    Word originalWord = this.wordFactoryInput.createWord(originalString, "eng");
                    Word translatedWord = this.wordFactoryOutput.createWord(translatedString, "fr");
                    wordHistory.put(originalWord, translatedWord);
                    insertionOrder.add(originalWord);
                }
            }
        }
    }

    public void save(Word original, Word translated){
        wordHistory.put(original, translated);
        insertionOrder.add(original);
        this.save();
    }
    public void save(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (Word word : insertionOrder) {
                String line = word.getWord() + "," + String.join(",", wordHistory.get(word).getWord());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> get(){
        ArrayList<String> result = new ArrayList<>();

        for (Word word : insertionOrder){
            result.add(0, wordHistory.get(word).getWord()); // add the oldest words to the front
            result.add(0, word.getWord());
        }
        return result; // should return an arraylist of original and translated words from newest to oldest
    }

    public Word getTranslatedWord(Word original) {
        return wordHistory.getOrDefault(original, new TranslatedWord("", "fr"));
    }

    public void clearWordHistory() {
        wordHistory.clear();
        insertionOrder.clear();
        save();  // Save the empty state
    } //is a separate use case

}
