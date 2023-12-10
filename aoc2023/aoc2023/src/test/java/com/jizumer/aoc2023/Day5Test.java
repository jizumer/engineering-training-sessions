package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    public static final String SMALL_INPUT_PATH = "src/test/resources/day5-input-small.txt";
    public static final String LARGE_INPUT_PATH = "src/test/resources/day5-input.txt";

    @Test
    public void shouldLoadAlmanacFromFileContainingInitialSeeds() throws IOException {
        assertEquals(List.of(79, 14, 55, 13), new Day5().loadAlmanac(SMALL_INPUT_PATH).getSeeds());
    }

}