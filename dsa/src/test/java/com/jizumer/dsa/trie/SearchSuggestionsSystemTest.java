package com.jizumer.dsa.trie;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchSuggestionsSystemTest {

    /**
     * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
     * Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
     */
    @Test
    void shouldTestHappyPath() {
        SearchSuggestionsSystem searchSuggestionsSystem = new SearchSuggestionsSystem();
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        List<List<String>> result = searchSuggestionsSystem.suggestedProducts(products, searchWord);
        assertEquals(List.of(
                List.of("mobile", "moneypot", "monitor"),
                List.of("mobile", "moneypot", "monitor"),
                List.of("mouse", "mousepad"),
                List.of("mouse", "mousepad"),
                List.of("mouse", "mousepad")), result);
    }

}