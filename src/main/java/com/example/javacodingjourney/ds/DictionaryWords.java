package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryWords {
    public static final String[] DICTIONARY = {
            "apple", "banana", "cat", "dog", "elephant",
            "frog", "grape", "house", "ice", "jacket",
            "kite", "lemon", "monkey", "nest", "orange",
            "penguin", "queen", "rabbit", "snake", "tiger",
            "umbrella", "violin", "whale", "xylophone", "yellow",
            "zebra"
    };

    /**
     * Returns a Map containing words from the dictionary grouped by their starting letter.
     *
     * @return Map where key is the starting letter and value is list of words starting with that letter
     */
    public static Map<Character, List<String>> getWordsGroupedByAlphabet() {
        Map<Character, List<String>> wordsByAlphabet = new TreeMap<>();

        // Initialize the map with all alphabets
        for (char c = 'a'; c <= 'z'; c++) {
            wordsByAlphabet.put(c, new ArrayList<>());
        }

        // Group words by their starting letter
        for (String word : DICTIONARY) {
            if (word != null && !word.isEmpty()) {
                char firstLetter = Character.toLowerCase(word.charAt(0));
                wordsByAlphabet.get(firstLetter).add(word);
            }
        }

        return wordsByAlphabet;
    }

    public static void main(String[] args) {
        DictionaryWords dictionaryWords = new DictionaryWords();
        dictionaryWords.printWordsByAlphabet();
    }

    /**
     * Prints all words grouped by their starting letter
     */
    public void printWordsByAlphabet() {
        Map<Character, List<String>> wordsByAlphabet = getWordsGroupedByAlphabet();

        for (Map.Entry<Character, List<String>> entry : wordsByAlphabet.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
    }
}