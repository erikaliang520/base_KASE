package entity.old_history;

//import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordHistoryManager implements HistoryManager {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, String>  wordHistory = new HashMap<>();

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
                    String original_word = String.valueOf(col[headers.get("original_word")]);
                    String translated_word = String.valueOf(col[headers.get("translated_word")]);
                    // Load word history
                    wordHistory.put(original_word, translated_word);
                }
            }
        }
    }

    public void save(String original, String translated){
        wordHistory.put(original, translated);
        this.save();
    }
    public void save(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (Map.Entry<String, String> entry : wordHistory.entrySet()) {
                String line = entry.getKey() + "," + String.join(",", entry.getValue());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTranslatedWord(String original) {
        return wordHistory.getOrDefault(original, "");
    }

    public void clearWordHistory() {
        wordHistory.clear();
        save();  // Save the empty state
    }

}
