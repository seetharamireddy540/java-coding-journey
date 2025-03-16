package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriesApp {

    public static void main(String[] args) {

        Trie trie = new Trie();

        // Insert words into the trie
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("banana");

        // Search for words
        System.out.println("Search 'apple': " + trie.search("apple"));       // true
        System.out.println("Search 'app': " + trie.search("app"));           // true
        System.out.println("Search 'appl': " + trie.search("appl"));         // false
        System.out.println("Search 'ban': " + trie.search("ban"));           // false



    }
    public static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);
            }
            current.isEndOfWord = true;
        }

        public boolean search(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return false;
                }
                current = current.children.get(c);
            }
            return current.isEndOfWord;
        }

        public List<String> autoComplete(String prefix) {
            List<String> results = new ArrayList<>();
            TrieNode current = root;
            for (char c : prefix.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return results;
                }
                current = current.children.get(c);
            }
            findAllWords(current, prefix, results);
            return results;
        }

        private void findAllWords(TrieNode node, String prefix, List<String> results) {
            if (node.isEndOfWord) {
                results.add(prefix);
            }
            for (char c : node.children.keySet()) {
                findAllWords(node.children.get(c), prefix + c, results);
            }
        }
    }

    public static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
}
