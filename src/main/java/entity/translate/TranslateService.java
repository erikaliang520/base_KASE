package entity.translate;

import java.io.IOException;

public interface TranslateService {
    String performTranslate(String word) throws IOException;
}
