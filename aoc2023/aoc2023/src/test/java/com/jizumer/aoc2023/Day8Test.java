package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

    @Test
    public void shouldCalculateSteps() throws Exception {
        Day8 day8 = new Day8();
        int result = day8.calculateSteps("src/test/resources/day8-input.txt");
        assertEquals(17141, result);
    }

    @Test
    public void shouldCreateDirectionEnumFromChar() {
        assertEquals(Direction.L, Direction.valueOf(
                String.valueOf((char) 76)));
        assertEquals(Direction.R, Direction.valueOf(
                String.valueOf((char) 82)));

    }


    @Test
    public void shouldCalculateStepsForASmallInput() throws Exception {
        Day8 day8 = new Day8();
        int result = day8.calculateSteps("src/test/resources/day8-input-small.txt");
        assertEquals(6, result);
    }
}