package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @Test
    public void shouldLoadInputFile() throws IOException {

        assertEquals(Map.of(44, 208,
                        80, 1581,
                        65, 1050,
                        72, 1102),
                new Day6().loadInputFile("src/test/resources/day6-input.txt"));
    }

}