package use_case.translate;

import entity.Word;

public interface TranslateDataAccessInterface {
    void save(Word original, Word translated);
}
