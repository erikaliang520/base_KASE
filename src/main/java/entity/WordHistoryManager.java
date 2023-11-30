package entity;

//import com.opencsv.CSVWriter;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordHistoryManager implements HistoryManager {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<Word, Word>  wordHistory = new HashMap<>();

    public WordHistoryManager(String csvpath) throws IOException {

        csvFile = new File(csvpath);
        headers.put("original_word", 0);
        headers.put("translated_word", 1);

        if (csvFile.length() == 0) {
            save(); // Creates an empty file if it doesn't already exist
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                //String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    OriginalWord originalWord = new OriginalWord(col[0], "eng");
                    TranslatedWord translatedWord = new TranslatedWord(col[1], "fr");
                    // Load word history
                    wordHistory.put(originalWord, translatedWord);
                }
            }
        }
    }

    public void save(Word original, Word translated){
        wordHistory.put(original, translated);
        this.save();
    }
    public void save(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (Map.Entry<Word, Word> entry : wordHistory.entrySet()) {
                String line = entry.getKey().getWord() + "," + String.join(",", entry.getValue().getWord());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Word getTranslatedWord(Word original) {
        return wordHistory.getOrDefault(original, new TranslatedWord("", "fr"));
    }

    public void clearWordHistory() {
        wordHistory.clear();
        save();  // Save the empty state
    }

}
