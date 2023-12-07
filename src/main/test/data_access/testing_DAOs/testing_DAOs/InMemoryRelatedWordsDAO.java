package data_access.testing_DAOs.testing_DAOs;

import entity.Word;
import entity.WordRelated;
import use_case.related_words.related_words_generate.RelatedWordDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRelatedWordsDAO implements RelatedWordDataAccessInterface {

    private final Map<String, ArrayList<String>> relatedWords = new HashMap<>();

    @Override
    public void save(WordRelated word) {
        relatedWords.put(word.getWord(), (ArrayList<String>) word.getRelatedWords());

    }
}
