package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    public void shouldCalculateWiningPointsForAGivenCardInput() {
        assertEquals(8,
                Day4.calculateWinningPointsForLine("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"));

    }

}