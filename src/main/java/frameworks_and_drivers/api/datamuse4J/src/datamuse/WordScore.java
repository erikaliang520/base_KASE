package frameworks_and_drivers.api.datamuse4J.src.datamuse;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.related_words.WordAndAttributes;
import entity.related_words.WordScores;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WordScore implements WordScores {
    @JsonProperty("word")
    private String word;

    @JsonProperty("score")
    private int score;

    // You can also override toString() for better printing or debugging
    @Override
    public String toString() {
        return "WordScore{" +
                "word='" + word + '\'' +
                ", score=" + score +
                '}';
    }

    public String getWord() {
        return this.word;
    }

    public String getScore() {
        return String.valueOf(this.score);
    }

    public int getIntScore() {
        return this.score;
    }


//    public List<String> selectTopWords(WordsAndAttributesList listedWords, int topN) {
//        return listedWords.get(0).selectTopWords(wordScores, topN);
//    }

}