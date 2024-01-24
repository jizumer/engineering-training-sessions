package com.jizumer.dsa;

import java.util.*;

//2215. Find the Difference of Two Arrays
public class DifferenceOfTwoArrays {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> second = new HashMap<>();

    for (int i : nums1) {
            first.putIfAbsent(i, 1);
        }
        for (int i : nums2) {
            second.putIfAbsent(i, 1);
        }

        for (int i : nums1) {
            second.remove(i);
        }
        for (int i : nums2) {
            first.remove(i);
        }
        return List.of(
                first.keySet().stream().toList(),
                second.keySet().stream().toList());
    }
}
