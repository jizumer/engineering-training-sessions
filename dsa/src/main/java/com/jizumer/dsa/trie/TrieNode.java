package com.jizumer.dsa.trie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TrieNode {
    private boolean endOfWord = false;

    private final int R = 26;

    private final TrieNode[] links = new TrieNode[R];

    public void insert(String word) {
        if (word.isEmpty()) {
            return;
        }
        char letter = word.charAt(0);
        if (links[letter - 'a'] == null) {
            links[letter - 'a'] = new TrieNode();
        }
        if (word.length() == 1) {
            links[letter - 'a'].endOfWord = true;
            return;
        }
        links[letter - 'a'].insert(word.substring(1));
    }

    public boolean search(String word) {
        if (word.isEmpty()) {
            return false;
        }
        char letter = word.charAt(0);
        if (links[letter - 'a'] == null) {
            return false;
        }
        if (word.length() == 1) {
            return links[letter - 'a'].endOfWord;
        }
        return links[letter - 'a'].search(word.substring(1));
    }

    public boolean startsWith(String prefix) {
        if (prefix.isEmpty()) {
            return false;
        }
        char letter = prefix.charAt(0);
        if (links[letter - 'a'] == null) {
            return false;
        }
        if (prefix.length() == 1) {
            return true;
        }
        return links[letter - 'a'].startsWith(prefix.substring(1));
    }

    @Override
    public String toString() {
        return "Node{" +
                "EOW=" + endOfWord +
                ", links=" + printLinks() +
                '}';
    }

    private String printLinks() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < R; i++) {
            if (links[i] != null) {
                sb.append((char) (i + 'a')).append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    public List<String> findByPrefix(String prefix) {

        List<String> words = new ArrayList<>();
        TrieNode runner = this;

        for (int i = 0; i < prefix.length(); i++) {
            TrieNode next = runner.links[prefix.charAt(i) - 'a'];
            if (next == null) return words;
            runner = next;
        }

        dfs(runner, words, prefix);

        return words;


    }

    private void dfs(TrieNode runner, List<String> words, String prefix) {
        if(words.size() == 3) return;
        if (runner.endOfWord) {
            words.add(prefix);
        }
        for (int i = 0; i < R; i++) {
            TrieNode child = runner.links[i];
            if (child != null) dfs(child, words, prefix + (char) (i + 'a'));
        }
    }
}
