package com.jizumer.aoc2023.day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

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
        int[] initialPoint = findStartingPoint(map);
        PipeRunner[] runners = PipeRunner.locateRunnersStartingPoints(map, initialPoint);
        PipeRunner runnerA = runners[0];
        PipeRunner runnerB = runners[1];

        int steps = 0;
        do {
            move(runnerA, map);
            move(runnerB, map);
            steps++;
        } while (!isSamePosition(runnerA, runnerB));
        return steps;
    }

    private boolean isSamePosition(int[] runnerA, int[] runnerB) {
        return Arrays.equals(runnerA, runnerB);
    }

    private void move(int[] runner, int[][] map) {

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