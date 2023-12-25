package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {
    @Test
    public void shouldFindNumberOfArrangementsByConditionRecord() {
        assertEquals(1, new Day12()
                .shouldFindNumberOfArrangementsByConditionRecord("???.### 1,1,3"));
    }

    @Test
    public void shouldDetectTheRightGroupOfBrokenSpringSizes() {
        assertEquals(
                List.of(1, 1, 3),
                new Day12().detectGroupOfBrokenSpringSizes("#.#.###".toCharArray()));

        assertEquals(
                List.of(10),
                new Day12().detectGroupOfBrokenSpringSizes("##########".toCharArray()));

        assertEquals(
                new ArrayList<>(),
                new Day12().detectGroupOfBrokenSpringSizes("..........".toCharArray()));

        assertEquals(
                List.of(1, 2, 3),
                new Day12().detectGroupOfBrokenSpringSizes(".#.##.###.".toCharArray()));
    }

}