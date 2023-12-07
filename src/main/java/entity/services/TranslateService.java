package entity.services;

import java.io.IOException;

public interface TranslateService {
    String performTranslate(String word) throws IOException;
}
