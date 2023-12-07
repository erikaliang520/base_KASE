package use_case.translate;

import data_access.history.WordHistoryDataAccessObject;
import entity.Word;
import entity.factories.OriginalWordFactory;
import entity.factories.TranslatedWordFactory;
import entity.factories.WordFactory;
import interface_adapter.api.google_translate.ExternalTranslateService;
import org.junit.jupiter.api.Test;
import use_case.ports.api.TranslateService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

class TranslateInteractorTest {

    @Test
    void testingTranslationSavingToHistory() throws IOException {
        TranslateInputData translateInputData = new TranslateInputData("human", TRUE);
        TranslateService translateService = new TranslateService() {
            @Override
            public String performTranslate(String word) throws IOException {
                return "humain";
            }
        };

        OriginalWordFactory originalFactory = new OriginalWordFactory();
        TranslatedWordFactory translatedFactory = new TranslatedWordFactory();

        List<String> saveHere = new ArrayList<>();

        TranslateDataAccessInterface wordDataAccessObject = new TranslateDataAccessInterface() {
            @Override
            public void save(Word original, Word translated) {
                saveHere.add("original: "+original.getWord()+". translated: "+translated.getWord());
            }
        };

        TranslateOutputBoundary presenter = new TranslateOutputBoundary() {
            @Override
            public void prepareSuccessView(TranslateOutputData translation) {
                assertEquals(translation.getTranslated().getWord(), "humain");
                assertEquals(saveHere.get(0), "original: human. translated: humain");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case fail is unexpected.");

            }
        };

        TranslateInteractor myInteractor = new TranslateInteractor(translateService, wordDataAccessObject, presenter, originalFactory, translatedFactory);
        myInteractor.execute(translateInputData);

    }

    @Test
    void testingTranslationNoSaveToHistory() throws IOException {
        TranslateInputData translateInputData = new TranslateInputData("human", FALSE);
        TranslateService translateService = new TranslateService() {

            @Override
            public String performTranslate(String word) throws IOException {
                return "humain";
            }
        };

        OriginalWordFactory originalFactory = new OriginalWordFactory();
        TranslatedWordFactory translatedFactory = new TranslatedWordFactory();

        List<String> saveHere1 = new ArrayList<>();

        TranslateDataAccessInterface wordDataAccessObject = new TranslateDataAccessInterface() {
            @Override
            public void save(Word original, Word translated) {
                saveHere1.add("original: " + original.getWord() + ". translated: " + translated.getWord());
            }
        };

        TranslateOutputBoundary presenter = new TranslateOutputBoundary() {
            @Override
            public void prepareSuccessView(TranslateOutputData translation) {
                assertEquals(translation.getTranslated().getWord(), "humain");
                assertEquals(translation.getTranslated().getLanguage(), "French");
                assertTrue(saveHere1.isEmpty());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case fail is unexpected.");

            }
        };

        TranslateInteractor myInteractor = new TranslateInteractor(translateService, wordDataAccessObject, presenter, originalFactory, translatedFactory);
        myInteractor.execute(translateInputData);
    }
}