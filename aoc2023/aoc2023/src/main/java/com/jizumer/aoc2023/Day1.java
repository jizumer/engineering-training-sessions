package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

// https://adventofcode.com/2023/day/1
public class Day1 {

    public int calculateCalibrationValues(String filePath) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        return bufferedReader.lines()
                .mapToInt(this::parseCalibrationValue)
                .sum();
    }

    public int parseCalibrationValue(String s) {
        char firstDigit = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isNumber(s.charAt(i))) {
                firstDigit = s.charAt(i);
                break;
            }
        }
        char lastDigit = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (isNumber(s.charAt(i))) {
                lastDigit = s.charAt(i);
                break;
            }
        }

        return Integer.parseInt(firstDigit + String.valueOf(lastDigit));
    }

    private boolean isNumber(char c) {
        try {
            Integer.parseInt(String.valueOf(c));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}