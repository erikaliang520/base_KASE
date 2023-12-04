package entity;

public class OriginalWord implements Word{

    private final String word;

    private final String language;

    public OriginalWord(String userWord, String userLanguage){
        this.word = userWord;
        this.language = userLanguage;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public String getLanguage() {
        return language;
    }

}
