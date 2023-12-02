package use_case.translate;

import java.io.IOException;

public interface TranslateInputBoundary {
    void execute(TranslateInputData translateInputData) throws IOException;
}
