package interface_adapter.datamuse4J.src.datamuse;

import entity.related_words.RelatedWordsSelectionStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static interface_adapter.datamuse4J.src.datamuse.DatamuseQuery.getWordScores;

public class NounsAdjectiveTopicsMinScoreStrategy implements RelatedWordsSelectionStrategy {

    private int topN;
    private int minScore;
    private String topic;

    public NounsAdjectiveTopicsMinScoreStrategy(int developerTopNum, int developerMinScore, String clientWord, String developerTopic) {
        this.topN = developerTopNum;
        this.minScore = developerMinScore;
        this.topic = developerTopic;
    }

    public static List<String> filterTopWordsWithMinimum(List<WordScore> wordScores, int topN, int minScore) {
        List<WordScore> filteredWords = wordScores.stream()
                .filter(wordScore -> wordScore.getIntScore() >= minScore)
                .sorted(Comparator.comparingInt(WordScore::getIntScore).reversed())
                .limit(topN)
                .collect(Collectors.toList());

        ArrayList<String> result = new ArrayList<>();
        for (WordScore wordScore : filteredWords) {
            result.add(wordScore.getWord());
        }
        return result;
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
        // will return the elements that are >= to the minScore, limited by the topNumber desired
        else{
            return filterTopWordsWithMinimum(wordScores, this.topN, this.minScore);
        }

    }



}
