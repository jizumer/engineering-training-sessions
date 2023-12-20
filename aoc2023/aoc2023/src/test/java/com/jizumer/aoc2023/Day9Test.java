package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    @Test
    public void shouldLoadInputFile() throws FileNotFoundException {
        assertEquals(3,
                new Day9()
                        .loadReportFromFile("src/test/resources/day9-input-small.txt")
                        .count());
    }

    @Test
    public void shouldFindNextValueForHistory() {
        assertEquals(18,
                new Day9()
                        .findNextValueForHistory(new Integer[]{0, 3, 6, 9, 12, 15}));
        assertEquals(28,
                new Day9()
                        .findNextValueForHistory(new Integer[]{1, 3, 6, 10, 15, 21}));
    }

    @Test
    public void shouldFindPreviousValueForHistory() {
        assertEquals(-3,
                new Day9()
                        .findPreviousValueForHistory(new Integer[]{0, 3, 6, 9, 12, 15}));
        assertEquals(0,
                new Day9()
                        .findPreviousValueForHistory(new Integer[]{1, 3, 6, 10, 15, 21}));
        assertEquals(5,
                new Day9()
                        .findPreviousValueForHistory(new Integer[]{10 ,13, 16, 21, 30, 45}));
    }

    @Test
    public void shouldCalculateTheSumOfTheNextValueOfEachHistory() throws FileNotFoundException {
        assertEquals(114,
                new Day9()
                        .sumOfNextValuesOfEachHistory("src/test/resources/day9-input-small.txt"));
    }

    @Test
    public void shouldCalculateTheSumOfTheNextValueOfEachHistoryForTheBigInput() throws FileNotFoundException {
        assertEquals(1868368343,
                new Day9()
                        .sumOfNextValuesOfEachHistory("src/test/resources/day9-input.txt"));
    }

    @Test
    public void shouldCalculateTheSumOfThePreviousValueOfEachHistory() throws FileNotFoundException {
        assertEquals(2,
                new Day9()
                        .sumOfPreviousValuesOfEachHistory("src/test/resources/day9-input-small.txt"));
    }

    @Test
    public void shouldCalculateTheSumOfThePreviousValueOfEachHistoryForTheBigInput() throws FileNotFoundException {
        assertEquals(1022,
                new Day9()
                        .sumOfPreviousValuesOfEachHistory("src/test/resources/day9-input.txt"));
    }


}