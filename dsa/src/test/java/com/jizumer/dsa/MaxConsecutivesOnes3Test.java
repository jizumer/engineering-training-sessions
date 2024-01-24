package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxConsecutivesOnes3Test {

    @Test
    void shoouldCalculateMaxConsecutiveOnes() {

        assertEquals(6, new MaxConsecutivesOnes3()
                .longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    @Test
    void shouldCalculateMaxConsecutiveOnesForKZero() {
        assertEquals(3, new MaxConsecutivesOnes3().longestOnes(new int[]{0, 0, 1, 1, 1, 0, 0}, 0));
    }

    @Test
    void shouldCalculateMaxConsecutiveOnesForKZeroLarger() {
        assertEquals(4, new MaxConsecutivesOnes3().longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 0));
    }

}