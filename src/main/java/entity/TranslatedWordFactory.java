package entity;

public interface TranslatedWordFactory {

    TranslatedWord create(String input_word, String output_word, String audio_path, String language_translate);


}
