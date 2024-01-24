package com.jizumer.dsa;

import java.util.LinkedList;
import java.util.Queue;

//1004. Max Consecutive Ones III
public class MaxConsecutivesOnes3 {
    public int longestOnes(int[] nums, int k) {

        //Structure to store the flipped 1's
        Queue<Integer> flipped = new LinkedList<>();

        for (int i = 0; i < nums.length && flipped.size() < k; i++) {

            if (nums[i] == 0) {
                nums[i] = 1;
                flipped.add(i);
            }
        }

        if (flipped.size() < k) return nums.length;

        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                max = Math.max(max, count);
                count = 0;
            } else {
                count++;
            }
        }
        max = Math.max(max, count);

        if(k == 0) return max;
        int runner = getNextZero(0, nums);
        while (runner != -1) {
            int oldestFlipped = flipped.remove();
            nums[oldestFlipped] = 0;
            nums[runner] = 1;
            flipped.add(runner);
            max = Math.max(max, calculateConsecutiveOnesTo(runner, nums));
            runner = getNextZero(runner, nums);
        }

        return max;
    }

    private int calculateConsecutiveOnesTo(int pos, int[] nums) {
        int count = 1;
        for (int i = pos + 1; i < nums.length; i++) {
            if (nums[i] == 0) break;
            count++;
        }
        for (int i = pos - 1; i >= 0; i--) {
            if (nums[i] == 0) break;
            count++;
        }
        return count;
    }

    private int getNextZero(int pos, int[] nums) {
        int next = -1;

        for (int i = pos + 1; i < nums.length; i++) {
            if (nums[i] == 0) return i;
        }

        return next;
    }
}
