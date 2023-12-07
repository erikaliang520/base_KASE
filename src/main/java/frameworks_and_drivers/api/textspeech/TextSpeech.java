package frameworks_and_drivers.api.textspeech;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class TextSpeech {
    public static void main(String... args) throws Exception {
        String credentialsPath = "speech_api.json";

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath));
        TextToSpeechSettings textToSpeechSettings = TextToSpeechSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();

        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(textToSpeechSettings)) {
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder()
                    .setText("Bonjour")
                    .build();


            // Build the voice request
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("fr")
                    .setSsmlGender(SsmlVoiceGender.FEMALE)
                    .build();


            // Select the type of audio file you want returned
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();


            // Perform the text-to-speech request
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);


            // Get the audio data from the response
            ByteString audioData = response.getAudioContent();


            // Write the response to the output file.
            try (OutputStream out = new FileOutputStream("output.mp3")) {
                out.write(audioData.toByteArray());
            }
        }
    }
}

