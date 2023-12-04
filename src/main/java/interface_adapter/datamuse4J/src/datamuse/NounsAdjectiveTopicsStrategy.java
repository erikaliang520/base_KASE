package interface_adapter.datamuse4J.src.datamuse;

import entity.related_words.RelatedWordsSelectionStrategy;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import static interface_adapter.datamuse4J.src.datamuse.DatamuseQuery.getWordScores;

public class NounsAdjectiveTopicsStrategy implements RelatedWordsSelectionStrategy {
    private int topN;
    private String topic;

    public NounsAdjectiveTopicsStrategy(int developerTopNum, String developerTopic) {
        this.topN = developerTopNum;
        this.topic = developerTopic;
    }

    public static List<String> filterTopWordsWithMinimum(List<WordScore> wordScores, int topN, int minScore) {
        List<WordScore> filteredWords = wordScores.stream()
                .filter(wordScore -> wordScore.getIntScore() >= minScore)
                .sorted(Comparator.comparingInt(WordScore::getIntScore).reversed())
                .limit(topN)
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        for (WordScore wordScore : filteredWords) {
            result.add(wordScore.getWord());
        }
        return result;
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
        String jsonString = dQuery.findNounsByTopicRelativity(word, this.topic);

        // returns a parsed string, call method to return a list of strings of topN elements
        List<WordScore> wordScores = getWordScores(jsonString);

        // if list of WordScore is empty, can't return top elements, so just return empty string list
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


