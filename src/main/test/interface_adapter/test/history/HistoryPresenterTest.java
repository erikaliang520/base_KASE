package interface_adapter.test.history;

import entity.OriginalWord;
import entity.TranslatedWord;
import entity.Word;
import entity.factories.OriginalRelatedWordFactory;
import entity.factories.OriginalWordFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.translate.TranslateViewModel;
import org.junit.Test;
import use_case.history.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryPresenterTest {
    TranslateViewModel translateViewModel = new TranslateViewModel();
    HistoryViewModel historyViewModel = new HistoryViewModel();
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    HistoryOutputData historyOutputData;
    HistoryOutputBoundary historyPresenter;

    @Test
    public void successTest(){
        HistoryDataAccessInterface newDAO = new InMemoryHistoryDAO();
        HistoryOutputBoundary successPresenter = new InMemoryHistoryPresenter();
        HistoryInputData historyInputData = new HistoryInputData();

        historyPresenter = successPresenter;

        OriginalRelatedWordFactory factory = new OriginalWordFactory();
        HistoryInputBoundary interactor = new HistoryInteractor(newDAO, successPresenter);

        interactor.execute();
        historyOutputData = new HistoryOutputData(newDAO.get());

    }

    @Test
    public void prepareSuccessView(){
        Word or1 = new OriginalWord("apple", "eng");
        Word or2 = new OriginalWord("cat", "eng");
        Word tr1 = new TranslatedWord("pomme", "fr");
        Word tr2 = new TranslatedWord("chat", "fr");
        ArrayList<String> exampleWord = new ArrayList<>();
        exampleWord.add(or1.getWord());
        exampleWord.add(tr1.getWord());
        exampleWord.add(or2.getWord());
        exampleWord.add(tr2.getWord());

        HistoryOutputData output = new HistoryOutputData(exampleWord);
        InMemoryHistoryPresenter successPresenter = new InMemoryHistoryPresenter();

        successPresenter.prepareSuccessView(historyOutputData);
//        assertEquals(output.getWordHistory().get(0), "apple");
//        assertEquals(output.getWordHistory().get(1), "pomme");
//        assertEquals(output.getWordHistory().get(2), "cat");
//        assertEquals(output.getWordHistory().get(3), "chat");
        assertTrue(successPresenter.isPSVCalled());
    }




    @Test
    public void prepareFailView(){
        Word or1 = new OriginalWord("apple", "eng");
        Word or2 = new OriginalWord("cat", "eng");
        Word tr1 = new TranslatedWord("pomme", "fr");
        Word tr2 = new TranslatedWord("chat", "fr");
        ArrayList<String> exampleWord = new ArrayList<>();
        exampleWord.add(or1.getWord());
        exampleWord.add(tr1.getWord());
        exampleWord.add(or2.getWord());
        exampleWord.add(tr2.getWord());

        HistoryOutputData output = new HistoryOutputData(exampleWord);
        InMemoryHistoryPresenter failPresenter = new InMemoryHistoryPresenter();

        failPresenter.prepareFailView("Failure");
        String str = "";
        if (failPresenter.prepareFailViewCalled){
            str = " True";
        } else{
            str = " False";
        }

//        fail("Use case failure is expected." + str);

        assertTrue(failPresenter.isPFVCalled());

    }

    private static class InMemoryHistoryDAO implements HistoryDataAccessInterface{

        private boolean saveCalled = false;
        private boolean getCalled = false;
        public void save(){
            saveCalled = true;
        }

        public ArrayList<String> get(){
            getCalled = true;
            return new ArrayList<>();
        }
    }

    private static class InMemoryHistoryPresenter implements HistoryOutputBoundary{
        private boolean prepareSuccessViewCalled = false;
        private boolean prepareFailViewCalled = false;
        @Override
        public void prepareSuccessView(HistoryOutputData data) {
            prepareSuccessViewCalled = true;
        }

        @Override
        public void prepareFailView(String error) {
            prepareFailViewCalled = true;
        }

        public boolean isPSVCalled() {
            return prepareSuccessViewCalled;
        }

        public boolean isPFVCalled(){
            return prepareFailViewCalled;
        }
    };

}
