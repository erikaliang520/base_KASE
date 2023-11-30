package entity;

import java.util.ArrayList;

public class RelatedWords implements WordsList{

    private ArrayList<String> listOfWords;

    RelatedWords(ArrayList<String> inputListOfWords){

        this.listOfWords = inputListOfWords;

    }


    @Override
    public ArrayList<String> getList() {
        return this.listOfWords;
    }
}
