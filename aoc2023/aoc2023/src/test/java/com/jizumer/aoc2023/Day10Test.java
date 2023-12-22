package com.jizumer.aoc2023;

import com.jizumer.aoc2023.day10.Day10;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Day10 day10 = new Day10();
        day10.loadMapFromFile("src/test/resources/day10-input-small.txt");
        assertEquals(4, day10.getFurthestNumberOfSteps());
    }

    @Test
    public void shouldReturnFurthestNumberOfStepsForTheLargeInput() throws FileNotFoundException {
        Day10 day10 = new Day10();
        day10.loadMapFromFile("src/test/resources/day10-input.txt");
        assertEquals(6815, day10.getFurthestNumberOfSteps());
    }

    @Test
    public void shouldFindStartingPoint() throws FileNotFoundException {
        Day10 day10 = new Day10();
        day10.loadMapFromFile("src/test/resources/day10-input-small.txt");
        assertArrayEquals(new int[]{1, 1}, day10.findStartingPoint());
    }

    @Test
    public void shouldFindStartingPointForTheLargeInput() throws FileNotFoundException {
        Day10 day10 = new Day10();
        day10.loadMapFromFile("src/test/resources/day10-input.txt");
        assertArrayEquals(new int[]{62, 61}, day10.findStartingPoint());
    }

    @Test
    public void shouldReturnNumberOfTilesEnclosedByTheLoop() throws FileNotFoundException {
        Day10 day10 = new Day10();
        day10.loadMapFromFile("src/test/resources/day10-part2-input-easy.txt");
        assertEquals(4, day10.getNumberOfTilesEnclosedByTheLoop());
    }

    @Test
    public void shouldReturnNumberOfTilesEnclosedByTheLoopForTheMediumInput() throws FileNotFoundException {
        Day10 day10 = new Day10();
        day10.loadMapFromFile("src/test/resources/day10-part2-input-medium.txt");
        assertEquals(4, day10.getNumberOfTilesEnclosedByTheLoop());
    }

    @Test
    public void shouldReturnNumberOfTilesEnclosedByTheLoopForTheHardInput() throws FileNotFoundException {
        Day10 day10 = new Day10();
        day10.loadMapFromFile("src/test/resources/day10-part2-input-hard.txt");
        assertEquals(8, day10.getNumberOfTilesEnclosedByTheLoop());
    }

    @Test
    public void shouldReturnNumberOfTilesEnclosedByTheLoopForTheHarderInput() throws FileNotFoundException {
        Day10 day10 = new Day10();
        day10.loadMapFromFile("src/test/resources/day10-part2-input-harder.txt");
        assertEquals(10, day10.getNumberOfTilesEnclosedByTheLoop());
    }

    @Test
    public void shouldReturnNumberOfTilesEnclosedByTheLoopForTheLargeInput() throws FileNotFoundException {
        Day10 day10 = new Day10();
        day10.loadMapFromFile("src/test/resources/day10-input.txt");
        assertEquals(269, day10.getNumberOfTilesEnclosedByTheLoop());
    }

}