package entity;

import java.util.List;

public interface WordRelated extends Word {

//    public String getWord();
//
//    public String getLanguage();

    public List<String> getRelatedWords();

}
