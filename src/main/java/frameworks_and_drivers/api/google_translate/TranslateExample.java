package frameworks_and_drivers.api.google_translate;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.FileInputStream;
import java.io.IOException;

public class TranslateExample {
    public static void main(String[] args) throws IOException {
        // Set up the Translate object using the environment variable
        // String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

        // Couldn't get the environment variable working, so just used direct path, subject to change...

        // Update: changed name of .json file to api_key.json, user should enter api key details client side
        String credentialsPath = "api_key.json";

        Translate translate = TranslateOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(credentialsPath)))
                .build()
                .getService();


        // Text to translate
        String textToTranslate = "Hello";

        // Translate the text
        Translation translation = translate.translate(
                textToTranslate,
                Translate.TranslateOption.sourceLanguage("en"),
                Translate.TranslateOption.targetLanguage("fr")
        );

        // Print the results
        System.out.println("Original Text: " + textToTranslate);
        System.out.println("Translated Text: " + translation.getTranslatedText());
    }
}

