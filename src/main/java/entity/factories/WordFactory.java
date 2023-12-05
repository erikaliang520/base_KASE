package entity.factories;

import entity.Word;

import java.util.ArrayList;

public interface WordFactory {

    Word createWord(String userWord, String userLanguage);

}
