package com.jizumer.dsa.trie;

import java.util.ArrayList;
import java.util.List;

//1268. Search Suggestions System
public class SearchSuggestionsSystem {
    private TrieNode root = new TrieNode();

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        for (String product : products) {
            root.insert(product);
        }
        List<List<String>> results = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            List<String> result = new ArrayList<>();
            result.addAll(root.findByPrefix(searchWord.substring(0,i)));
            results.add(result);
        }

        return results;
    }


}
