package com.jizumer.dsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//1. Two Sum
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        //ordered and with no duplicates set based on nums
        Set<Integer> orderedNums = new TreeSet<>();
        for (int num : nums) {
            orderedNums.add(num);
        }

        for (Integer num : orderedNums) {
            int complement = target - num;
            if (complement == num && orderedNums.contains(complement)) {
                List<Integer> indexes = findUpToTheTwoFirstIndexes(nums, num);
                if (indexes.size() == 2) {
                    return new int[]{indexes.get(0), indexes.get(1)};
                }
            }
            if (orderedNums.contains(complement)) {
                List<Integer> indexes = findUpToTheTwoFirstIndexes(nums, num);
                return new int[]{indexes.getFirst(), findUpToTheTwoFirstIndexes(nums, complement).getFirst()};
            }
        }

        throw new IllegalArgumentException("No solution found");
    }

    private List<Integer> findUpToTheTwoFirstIndexes(int[] nums, int num) {
        int count = 0;
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < nums.length && count < 2; i++) {
            if (nums[i] == num) {
                indexes.add(i);
                count++;
            }
        }
        return indexes;
    }

}
