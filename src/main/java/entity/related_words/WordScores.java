package entity.related_words;

// Interface of Word and Attributes with Score compnent, could be reused depenidng on what API is used

public interface WordScores extends WordAndAttributes {

    public String getScore();

}
