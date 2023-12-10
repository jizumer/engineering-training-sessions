package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5Part2Test {
    public static final String SMALL_INPUT_PATH = "src/test/resources/day5-input-small.txt";

    @Test
    public void shouldLoadTheRightNumberOfSeedsFromFile() throws Exception {
        assertEquals(27,
                new Day5Part2()
                        .loadAlmanac(SMALL_INPUT_PATH)
                        .getSeeds().size());
    }

}