package use_case.related_words.related_words_translate;

import use_case.ports.api.TranslateService;

public class RelatedTranslateInputData {
    final private String originalLanguage; // final cos this is the reference word and language
    final private String originalWord;
    final private TranslateService translateService;

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public TranslateService getTranslateService() {
        return translateService;
    }

    public RelatedTranslateInputData(String originalLanguage, String originalWord, TranslateService translateService){
        this.originalLanguage = originalLanguage;
        this.originalWord = originalWord;
        this.translateService = translateService;
    }
}
