package com.example.orcale;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Getter
public class TrieApp {

    private TrieNode root;
    private TrieNodeV2 root2;

    public TrieApp() {
        root = new TrieNode();
        root2 = new TrieNodeV2();
    }

    public static void main(String[] args) {

        TrieApp trieApp = new TrieApp();
        trieApp.insert("apple");
        trieApp.insert2("apple");
        trieApp.insert("orange");
        trieApp.insert2("orange");
        trieApp.insert("pear");
        trieApp.insert2("pear");
        System.out.println(trieApp.search("apple"));
        System.out.println(trieApp.search2("apple"));
        System.out.println(trieApp.startsWith("app"));
        System.out.println(trieApp.startsWith2("app"));

        System.out.println(trieApp.startsWith("appz"));
        System.out.println(trieApp.startsWith2("zapp"));

    }

    public void insert2(String word) {
        TrieNodeV2 current = getRoot2();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            current.children.putIfAbsent(c, new TrieNodeV2());
            current = current.children.get(c);
        }
        current.isEnd = true;
    }

    public TrieNodeV2 getNodeV2(String word) {
        TrieNodeV2 current = getRoot2();
        for (Character c : word.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return null;
            }
        }
        return current;

    }

    public void insert(String word) {
        TrieNode node = getRoot();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = getNode(word);
        return node != null && node.isEnd;
    }

    public boolean search2(String word) {
        TrieNodeV2 node = getNodeV2(word);
        return node != null && node.isEnd;
    }
    public boolean startsWith2(String prefix) {
        TrieNodeV2 node = getNodeV2(prefix);

        if (node == null) {
            return false;
        }
        return true;
    }
    public boolean startsWith(String prefix) {
        TrieNode node = getNode(prefix);

        if (node == null) {
            return false;
        }
        return true;
    }

    public TrieNode getNode(String word) {
        TrieNode current = getRoot();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }

    private static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEnd = false;
        }
    }

    private static class TrieNodeV2 {
        Map<Character, TrieNodeV2> children;
        boolean isEnd;

        public TrieNodeV2() {
            this.children = new HashMap<Character, TrieNodeV2>();
            this.isEnd = false;
        }
    }
}
