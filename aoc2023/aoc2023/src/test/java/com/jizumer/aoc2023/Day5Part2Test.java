package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Part2Test {
    public static final String SMALL_INPUT_PATH = "src/test/resources/day5-input-small.txt";

    @Test
    public void shouldLoadTheRightNumberOfSeedsFromFile() throws IOException {
        assertEquals(27,
                new Day5Part2()
                        .loadAlmanac(SMALL_INPUT_PATH)
                        .getSeeds().size());
    }

    @Test
    public void shouldCalculateTheRightLowestLocationNumber() throws IOException {
        assertEquals(46, new Day5Part2()
                .loadAlmanac(SMALL_INPUT_PATH)
                .calculateLowestLocationPerSeed());
    }

}