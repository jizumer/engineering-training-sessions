package com.jizumer.dsa;

import java.util.*;

public class GroupAnagrams {

  public List<List<String>> groupAnagrams(String[] strs) {

    Map<String, List<String>> anagrams = new HashMap<>();

    for (String word : strs) {
      String fingerprint = calculateFingerprint(word);
      List<String> anagramList = anagrams.get(fingerprint);
      if (anagramList == null) {
        anagramList = new ArrayList<>();
        anagrams.put(fingerprint, anagramList);
      }
      anagramList.add(word);
    }

    List<List<String>> result = new ArrayList<>();

    for (String fingerprint : anagrams.keySet()) {
      result.add(anagrams.get(fingerprint));
    }

    return result;
  }

  private String calculateFingerprint(String word) {
    char[] charArray = word.toCharArray();
    Arrays.sort(charArray);
    return new String(charArray);
  }
}
