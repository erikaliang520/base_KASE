package entity.factories;

import entity.Word;

public interface TranslatedSpeechWordFactory {

    public Word createWord(String userWord, String userLanguage, String audioFile);
}
