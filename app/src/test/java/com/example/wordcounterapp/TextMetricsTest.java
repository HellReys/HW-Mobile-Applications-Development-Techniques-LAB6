package com.example.wordcounterapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class TextMetricsTest {

    @Test
    public void testCountWords_basic() {
        String text = "Hello world this is a test";
        assertEquals(6, TextMetrics.countWords(text));
    }

    @Test
    public void testCountWords_withPunctuation() {
        String text = "Hello, world. This is a test!";
        assertEquals(6, TextMetrics.countWords(text));
    }

    @Test
    public void testCountWords_empty() {
        assertEquals(0, TextMetrics.countWords(""));
    }

    @Test
    public void testCountSentences_basic() {
        String text = "Hello world. How are you? Fine!";
        assertEquals(3, TextMetrics.countSentences(text));
    }

    @Test
    public void testCountSentences_empty() {
        assertEquals(0, TextMetrics.countSentences(""));
    }

    @Test
    public void testCountChars_basic() {
        String text = "abcd";
        assertEquals(4, TextMetrics.countChars(text));
    }

    @Test
    public void testCountNumbers_basic() {
        String text = "There are 3 cats and 12 dogs.";
        assertEquals(2, TextMetrics.countNumbers(text));  // "3" and "12"
    }

    @Test
    public void testCountNumbers_none() {
        String text = "No numbers here.";
        assertEquals(0, TextMetrics.countNumbers(text));
    }
}
