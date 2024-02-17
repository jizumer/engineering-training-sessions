package com.jizumer.dsa; // 208. Implement Trie (Prefix Tree)

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private final Map<Character, Node> nodes = new HashMap<>();

    public Trie() {
    }

    public void insert(String word) {

        if (word.isEmpty()) {
            return;
        }

        Node node;
        if (!nodes.containsKey(word.charAt(0))) {
            node = new Node();
            nodes.put(word.charAt(0), node);
        } else node = nodes.get(word.charAt(0));
        node.insert(word);
    }

    public boolean search(String word) {

        if (word.isEmpty()) {
            return false;
        }
        Node node = nodes.get(word.charAt(0));
        if (node == null) {
            return false;
        }
        return node.search(word);
    }

    public boolean startsWith(String prefix) {

        if (prefix.isEmpty()) {
            return false;
        }
        Node node = nodes.get(prefix.charAt(0));
        if (node == null) {
            return false;
        }
        return node.startsWith(prefix);
    }

    static class Node {
        boolean endOfWord = false;
        Character letter;
        Map<Character, Node> suffixes = new HashMap<>();


        void insert(String word) {
            if (word.isEmpty()) {
                return;
            }
            letter = word.charAt(0);
            if (word.length() == 1) {
                endOfWord = true;
                return;
            }
            if (suffixes.get(word.charAt(1)) == null) {
                suffixes.put(word.charAt(1), new Node());
            }
            suffixes.get(word.charAt(1)).insert(word.substring(1));
        }

        boolean search(String word) {
            if (word.isEmpty()) return this.letter == null;
            if (this.letter != word.charAt(0)) return false;
            if (word.length() == 1) return endOfWord;

            Node suffix = suffixes.get(word.charAt(1));
            if (suffix == null) return false;

            return suffix.search(word.substring(1));
        }

        public boolean startsWith(String prefix) {
            if (prefix.isEmpty()) return true;
            if (this.letter == null || this.letter != prefix.charAt(0)) return false;
            if (prefix.length() == 1) return true;

            Node suffix = suffixes.get(prefix.charAt(1));
            if (suffix == null) return false;

            return suffix.startsWith(prefix.substring(1));
        }
    }
}
