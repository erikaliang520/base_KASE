package entity;

public class TranslatedWord implements Word {

    private final String word;

    private final String language;

    //private final String audioPathFile;

    TranslatedWord(String inputWord, String inputLanguage){
        this.word = inputWord;
        this.language = inputLanguage;
        //this.audioPathFile = audioFile;
    }

    @Override
    public String getWord() {
        return this.word;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    //public String getAudioPathFileName() {
    //    return this.audioPathFile;
    //}
}
