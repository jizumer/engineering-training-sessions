package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {
    private static final String SMALL_INPUT = "src/test/resources/day3-input-small.txt";

    @Test
    public void shouldCalculateSumOfPartNumbers() throws IOException {
        assertEquals(4361, new Day3().calculateSumOfPartNumbers(SMALL_INPUT));
    }

    @Test
    public void shouldCalculateSumOfGearRatiosForSmallInput() throws IOException {
        assertEquals(467835, new Day3().calculateSumOfGearRatios(SMALL_INPUT));
    }

    @Test
    public void shouldCalculateSumOfGearRatiosForLargeInput() throws IOException {
        assertEquals(84289137, new Day3().calculateSumOfGearRatios("src/test/resources/day3-input.txt"));
    }

    @Test
    public void shouldCalculateSumOfPartNumbersForLargeInput() throws IOException {
        assertEquals(525181, new Day3().calculateSumOfPartNumbers("src/test/resources/day3-input.txt"));
    }

    @ParameterizedTest
    @CsvSource({
            "617*......,0,617",
            "..592.....,2,592",
            "..467..114,7,114",
            "..*25,3,25",
            "2147483647,0,2147483647"
    })
    public void shouldExtractNumbersFromLines(String line, int index, int expected) {
        assertEquals(expected, new Day3().extractNumber(line, index));
    }

}