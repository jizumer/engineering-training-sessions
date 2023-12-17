package com.jizumer.aoc2023.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {


    @Test
    public void shouldCreateDirectionEnumFromChar() {
        assertEquals(Direction.L, Direction.valueOf(
                String.valueOf((char) 76)));
        assertEquals(Direction.R, Direction.valueOf(
                String.valueOf((char) 82)));

    }

    @Test
    public void shouldCalculateStepsInGhostMode() throws Exception {
        Day8 day8 = new Day8("src/test/resources/day8-ghost-input.txt");
        assertEquals(6, day8.traverseMapInGhostMode());
    }

    @Test
    public void shouldCalculateStepsInGhostModeForALargeInput() throws Exception {
        Day8 day8 = new Day8("src/test/resources/day8-input.txt");
        assertEquals(10818234074807L, day8.traverseMapInGhostMode());
    }

}