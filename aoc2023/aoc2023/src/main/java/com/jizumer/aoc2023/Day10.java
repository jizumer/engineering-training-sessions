package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Day10 {
    public int[][] loadMapFromFile(final String mapFilePath) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(
                mapFilePath));

        return bufferedReader
                .lines()
                .map(line -> line.chars().toArray())
                .toArray(int[][]::new);
    }

    public int getFurthestNumberOfSteps(int[][] map) {
        int[] runnerA = findStartingPoint(map);
        int[] runnerB = runnerA.clone();

        throw new RuntimeException("Not implemented yet");
    }

    public int[] findStartingPoint(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == (int) 'S') {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("No starting point found");
    }
}