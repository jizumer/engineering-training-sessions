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

}