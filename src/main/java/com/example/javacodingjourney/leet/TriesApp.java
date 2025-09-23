package com.example.javacodingjourney.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriesApp {

    public static void main(String[] args) {

    }

    public static class Trie {

        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {

            TrieNode current = this.root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a'; // Get array index from 0 - 25
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isEndOfWord = true;

        }

        // Search for a word in the trie
        public boolean search(String word) {
            TrieNode current = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';

                // If path doesn't exist, word not found
                if (current.children[index] == null) {
                    return false;
                }

                current = current.children[index];
            }

            // Check if it's a complete word
            return current.isEndOfWord;
        }

        // Check if any word starts with given prefix
        public boolean startsWith(String prefix) {
            TrieNode current = root;

            for (char ch : prefix.toCharArray()) {
                int index = ch - 'a';

                if (current.children[index] == null) {
                    return false;
                }

                current = current.children[index];
            }

            return true; // Prefix exists
        }

        // Delete a word from the trie
        public void delete(String word) {
            deleteHelper(root, word, 0);
        }

        private boolean deleteHelper(TrieNode node, String word, int index) {
            if (index == word.length()) {
                // We've reached the end of the word
                if (!node.isEndOfWord) {
                    return false; // Word doesn't exist
                }
                node.isEndOfWord = false;

                // Return true if node has no children (can be deleted)
                return !hasChildren(node);
            }

            int charIndex = word.charAt(index) - 'a';
            TrieNode child = node.children[charIndex];

            if (child == null) {
                return false; // Word doesn't exist
            }

            boolean shouldDeleteChild = deleteHelper(child, word, index + 1);

            if (shouldDeleteChild) {
                node.children[charIndex] = null;

                // Return true if current node has no children and is not end of another word
                return !node.isEndOfWord && !hasChildren(node);
            }

            return false;
        }

        private boolean hasChildren(TrieNode node) {
            for (TrieNode child : node.children) {
                if (child != null) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    class TrieNodeMap {
        Map<Character, TrieNodeMap> children;
        boolean isEndOfWord;
        int count; // Track word frequency

        public TrieNodeMap() {
            children = new HashMap<>();
            isEndOfWord = false;
            count = 0;
        }
    }

    public class AdvancedTrie {
        private TrieNodeMap root;

        public AdvancedTrie() {
            root = new TrieNodeMap();
        }

        // Insert with word frequency tracking
        public void insert(String word) {
            TrieNodeMap current = root;

            for (char ch : word.toCharArray()) {
                current.children.putIfAbsent(ch, new TrieNodeMap());
                current = current.children.get(ch);
            }

            current.isEndOfWord = true;
            current.count++;
        }

        // Get all words with given prefix
        public List<String> getWordsWithPrefix(String prefix) {
            List<String> result = new ArrayList<>();
            TrieNodeMap current = root;

            // Navigate to prefix node
            for (char ch : prefix.toCharArray()) {
                if (!current.children.containsKey(ch)) {
                    return result; // Prefix not found
                }
                current = current.children.get(ch);
            }

            // Collect all words from this node
            collectWords(current, prefix, result);
            return result;
        }

        private void collectWords(TrieNodeMap node, String prefix, List<String> result) {
            if (node.isEndOfWord) {
                result.add(prefix);
            }

            for (Map.Entry<Character, TrieNodeMap> entry : node.children.entrySet()) {
                collectWords(entry.getValue(), prefix + entry.getKey(), result);
            }
        }

        // Autocomplete: Get top K suggestions
        public List<String> autocomplete(String prefix, int k) {
            List<WordFreq> words = new ArrayList<>();
            TrieNodeMap current = root;

            // Navigate to prefix
            for (char ch : prefix.toCharArray()) {
                if (!current.children.containsKey(ch)) {
                    return new ArrayList<>();
                }
                current = current.children.get(ch);
            }

            // Collect words with frequencies
            collectWordsWithFreq(current, prefix, words);

            // Sort by frequency and return top K
            words.sort((a, b) -> b.freq - a.freq);

            List<String> result = new ArrayList<>();
            for (int i = 0; i < Math.min(k, words.size()); i++) {
                result.add(words.get(i).word);
            }

            return result;
        }

        private void collectWordsWithFreq(TrieNodeMap node, String prefix, List<WordFreq> words) {
            if (node.isEndOfWord) {
                words.add(new WordFreq(prefix, node.count));
            }

            for (Map.Entry<Character, TrieNodeMap> entry : node.children.entrySet()) {
                collectWordsWithFreq(entry.getValue(), prefix + entry.getKey(), words);
            }
        }

        class WordFreq {
            String word;
            int freq;

            WordFreq(String word, int freq) {
                this.word = word;
                this.freq = freq;
            }
        }
    }
}
