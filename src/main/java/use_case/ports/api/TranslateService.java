package use_case.ports.api;

import java.io.IOException;

public interface TranslateService {
    String performTranslate(String word) throws IOException;
}
