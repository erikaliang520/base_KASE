package entity.factories;

import entity.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;



class OriginalWordFactoryTest {

    private OriginalWordFactory factory;

    @BeforeEach
    void setUp() {
        OriginalWordFactory newFactory = new OriginalWordFactory();
        this.factory = newFactory;
    }

    @Test
    void createWord() {
        Word appleWord = factory.createWord("apple", "english");
        assertEquals(appleWord.getWord(), "apple");
        assertEquals(appleWord.getLanguage(), "english");
    }

//    @Test
//    public void testInvalidInputType() {
//        OriginalWordFactory newFactory = new OriginalWordFactory();
//
//        // Assert that calling yourMethod with an int throws IllegalArgumentException
//        assertThrows(IllegalArgumentException.class, () -> {
//            newFactory.createWord(42, "english"); // Passing an int instead of a String
//        });
//    }

}