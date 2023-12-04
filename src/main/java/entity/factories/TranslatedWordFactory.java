package entity.factories;

import entity.TranslatedWord;
import entity.TranslatedWordSpeech;
import entity.Word;

public class TranslatedWordFactory implements WordFactory {

    @Override
    public Word createWord(String userWord, String userLanguage) {
        return new TranslatedWord(userWord, userLanguage);
    }

    public Word createWord(String userWord, String userLanguage, String audioFile) {
        return new TranslatedWordSpeech(userWord, userLanguage, audioFile);
    }
}