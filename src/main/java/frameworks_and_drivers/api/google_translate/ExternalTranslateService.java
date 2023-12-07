package frameworks_and_drivers.api.google_translate;


import entity.translate.TranslateService;

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
