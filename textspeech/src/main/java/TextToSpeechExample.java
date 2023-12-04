import com.google.cloud.texttospeech.v1.*;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class TextToSpeechExample {

    public static void main(String... args) throws Exception {
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            SynthesisInput input = SynthesisInput.newBuilder()
                    .setText("Buenosera")
                    .build();

            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("it")
                    .setSsmlGender(SsmlVoiceGender.MALE)
                    .build();

            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            try (OutputStream out = new FileOutputStream("output.mp3")) {
                out.write(response.getAudioContent().toByteArray());
            }
        }
    }
}


