package entity;

public class TranslatedWordSpeech implements Word{

    private final String word;

    private final String language;

    private final String audioPathFile;

    public TranslatedWordSpeech(String inputWord, String inputLanguage, String audioFile){
        this.word = inputWord;
        this.language = inputLanguage;
        this.audioPathFile = audioFile;
    }

    @Override
    public String getWord() {
        return this.word;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    public String getAudioPathFileName() {
        return this.audioPathFile;
    }

}
