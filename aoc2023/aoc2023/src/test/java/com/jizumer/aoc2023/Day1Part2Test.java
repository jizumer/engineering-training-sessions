package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


//https://adventofcode.com/2023/day/1
class Day1Part2Test {

    @Test
    public void shouldDetectNumbersWrittenWithLetters(){
        assertEquals(29,new Day1Part2().parseCalibrationValue("two1nine"));
    }

}