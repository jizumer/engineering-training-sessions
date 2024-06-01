package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupAnagramsTest {

  @Test
  void groupAnagrams() {
    GroupAnagrams groupAnagrams = new GroupAnagrams();
    String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
    List<List<String>> expected =
        List.of(List.of("eat", "tea", "ate"), List.of("bat"), List.of("tan", "nat"));

    assertEquals(expected, groupAnagrams.groupAnagrams(input));
  }
}
