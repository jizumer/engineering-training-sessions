package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
    @Test
    public void shouldFindNumberOfArrangementsByConditionRecord() {
        assertEquals(1, new Day12()
                .shouldFindNumberOfArrangementsByConditionRecord("???.### 1,1,3"));

        assertEquals(4, new Day12()
                .shouldFindNumberOfArrangementsByConditionRecord(".??..??...?##. 1,1,3"));

        assertEquals(1, new Day12()
                .shouldFindNumberOfArrangementsByConditionRecord("?#?#?#?#?#?#?#? 1,3,1,6"));

        assertEquals(1, new Day12()
                .shouldFindNumberOfArrangementsByConditionRecord("????.#...#... 4,1,1"));

        assertEquals(4, new Day12()
                .shouldFindNumberOfArrangementsByConditionRecord("????.######..#####. 1,6,5"));

        assertEquals(10, new Day12()
                .shouldFindNumberOfArrangementsByConditionRecord("?###???????? 3,2,1"));
    }

    @Test
    public void shouldDetectTheRightGroupOfBrokenSpringSizes() {
        assertArrayEquals(
                new String[]{"1", "1", "3"},
                new Day12().detectGroupOfBrokenSpringSizes("#.#.###".toCharArray()));

        assertArrayEquals(
                new String[]{"10"},
                new Day12().detectGroupOfBrokenSpringSizes("##########".toCharArray()));

        assertArrayEquals(
                new String[]{},
                new Day12().detectGroupOfBrokenSpringSizes("..........".toCharArray()));

        assertArrayEquals(
                new String[]{"1", "2", "3"},
                new Day12().detectGroupOfBrokenSpringSizes(".#.##.###.".toCharArray()));
    }

}