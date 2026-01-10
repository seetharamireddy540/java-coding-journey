package com.example.javacodingjourney.interview;

import java.util.Locale;

public class TrieApp {

    private TrieNode root;

    public TrieApp() {
        this.root = new TrieNode();
    }

    public static void main(String[] args) {
        TrieApp trieApp = new TrieApp();
        trieApp.insertString("Seetharamireddy");
        System.out.println("Search for name ->" + trieApp.search("Seetharamireddyd"));
    }

    public boolean search(String data) {
        TrieNode current = this.root;

        for(char ch : data.toLowerCase(Locale.ROOT).toCharArray()){
            int index = ch - 'a';
            if (current.children[index] == null){
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }

    public void insertString(String data) {
        TrieNode current = this.root;

        for(char ch : data.toLowerCase(Locale.ROOT).toCharArray()){
            int index = ch - 'a';
            if (current.children[index] == null){
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            this.children = new TrieNode[26]; // for 26 lowercase letters
            this.isEndOfWord = false;
        }
    }
}
