package com.gmail.krotenok.test;

import com.gmail.krotenok.AnagramService;
import com.gmail.krotenok.imp.AnagramServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnagramServiceImplTest {
    private final AnagramService anagramService = new AnagramServiceImpl();

    @Test
    void isAnagram_FirstWordNull_returnFalse() {
        String testWordA = "test";
        String testWordB = null;
        boolean result = anagramService.isAnagram(testWordA, testWordB);
        Assertions.assertFalse(result);
    }

    @Test
    void isAnagram_SecondWordNull_returnFalse() {
        String testWordA = null;
        String testWordB = "test";
        boolean result = anagramService.isAnagram(testWordA, testWordB);
        Assertions.assertFalse(result);
    }

    @Test
    void isAnagram_TwoWordsNull_returnFalse() {
        String testWordA = null;
        String testWordB = null;
        boolean result = anagramService.isAnagram(testWordA, testWordB);
        Assertions.assertFalse(result);
    }

    @Test
    void isAnagram_TwoWordsNotAnagram_returnFalse() {
        String testWordA = "SRIANGLE";
        String testWordB = "integral";
        boolean result = anagramService.isAnagram(testWordA, testWordB);
        Assertions.assertFalse(result);
    }

    @Test
    void isAnagram_TwoWordsAnagramDifferentCase_returnTrue() {
        String testWordA = "TRIANGLE";
        String testWordB = "integral";
        boolean result = anagramService.isAnagram(testWordA, testWordB);
        Assertions.assertTrue(result);
    }

    @Test
    void isAnagram_TwoWordsAnagram_returnTrue() {
        String testWordA = "triangle";
        String testWordB = "integral";
        boolean result = anagramService.isAnagram(testWordA, testWordB);
        Assertions.assertTrue(result);
    }
}