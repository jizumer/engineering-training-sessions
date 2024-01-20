package com.jizumer.dsa;

import java.util.*;

//1. Two Sum
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexes = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (indexes.containsKey(complement))
                return new int[]{indexes.get(complement), i};
            indexes.put(nums[i], i);
        }

        throw new RuntimeException("Solution not found");
    }


}
