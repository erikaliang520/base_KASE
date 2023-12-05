package use_case.related_words.related_words_translate;

import entity.Word;

public interface RelatedTranslateWordDataAccessInterface {
    void save(Word word);

    Word get(String originalWord);


}
