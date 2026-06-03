package com.example.javacodingjourney.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreApp {

    public static void main(String[] arg) {
        List<String> stores = List.of("Door Dash", "Apple Store", "Application Store", "Applications", "Applause Store", "Aptitude Store");
        Trie trie = new Trie();
        stores.forEach(trie::insert);

        trie.search("App").forEach(System.out::println);
    }

    // Prefix Tree / Trie
    public static class Trie {

        private final TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public List<String> search(String prefix) {
            TrieNode current = this.root;
            for (char ch : prefix.toLowerCase().toCharArray()) {
                if (!current.childrenMap.containsKey(ch)) {
                    return new ArrayList<>();
                }
                current = current.childrenMap.get(ch);
            }
            return current.storeNames;
        }

        public void insert(String storeName) {
            TrieNode current = this.root;
            for (char ch : storeName.toLowerCase().toCharArray()) { //  appaj
                current = current.childrenMap.computeIfAbsent(ch, c -> new TrieNode());
                current.storeNames.add(storeName);
            }
            current.isEndOfWord = true;
        }

        public static class TrieNode {
            private final Map<Character, TrieNode> childrenMap;
            private final List<String> storeNames;
            boolean isEndOfWord;

            public TrieNode() {
                childrenMap = new HashMap<>();
                isEndOfWord = false;
                storeNames = new ArrayList<>();
            }
        }
    }
}
