package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    @ParameterizedTest
    @CsvSource({
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53,8",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19,2",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1, 2",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83,1",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36,0",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11,0"
    })
    public void shouldCalculateWiningPointsForAGivenCardInput(String line, int expectedPoints) {
        assertEquals(expectedPoints,
                Day4.calculateWinningPointsForLine(line));

    }

    @Test
    public void shouldCalculateSumOfWiningPointsForAGivenFilePath() throws FileNotFoundException {
        assertEquals(23441,
                Day4.calculateSumOfWinningPointsForFile("src/test/resources/day4-input.txt"));
    }

    @Test
    public void shouldCalculateTotalNumOfCardsForAGivenSmallFile() throws FileNotFoundException {
        assertEquals(30,
                Day4.calculateTotalNumOfCardsForAGivenFile("src/test/resources/day4-input-small.txt"));
    }

    @Test
    public void shouldCalculateTotalNumOfCardsForAGivenFile() throws FileNotFoundException {
        assertEquals(5923918,
                Day4.calculateTotalNumOfCardsForAGivenFile("src/test/resources/day4-input.txt"));
    }

}