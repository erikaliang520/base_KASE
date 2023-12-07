package use_case.related_words.related_words_generate;

import entity.Word;
import entity.WordRelated;

public interface RelatedWordDataAccessInterface {
    void save(Word word);
}
