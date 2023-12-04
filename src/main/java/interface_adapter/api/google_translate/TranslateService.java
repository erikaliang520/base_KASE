package interface_adapter.api.google_translate;

import java.io.IOException;

public interface TranslateService {
    String performTranslate(String word) throws IOException;
}
