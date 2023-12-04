package data_access.history;

import entity.Word;
import use_case.history.HistoryDataAccessInterface;
import use_case.related_words.RelatedWordDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RelatedWordsHistoryDataAccessObject implements HistoryDataAccessInterface, RelatedWordDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, ArrayList<String>> relatedWordsHistory = new HashMap<>();

    public RelatedWordsHistoryDataAccessObject(String csvpath) throws IOException {

        csvFile = new File(csvpath);
        headers.put("original_word", 0);
        headers.put("related_words", 1);

        if (csvFile.length() == 0) {
            save(); // Creates an empty file if it doesn't already exist
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                //String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String original_word = String.valueOf(col[headers.get("original_word")]);
                    ArrayList<String> related_words = new ArrayList<>();
                    if (col.length > headers.get("related_words")) {
                        for (int i = headers.get("related_words"); i < col.length; i++) {
                            related_words.add(col[i]);
                        }
                        // Load word history
                        relatedWordsHistory.put(original_word, related_words);
                    }
                }
            }
        }
    }
    public void save(String original, ArrayList < String > relatedWords){
        relatedWordsHistory.put(original, relatedWords);
        this.save();
    }
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (Map.Entry<String, ArrayList<String>> entry : relatedWordsHistory.entrySet()) {
                String line = entry.getKey() + "," + String.join(",", entry.getValue());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<String> getRelatedWords(String original){
        return relatedWordsHistory.getOrDefault(original, new ArrayList<>());
    }

    public void clearRelatedWordsHistory(){
        relatedWordsHistory.clear();
        save();
    }

    @Override
    public void save(Word word) {

    }
}

