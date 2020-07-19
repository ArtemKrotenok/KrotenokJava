package com.gmail.krotenok;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String testWordA = "TRIANGLE";
        String testWordB = "integral";
        System.out.println(isAnagram(testWordA, testWordB));
    }

    public static boolean isAnagram(String wordA, String wordB) {
        if (wordA.equals(wordB)) {
            return false;
        }
        if (wordA.length() != wordB.length()) {
            return false;
        }
        char[] arrayCharA = wordA.toLowerCase().toCharArray();
        char[] arrayCharB = wordB.toLowerCase().toCharArray();
        Arrays.sort(arrayCharA);
        Arrays.sort(arrayCharB);
        return Arrays.equals(arrayCharA, arrayCharB);
    }
}
