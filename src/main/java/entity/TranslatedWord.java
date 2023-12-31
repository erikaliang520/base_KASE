package entity;

public class TranslatedWord implements Word {

    private final String word;

    private final String language;

    public TranslatedWord(String inputWord, String inputLanguage){
        this.word = inputWord;
        this.language = inputLanguage;
    }

    @Override
    public String getWord() {
        return this.word;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

}
