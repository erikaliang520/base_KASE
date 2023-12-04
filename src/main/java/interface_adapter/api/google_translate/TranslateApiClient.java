package interface_adapter.api.google_translate;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TranslateApiClient {
    public String Translate(String word) throws IOException {
        String credentialsPath = "api_key.json";

        com.google.cloud.translate.Translate translate = TranslateOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(credentialsPath)))
                .build()
                .getService();


        // Text to translate

        // Translate the text
        Translation translation = translate.translate(
                word,
                com.google.cloud.translate.Translate.TranslateOption.sourceLanguage("en"),
                com.google.cloud.translate.Translate.TranslateOption.targetLanguage("fr")
        );

        // Print the results
//        System.out.println("Original Text: " + word);
//        System.out.println("Translated Text: " + translation.getTranslatedText());

        return translation.getTranslatedText();
    }
}
