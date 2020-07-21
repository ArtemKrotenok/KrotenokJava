package com.gmail.krotenok.imp;

import com.gmail.krotenok.AnagramService;

import java.util.Arrays;

public class AnagramServiceImpl implements AnagramService {
    @Override
    public boolean isAnagram(String wordOrigin, String wordVerifiable) {
        if (wordOrigin == null || wordVerifiable == null) {
            return false;
        }
        if (wordOrigin.length() != wordVerifiable.length()) {
            return false;
        }
        char[] arrayCharA = wordOrigin.toLowerCase().toCharArray();
        char[] arrayCharB = wordVerifiable.toLowerCase().toCharArray();
        Arrays.sort(arrayCharA);
        Arrays.sort(arrayCharB);
        return Arrays.equals(arrayCharA, arrayCharB);
    }
}
