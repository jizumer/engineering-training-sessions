package com.jizumer.dsa; // 208. Implement Trie (Prefix Tree)

import java.util.HashMap;
import java.util.Map;

public class Trie {

  Node root = new Node("");

  public Trie() {}

  public void insert(String word) {
    root.insert(word);
  }

  public boolean search(String word) {
    return root.search(word);
  }

  public boolean startsWith(String prefix) {
    return root.startsWith(prefix);
  }

  class Node {
    Character letter;
    Map<Character, Node> suffixes = new HashMap<>();

    Node(String word) {
      if (word.isEmpty()) {
        this.letter = null;
        return;
      }

      this.letter = word.charAt(0);
      if (word.length() > 1) {
        Node suffix = suffixes.putIfAbsent(word.charAt(1), new Node(word.substring(1)));
        suffix.insert(word.substring(1));
      }
    }

    void insert(String suffix) {
      if (suffix.isEmpty()) {
        return;
      }
      suffixes.putIfAbsent(suffix.charAt(0), new Node(suffix));
    }

    boolean search(String word) {
      if (word.isEmpty()) return true;
      Node suffix = suffixes.get(word.charAt(0));
      if (suffix == null) return false;
      return search((word.substring(1)));
    }

    public boolean startsWith(String prefix) {
      return false;
    }
  }
}
