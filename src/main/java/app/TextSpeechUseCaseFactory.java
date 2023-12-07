package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslatePresenter;
import interface_adapter.translate.TranslateViewModel;
import use_case.translate.TranslateDataAccessInterface;
import use_case.translate.TranslateOutputBoundary;
import view.TextSpeechView;
import interface_adapter.textspeech.TextSpeechViewModel;
import use_case.textspeech.TextSpeechDataAccessInterface;
import interface_adapter.textspeech.TextSpeechController;
import interface_adapter.textspeech.TextSpeechPresenter;
import interface_adapter.textspeech.TextSpeechState;
import use_case.textspeech.TextSpeechInputBoundary;
import use_case.textspeech.TextSpeechOutputData;
import use_case.textspeech.TextSpeechInteractor;
import use_case.textspeech.TextSpeechInputData;
import use_case.textspeech.TextSpeechOutputBoundary;
import view.TranslateView;


import javax.swing.*;
import java.io.IOException;

public class TextSpeechUseCaseFactory {

    /** Prevent instantiation */
    private TextSpeechUseCaseFactory() {}

    public static TextSpeechView create(
            ViewManagerModel viewManagerModel,
            TextSpeechViewModel textSpeechViewModel,
            TextSpeechDataAccessInterface textSpeechDataAccessObject) {

        try {
            TextSpeechController textSpeechController = createTextSpeechUseCase(viewManagerModel, textSpeechViewModel,
                    textSpeechDataAccessObject);
            return new TextSpeechView(textSpeechViewModel, textSpeechController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static TextSpeechController createTextSpeechUseCase(
            ViewManagerModel viewManagerModel,
            TextSpeechViewModel textspeechViewModel,
            TextSpeechDataAccessInterface textSpeechDataAccessObject) throws IOException {

        TextSpeechOutputBoundary textSpeechOutputBoundary = new TextSpeechPresenter(viewManagerModel,
                textspeechViewModel);

        TextSpeechInputBoundary textSpeechInteractor = new TextSpeechInteractor(textSpeechDataAccessObject,
                textSpeechOutputBoundary);

        return new TextSpeechController(textSpeechInteractor);
    }

}


