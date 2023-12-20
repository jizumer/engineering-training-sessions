package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    public void shouldLoadMapFromFile() throws FileNotFoundException {

        int[][] map = new Day10().loadMapFromFile();
        assertEquals(140, map.length);
        assertEquals(140, map[0].length);
    }

}