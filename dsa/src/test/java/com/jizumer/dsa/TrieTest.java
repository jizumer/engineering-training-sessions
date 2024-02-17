package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

class TrieTest {

    /**
     * Operations: ["Trie","insert","search","search","startsWith","insert","search"]
     * Inputs: [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
     * Expected: [null,null,true,false,true,null,true]
     */
    @Test
    void shouldTestHappyPath(){
        Trie trie = new Trie();
        trie.insert("apple");
        assert trie.search("apple");
        assert !trie.search("app");
        assert trie.startsWith("app");
        trie.insert("app");
        assert trie.search("app");
    }

    /**
     * Operations: ["Trie","insert","insert","insert","insert","insert","insert","search","search","search","search","search","search","search","search","search","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith"]
     * Inputs [[],["app"],["apple"],["beer"],["add"],["jam"],["rental"],["apps"],["app"],["ad"],["applepie"],["rest"],["jan"],["rent"],["beer"],["jam"],["apps"],["app"],["ad"],["applepie"],["rest"],["jan"],["rent"],["beer"],["jam"]]
     * Expected: [null,null,null,null,null,null,null,false,true,false,false,false,false,false,true,true,false,true,true,false,false,false,true,true,true]
     */
    @Test
    void shouldTestLongHappyPath() {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");

        assert !trie.search("apps");
        assert trie.search("app");
        assert !trie.search("ad");
        assert !trie.search("applepie");
        assert !trie.search("rest");
        assert !trie.search("jan");
        assert !trie.search("rent");
        assert trie.search("beer");
        assert trie.search("jam");
        assert !trie.startsWith("apps");
        assert trie.startsWith("app");
        assert trie.startsWith("ad");
        assert !trie.startsWith("applepie");
        assert !trie.startsWith("rest");
        assert !trie.startsWith("jan");
        assert !trie.startsWith("rent");
        assert trie.startsWith("beer");
        assert trie.startsWith("jam");

    }

}