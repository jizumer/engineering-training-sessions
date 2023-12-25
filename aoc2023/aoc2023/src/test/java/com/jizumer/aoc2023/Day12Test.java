package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
    @Test
    public void shouldFindNumberOfArrangementsByConditionRecord() {
        assertEquals(1, new Day12()
                .findNumberOfArrangementsByConditionRecord("???.### 1,1,3"));

        assertEquals(4, new Day12()
                .findNumberOfArrangementsByConditionRecord(".??..??...?##. 1,1,3"));

        assertEquals(1, new Day12()
                .findNumberOfArrangementsByConditionRecord("?#?#?#?#?#?#?#? 1,3,1,6"));

        assertEquals(1, new Day12()
                .findNumberOfArrangementsByConditionRecord("????.#...#... 4,1,1"));

        assertEquals(4, new Day12()
                .findNumberOfArrangementsByConditionRecord("????.######..#####. 1,6,5"));

        assertEquals(10, new Day12()
                .findNumberOfArrangementsByConditionRecord("?###???????? 3,2,1"));
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

    @Test
    public void shouldCalculateTheSumOfArrangeOptionsForASmallConditionRecord() throws FileNotFoundException {
        assertEquals(21,
                new Day12()
                        .findSumOfArrangementsByConditionRecordFile(
                                "src/test/resources/day12-input-small.txt"));
    }

    @Test
    public void shouldCalculateTheSumOfArrangeOptionsForAConditionRecord() throws FileNotFoundException {
        assertEquals(7047,
                new Day12()
                        .findSumOfArrangementsByConditionRecordFile(
                                "src/test/resources/day12-input.txt"));
    }

    @Test
    public void shouldExpandConditionRecords() {
        assertEquals(".#?.#?.#?.#?.# 1,1,1,1,1",
                new Day12().expandConditionRecord(".# 1"));
    }

}