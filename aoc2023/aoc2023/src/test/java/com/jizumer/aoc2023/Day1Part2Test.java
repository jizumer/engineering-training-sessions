package com.jizumer.aoc2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


//https://adventofcode.com/2023/day/1
class Day1Part2Test {

    @ParameterizedTest
    @CsvSource({
            "two1nine, 29",
            "eightwothree, 83",
            "abcone2threexyz, 13",
            "xtwone3four, 24",
            "4nineeightseven2, 42",
            "zoneight234, 14",
            "7pqrstsixteen, 76",
    })
    public void shouldDetectNumbersWrittenWithLetters(String calibrationLine, int expected) {
        assertEquals(expected, new Day1Part2().parseCalibrationValue(calibrationLine));
    }

}