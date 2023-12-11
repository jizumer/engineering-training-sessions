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
                Day6.loadInputFile("src/test/resources/day6-input.txt"));
    }

    @Test
    public void shouldCalculatePossibleWaysToWin(){
        assertEquals(4,
                Day6.calculatePossibleWaysToWin(7, 9));
        assertEquals(8,
                Day6.calculatePossibleWaysToWin(15, 40));
        assertEquals(9,
                Day6.calculatePossibleWaysToWin(30, 200));
    }

    @Test
    public void shouldCalculateMultOfPossibleWays() throws IOException {
        assertEquals(288,
                Day6.calculateMultOfPossibleWays("src/test/resources/day6-input-small.txt"));
        assertEquals(32076,
                Day6.calculateMultOfPossibleWays("src/test/resources/day6-input.txt"));
    }

}