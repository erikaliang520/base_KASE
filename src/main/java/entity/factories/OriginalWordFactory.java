package entity.factories;

import entity.Word;
import entity.OriginalWord;
import entity.OriginalWordRelated;
import java.util.List;

public class OriginalWordFactory implements WordFactory, OriginalRelatedWordFactory{
    @Override
    public Word createWord(String userWord, String userLanguage) {
        return new OriginalWord(userWord, userLanguage);
    }

    @Override
    public Word createWord(String userWord, String userLanguage, List<String> computedRelatedWords) {
        return new OriginalWordRelated(userWord, userLanguage, computedRelatedWords);
    }

}
