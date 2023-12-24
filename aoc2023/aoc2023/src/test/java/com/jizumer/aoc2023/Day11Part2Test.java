package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day11Part2Test {

    @Test
    public void shouldSumDistancesBetweenGalaxiesForASmallInput() throws FileNotFoundException {
        Day11Part2 day11Part2 = new Day11Part2();
        day11Part2.loadUniverseFromFile("src/test/resources/day11-input-small.txt");
        assertEquals(82000210L, day11Part2.sumDistancesBetweenGalaxies());
    }

    @Test
    public void shouldSumDistancesBetweenGalaxiesForALargeInput() throws FileNotFoundException {
        Day11Part2 day11Part2 = new Day11Part2();
        day11Part2.loadUniverseFromFile("src/test/resources/day11-input.txt");
        assertEquals(678728808158L, day11Part2.sumDistancesBetweenGalaxies());
    }

}