package com.jizumer.aoc2023.day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

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
        List<PipeRunner> runners = PipeRunner.locateRunnersStartingPoints(map, initialPoint);
        PipeRunner runnerA = runners.get(0);
        PipeRunner runnerB = runners.get(1);

        int[] positionOfRunnerA;
        int[] positionOfRunnerB;
        int steps = 1;
        do {
            positionOfRunnerA = runnerA.move(map);
            positionOfRunnerB = runnerB.move(map);
            steps++;
        } while (!isSamePosition(positionOfRunnerA, positionOfRunnerB));
        return steps;
    }

    private boolean isSamePosition(int[] positionOfRunnerA, int[] positionOfRunnerB) {
        return Arrays.equals(positionOfRunnerA, positionOfRunnerB);
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