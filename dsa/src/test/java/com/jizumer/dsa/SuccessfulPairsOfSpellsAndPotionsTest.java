package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuccessfulPairsOfSpellsAndPotionsTest {

    @Test
    void shouldCalculateSuccesfullSpellsAndPotions() {
        assertArrayEquals(new int[]{48, 0, 32, 37, 48, 22, 33, 47, 37, 0, 43, 6, 0, 46, 0, 21, 0, 22, 21, 14, 46, 0, 48, 48, 0, 6, 0, 0, 0, 3, 46, 3,
                        45, 0, 34, 20, 32, 0, 33, 47, 45, 47, 20, 18, 22, 45, 0, 22, 0, 14, 0, 0},
                new SuccessfulPairsOfSpellsAndPotions()
                        .successfulPairs(new int[]{40, 11, 24, 28, 40, 22, 26, 38, 28, 10, 31, 16, 10, 37, 13, 21, 9, 22, 21, 18, 34, 2, 40,
                                        40, 6, 16, 9, 14, 14, 15, 37, 15, 32, 4, 27, 20, 24, 12, 26, 39, 32, 39, 20, 19, 22, 33, 2, 22, 9, 18, 12, 5},
                                new int[]{31, 40, 29, 19, 27, 16, 25, 8, 33, 25, 36, 21, 7, 27, 40, 24, 18, 26, 32, 25, 22, 21, 38, 22, 37, 34,
                                        15, 36, 21, 22, 37, 14, 31, 20, 36, 27, 28, 32, 21, 26, 33, 37, 27, 39, 19, 36, 20, 23, 25, 39, 40},
                                600));
    }

    @Test
    void shouldPerformBinarySearchOfTheLowestSuccessfullPotionWhenAllAreBigger() {
        assertEquals(0, new SuccessfulPairsOfSpellsAndPotions().searchLowestSuccessfullPotion(new int[]{23, 36, 38}, 21.888));
    }

    @Test
    void shouldSearchLowestSuccessfulPotionWithDuplicates() {
        assertEquals(19, new SuccessfulPairsOfSpellsAndPotions().searchLowestSuccessfullPotion(new int[]{7, 8,
                14, 15, 16, 18, 19, 19, 20, 20, 21, 21, 21, 21, 22, 22, 22, 23, 24, 25, 25, 25, 25, 26, 26, 27, 27, 27,
                27, 28, 29, 31, 31, 32, 32, 33, 33, 34, 36, 36, 36, 36, 37, 37, 37, 38, 39, 39, 40, 40, 40}, 25));
    }

    @Test
    void shouldSearchLowestSuccessfulPotionWithMoreDuplicates() {
        assertEquals(0, new SuccessfulPairsOfSpellsAndPotions().searchLowestSuccessfullPotion(new int[]
                {5,11,11,11,17,17,17,17,18,18,19,19,19,20,20,20,22,22,22,22,22,23,24,24,25,25,27,27,28,28,28,28,29,29,29
                        ,30,31,32,32,32,33,33,33,34,34,35,35,35,36,36,36,36,36,36,37,37,37,37,38,38,38,39,39,39,40,40,40,
                        40,40,40,40,40}, 5.0));
    }



}