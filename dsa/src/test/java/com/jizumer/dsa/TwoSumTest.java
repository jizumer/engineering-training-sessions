package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumTest {

    @Test
    void shouldReturnTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};
        assertArrayEquals(expected, new TwoSum().twoSum(nums, target));
    }

    @Test
    void shouldReturnTwoSumOf3And3(){
        int[] nums = {3,3};
        int target = 6;
        int[] expected = {0, 1};
        assertArrayEquals(expected, new TwoSum().twoSum(nums, target));
    }

}