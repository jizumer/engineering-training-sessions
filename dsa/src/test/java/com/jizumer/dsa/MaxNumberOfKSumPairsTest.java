package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//1679. Max Number of K-Sum Pairs
class MaxNumberOfKSumPairsTest {

    @Test
    void maxOperations() {
        int[] nums = {1, 2, 3, 4};
        int k = 5;
        assertEquals(2, new MaxNumberOfKSumPairs().maxOperations(nums, k));
    }

    @Test
    void maxOperations2() {
        int[] nums = {3, 1, 3, 4, 3};
        int k = 6;
        assertEquals(1, new MaxNumberOfKSumPairs().maxOperations(nums, k));
    }

}