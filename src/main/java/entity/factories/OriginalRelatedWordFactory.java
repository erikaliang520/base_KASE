package entity.factories;

import entity.Word;

import java.util.List;

public interface OriginalRelatedWordFactory {
    public Word createWord(String userWord, String userLanguage, List<String> computedRelatedWords);
}
