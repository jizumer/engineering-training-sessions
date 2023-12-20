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

}