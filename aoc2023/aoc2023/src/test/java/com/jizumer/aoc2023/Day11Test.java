package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    public void shouldLoadUniverseFromFile() throws FileNotFoundException {
        Day11 day11 = new Day11();
        day11.loadUniverseFromFile("src/test/resources/day11-input-small.txt");
        assertEquals(10, day11.getUniverse().length);
        assertEquals(10, day11.getUniverse()[0].length);
    }

    @Test
    public void shouldExpandUniverse() throws FileNotFoundException {
        Day11 day11 = new Day11();
        day11.loadUniverseFromFile("src/test/resources/day11-input-small.txt");
        assertEquals(12, day11.getExpandedUniverse().length);
        assertEquals(13, day11.getExpandedUniverse()[0].length);
    }

    @Test
    public void shouldSumDistancesBetweenGalaxies() throws FileNotFoundException {
        Day11 day11 = new Day11();
        day11.loadUniverseFromFile("src/test/resources/day11-input-small.txt");
        assertEquals(374, day11.sumDistancesBetweenGalaxies());
    }

}