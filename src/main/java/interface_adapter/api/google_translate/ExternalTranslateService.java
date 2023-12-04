package interface_adapter.api.google_translate;


import use_case.ports.api.TranslateService;

import java.io.IOException;

public class ExternalTranslateService implements TranslateService {
    private final TranslateApiClient translateApiClient;

    public ExternalTranslateService(TranslateApiClient translateApiClient) {
        this.translateApiClient = translateApiClient;
    }

    @Override
    public String performTranslate(String word) throws IOException {
        return translateApiClient.Translate(word);
    }
}
