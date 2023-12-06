package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OriginalWordRelatedTest {

    private OriginalWordRelated word;

    @BeforeEach
    void init(){
        List<String> related_words = new ArrayList<>();
        related_words.add("banana");
        related_words.add("orange");
        word = new OriginalWordRelated("apple", "english", related_words);
    }

    @Test
    void getWord() {
        assertEquals("apple", word.getWord());
    }

    @Test
    void getLanguage() {
        assertEquals("english", word.getLanguage());
    }

    @Test
    void getRelatedWords() {
        List<String> related_words = new ArrayList<>();
        related_words.add("banana");
        related_words.add("orange");
        assertEquals(related_words, word.getRelatedWords());
    }

    @Test
    void setRelatedWords() {
        List<String> related_words = new ArrayList<>();
        related_words.add("banana");
        related_words.add("orange");

        List<String> other_words = new ArrayList<>();
        related_words.add("erika");
        related_words.add("sophie");
        related_words.add("addision");

        word.setRelatedWords(other_words);
        assertNotEquals(related_words, word.getRelatedWords());
        assertEquals(other_words, word.getRelatedWords());
    }
}