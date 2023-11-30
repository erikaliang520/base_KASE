package history;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LanguageHistoryManager implements HistoryManager{
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, String>  languageHistory = new HashMap<>();

    public LanguageHistoryManager(String csvpath) throws IOException {

        csvFile = new File(csvpath);
        headers.put("original_language", 0);
        headers.put("translated_language", 1);

        if (csvFile.length() == 0) {
            save(); // Creates an empty file if it doesn't already exist
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                //String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String original_language = String.valueOf(col[headers.get("original_language")]);
                    String translated_language = String.valueOf(col[headers.get("translated_language")]);
                    // Load word history
                    languageHistory.put(original_language, translated_language);
                }
            }
        }
    }
    // There is an existing string
    public void save(String original, String translated){
        languageHistory.put(original, translated);
        this.save();
    }
    public void save(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (Map.Entry<String, String> entry : languageHistory.entrySet()) {
                String line = entry.getKey() + "," + String.join(",", entry.getValue());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTranslatedLanguage(String original) {
        return languageHistory.getOrDefault(original, "");
    }

    public void clearWordHistory() {
        languageHistory.clear();
        save();  // Save the empty state
    }

}
