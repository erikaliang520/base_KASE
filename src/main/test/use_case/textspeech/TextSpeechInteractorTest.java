package use_case.textspeech;

import org.junit.jupiter.api.Test;
import use_case.history.HistoryDataAccessInterface;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;
import use_case.ports.api.TextSpeechService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

class TextSpeechInteractorTest {

    @Test
    void testingThatPresenterWorks() throws IOException {

        TextSpeechDataAccessInterface repo = new TextSpeechDataAccessInterface() {
            @Override
            public boolean equals(Object obj) {
                return TRUE;
            }
        };

        TextSpeechOutputBoundary textSpeechOutputBoundary = new TextSpeechOutputBoundary() {
            @Override
            public void prepareTextSpeechSuccessView(TextSpeechOutputData outputData) {
                assertEquals(outputData.getAudioPath(), "songhello, it's me, - adele");
            }

            @Override
            public void prepareTextSpeechFailView(String error) {

            }
        };

        TextSpeechService textSpeechService = new TextSpeechService() {
            @Override
            public String performTextToSpeech(String text) throws IOException {
                return text+"hello, it's me, - adele";
            }
        };

        TextSpeechInputData input = new TextSpeechInputData();
        input.InputData("song");

        TextSpeechInteractor interactorText = new TextSpeechInteractor(repo, textSpeechOutputBoundary, textSpeechService);
        interactorText.execute(input);

    }



}