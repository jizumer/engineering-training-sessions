package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static java.lang.Character.isDigit;

public class Day1Part2 {

    public int parseCalibrationValue(String calibrationLine) {
        int firstDigit = 0;
        int lastDigit = 0;
        int i = 0;
        while (i < calibrationLine.length()) {
            int extractionAttempt = extractWrittenNumber(calibrationLine.substring(0, i));
            if (extractionAttempt != -1) {
                firstDigit = extractionAttempt;
                break;
            }
            if (isDigit(calibrationLine.charAt(i))) {
                firstDigit = Integer.parseInt(String.valueOf(calibrationLine.charAt(i)));
                break;
            }
            i++;
        }
        i = calibrationLine.length() - 1;
        while (i >= 0) {

            int extractionAttempt = extractWrittenNumber(calibrationLine.substring(i));
            if (extractionAttempt != -1) {
                lastDigit = extractionAttempt;
                break;
            }
            if (isDigit(calibrationLine.charAt(i))) {
                lastDigit = Integer.parseInt(String.valueOf(calibrationLine.charAt(i)));
                break;
            }
            i--;
        }
        return Integer.parseInt(String.valueOf(firstDigit) + lastDigit);
    }

    private int extractWrittenNumber(String substring) {
        if (substring.contains("one")) {
            return 1;
        } else if (substring.contains("two")) {
            return 2;
        } else if (substring.contains("three")) {
            return 3;
        } else if (substring.contains("four")) {
            return 4;
        } else if (substring.contains("five")) {
            return 5;
        } else if (substring.contains("six")) {
            return 6;
        } else if (substring.contains("seven")) {
            return 7;
        } else if (substring.contains("eight")) {
            return 8;
        } else if (substring.contains("nine")) {
            return 9;
        } else if (substring.contains("zero")) {
            return 0;
        } else {
            return -1;

        }
    }

    public int calculateCalibrationValues(String calibrationValuesFilePath) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(calibrationValuesFilePath));
        return bufferedReader
                .lines()
                .mapToInt(this::parseCalibrationValue)
                .sum();
    }
}
