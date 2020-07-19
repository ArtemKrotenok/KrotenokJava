package com.gmail.krotenok;

public class Main {
    private static final char REPLACEABLE_CHARACTER = '+';

    public static void main(String[] args) {
        String testWordA = "triangle";
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
        char[] arrayCharA = wordA.toCharArray();
        char[] arrayCharB = wordB.toCharArray();
        boolean matchFound = false;
        for (int i = 0; i < arrayCharA.length; i++) {
            for (int j = 0; j < arrayCharB.length; j++) {
                matchFound = false;
                if (arrayCharB[j] == arrayCharA[i]) {
                    arrayCharB[j] = REPLACEABLE_CHARACTER;
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) return false;
        }
        return true;
    }
}
