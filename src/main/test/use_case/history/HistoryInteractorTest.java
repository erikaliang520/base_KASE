package use_case.history;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryInteractorTest {

    @Test
    void successTest(){

        List<String> saved_list = new ArrayList<String>();

        HistoryDataAccessInterface historyDataAccessInterface = new HistoryDataAccessInterface() {
            @Override
            public void save() {
                saved_list.add("Word word");
            }

            @Override
            public ArrayList<String> get() {
                return (ArrayList<String>) saved_list;
            }
        };

        HistoryOutputBoundary presenter = new HistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(HistoryOutputData data) {
                fail("unexpected reached point for this test uh oh!");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Translation history is currently empty or unavailable.", error);
            }
        };

        HistoryInteractor interactor = new HistoryInteractor(historyDataAccessInterface, presenter);
        interactor.execute();


    }

    @Test
    void failTest(){

        List<String> saved_list = new ArrayList<String>();
        saved_list.add("Word word");

        HistoryDataAccessInterface historyDataAccessInterface = new HistoryDataAccessInterface() {
            @Override
            public void save() {
                saved_list.add("Word word");
            }

            @Override
            public ArrayList<String> get() {
                return (ArrayList<String>) saved_list;
            }
        };

        HistoryOutputBoundary presenter = new HistoryOutputBoundary() {

            @Override
            public void prepareSuccessView(HistoryOutputData data) {
                assertEquals(data.getWordHistory().get(0), "Word word");
            }

            @Override
            public void prepareFailView(String error) {
                fail("unexpected reached point for this test uh oh!");
            }
        };

        HistoryInteractor interactor = new HistoryInteractor(historyDataAccessInterface, presenter);
        interactor.execute();


    }

}