package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    public void shouldLoadMapFromFile() throws FileNotFoundException {

        int[][] map = new Day10()
                .loadMapFromFile("src/test/resources/day10-input.txt");
        assertEquals(140, map.length);
        assertEquals(140, map[0].length);
    }

    @Test
    public void shouldReturnFurthestNumberOfStepsForTheSmallInput() throws FileNotFoundException {
        int[][] map = new Day10()
                .loadMapFromFile("src/test/resources/day10-input-small.txt");
        assertEquals(4, new Day10().getFurthestNumberOfSteps(map));
    }

    @Test
    public void shouldFindStartingPoint() throws FileNotFoundException {
        int[][] map = new Day10()
                .loadMapFromFile("src/test/resources/day10-input-small.txt");
        assertArrayEquals(new int[]{1, 1}, new Day10().findStartingPoint(map));
    }

    @Test
    public void shouldFindStartingPointForTheLargeInput() throws FileNotFoundException {
        int[][] map = new Day10()
                .loadMapFromFile("src/test/resources/day10-input.txt");
        assertArrayEquals(new int[]{62, 61}, new Day10().findStartingPoint(map));
    }

}