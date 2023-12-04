package entity.related_words;

import java.util.List;

public interface RelatedWordsSelectionStrategy {
    List<String> selectTopWordsStrategy(String word);
}