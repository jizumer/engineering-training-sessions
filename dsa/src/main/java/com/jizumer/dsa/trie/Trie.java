package com.jizumer.dsa.trie; // 208. Implement Trie (Prefix Tree)

public class Trie {

    private final TrieNode root = new TrieNode();

    public Trie() {
    }

    public void insert(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return root.search(word);
    }

    public boolean startsWith(String prefix) {
        return root.startsWith(prefix);
    }

}
