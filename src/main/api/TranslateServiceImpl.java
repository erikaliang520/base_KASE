// package api;


import interface_adapter.api.TranslateService;

import java.io.IOException;

public class TranslateServiceImpl implements TranslateService {
    private final TranslateApiClient translateApiClient;

    public TranslateServiceImpl(TranslateApiClient translateApiClient) {
        this.translateApiClient = translateApiClient;
    }

    @Override
    public String performTranslate(String word) throws IOException {
        return translateApiClient.Translate(word);
    }
}
