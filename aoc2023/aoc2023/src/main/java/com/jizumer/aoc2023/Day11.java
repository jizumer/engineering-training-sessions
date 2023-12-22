package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Day11 {

    private int[][] universe;

    public void loadUniverseFromFile(String path) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        universe = reader
                .lines()
                .map(line -> line.chars().map(c -> c == '.' ? 0 : 1).toArray())
                .toArray(int[][]::new);
    }

    public int[][] getUniverse() {
        return universe;
    }
}
