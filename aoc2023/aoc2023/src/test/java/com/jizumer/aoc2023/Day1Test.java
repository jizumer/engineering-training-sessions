package com.jizumer.aoc2023;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

class Day1Test {


    @Test
    void shouldLoadInputFileAndCalculateSum() throws IOException {
        Assertions.assertEquals(53334,
                new Day1().calculateCalibrationValues("src/test/resources/input.txt"));
    }


    @ParameterizedTest
    @CsvSource({
            "abc123def, 13",
            "1abc2, 12",
            "pqr3stu8vwx, 38",
            "a1b2c3d4e5f, 15",
            "treb7uchet, 77"
    })
    void shouldParseCalibrationValue(String calibrationValue, String expected) {
        Assertions.assertEquals(Integer.parseInt(expected),
                new Day1().parseCalibrationValue(calibrationValue));
    }

}