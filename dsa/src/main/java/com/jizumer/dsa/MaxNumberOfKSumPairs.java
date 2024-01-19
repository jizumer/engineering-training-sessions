package com.jizumer.dsa;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MaxNumberOfKSumPairs {

    public int maxOperations(int[] nums, int k) {

        int maxOperations = 0;
        Arrays.sort(nums);
        int limit = nums.length - 1;
        while (nums[limit] >= k && limit > 0)
            limit--;

        TreeMap<Integer, Integer> counts = new TreeMap<>();
        int count = 1;
        int last = nums[0];
        for (int i = 1; i <= limit; i++) {
            if (nums[i] == last) {
                count++;
            } else {
                counts.put(last, count);
                last = nums[i];
                count = 1;
            }
        }
        counts.put(nums[limit], count);


        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getKey() > k / 2.0) break;
            if (entry.getKey() == k / 2.0) {
                maxOperations += Math.floor(entry.getValue() / 2);
                break;
            }
            maxOperations
                    += Math.min(entry.getValue(),
                    counts.getOrDefault(k - entry.getValue(), 0));
        }

        return maxOperations;

    }
}
