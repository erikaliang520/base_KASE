package interface_adapter.api.datamuse4J.src.datamuse;

import entity.related_words.RelatedWordsSelectionStrategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SynonymStrategy implements RelatedWordsSelectionStrategy {

    private final int topN;

    public SynonymStrategy(int topN) {
        this.topN = topN;
    }

    private static List<String> getTopNStringAttributes(List<WordScore> wordScores, int topN) {
        return wordScores.stream()
                .limit(topN)
                .map(WordScore::getWord)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> selectTopWordsStrategy(String word) {
        DatamuseQuery dQuery = new DatamuseQuery();

        // find the related words based on word and given strategy
        String jsonString = dQuery.findSynonyms(word);

        // returns a parsed string, call method to return a list of strings of topN elements
        List<WordScore> wordScores = DatamuseQuery.getWordScores(jsonString);

        // if list of WordScore is empty, can't return top elements, so just return empty string list
        assert wordScores != null;
        if (wordScores.isEmpty()){
            return Collections.emptyList();
        }
        // if the top number of score they want is bigger or equal
        // to what's actually there, return the list of words as original
        else if (wordScores.size() <= this.topN) {
            return wordScores.stream()
                    .map(WordScore::getWord)
                    .collect(Collectors.toList());
        }

        // otherwise, only return the top word element words
        else if (wordScores.size() > this.topN) {
            return getTopNStringAttributes(wordScores, this.topN);
        }

        // catch all case
        return Collections.emptyList();
    }
}
