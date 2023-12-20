package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Day10 {
    public int[][] loadMapFromFile() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(
                "src/test/resources/day10-input.txt"));

        return bufferedReader
                .lines()
                .map(line -> line.chars().toArray())
                .toArray(int[][]::new);
    }
}