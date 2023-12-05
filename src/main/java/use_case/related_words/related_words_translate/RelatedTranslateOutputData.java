package use_case.related_words.related_words_translate;

import use_case.ports.api.TranslateService;

import java.io.IOException;

public class RelatedTranslateOutputData {
    final private String originalLanguage; // final cos this is the reference word and language
    final private String originalWord;
    final private String translatedLanguage; // should be final, cos if we wanted a new language, it would create a new object
    private String translatedWord; // not final, based on how we choose the translation service
    private TranslateService translationService; // can be changed if we change the api

    public RelatedTranslateOutputData(String originalLanguage, String originalWord, String translatedLanguage, TranslateService translationService) throws IOException {
        this.originalLanguage = originalLanguage;
        this.originalWord = originalWord;
        this.translatedLanguage = translatedLanguage;
        this.translationService = translationService;

        // use given translation service to compute output data of translated word
        this.translatedWord = translationService.performTranslate(this.originalWord);
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public String getTranslatedLanguage() {
        return translatedLanguage;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public TranslateService getTranslationService() {
        return translationService;
    }

    public void setNewTranslationService(TranslateService newTranslationService) throws IOException {
        this.translationService = newTranslationService;
        this.translatedWord = newTranslationService.performTranslate(this.getOriginalWord());
    }


}
