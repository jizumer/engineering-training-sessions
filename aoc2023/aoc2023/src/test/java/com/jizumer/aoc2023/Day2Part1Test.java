package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day2Part1Test {

    @Test
    public void shouldLoadTheListOfGamesByFileName() throws FileNotFoundException {
        assertEquals(100,
                new Day2Part1()
                        .loadGames("src/test/resources/day2-input.txt")
                        .size());
    }

}