package use_case.history;

import entity.Word;

import java.util.ArrayList;

public interface HistoryDataAccessInterface {

    void save(Word word1, Word word2);

    ArrayList<String> get();
}
