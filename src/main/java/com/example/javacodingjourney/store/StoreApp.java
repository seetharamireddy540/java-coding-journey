package com.example.javacodingjourney.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreApp {

    public static void main(String[] arg) {
        List<String> stores = List.of("Akpple", "Ap32plication", "A5pplications", "A2pplause", "Aptitude");
        Trie trie = new Trie();
        stores.forEach(trie::insert);

        trie.search("App").forEach(System.out::println);
    }

    public static class Trie {

        private final TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public List<String> search(String prefix) {
            TrieNode current = this.root;
            for (char ch : prefix.toLowerCase().toCharArray()) {
                if (!current.children.containsKey(ch)) {
                    return new ArrayList<>();
                }
                current = current.children.get(ch);
            }
            return current.storeNames;
        }

        public void insert(String storeName) {
            TrieNode current = root;
            for (char ch : storeName.toLowerCase().toCharArray()) {
                current = current.children.computeIfAbsent(ch, c -> new TrieNode());
                current.storeNames.add(storeName);
            }
            current.isEndOfWord = true;
        }

        public static class TrieNode {
            private final Map<Character, TrieNode> children;
            private final List<String> storeNames;
            boolean isEndOfWord;

            public TrieNode() {
                children = new HashMap<>();
                isEndOfWord = false;
                storeNames = new ArrayList<>();
            }
        }
    }
}
