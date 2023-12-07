package entity.factories;

import entity.Word;
import entity.WordRelated;

import java.util.List;

public interface OriginalRelatedWordFactory {
    public Word createWord(String userWord, String userLanguage, List<String> computedRelatedWords);
}
