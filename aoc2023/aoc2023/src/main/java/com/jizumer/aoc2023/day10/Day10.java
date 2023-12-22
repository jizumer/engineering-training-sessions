package com.jizumer.aoc2023.day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Day10 {

    private int[][] map;
    private int[][] loop;

    public int[][] loadMapFromFile(final String mapFilePath) throws FileNotFoundException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(
                mapFilePath));

        this.map = bufferedReader
                .lines()
                .map(line -> line.chars().toArray())
                .toArray(int[][]::new);
        loop = new int[map.length][map[0].length];
        return this.map;
    }

    public int getFurthestNumberOfSteps() {
        int[] initialPoint = findStartingPoint();
        List<PipeRunner> runners = PipeRunner.locateRunnersStartingPoints(map, initialPoint);
        PipeRunner runnerA = runners.get(0);
        PipeRunner runnerB = runners.get(1);

        for (PipeRunner runner : runners) {
            loop[runner.getCurrentPosition()[0]][runner.getCurrentPosition()[1]] = 1;
        }

        int[] positionOfRunnerA;
        int[] positionOfRunnerB;
        int steps = 1;
        do {
            positionOfRunnerA = runnerA.move(map);
            positionOfRunnerB = runnerB.move(map);

            loop[positionOfRunnerA[0]][positionOfRunnerA[1]] = 1;
            loop[positionOfRunnerB[0]][positionOfRunnerB[1]] = 1;
            steps++;
        } while (!isSamePosition(positionOfRunnerA, positionOfRunnerB));
        return steps;
    }

    private boolean isSamePosition(int[] positionOfRunnerA, int[] positionOfRunnerB) {
        return Arrays.equals(positionOfRunnerA, positionOfRunnerB);
    }

    public int[] findStartingPoint() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == (int) 'S') {
                    loop[i][j] = 1;
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("No starting point found");
    }

    public int getNumberOfTilesEnclosedByTheLoop() {
        throw new RuntimeException("Not implemented yet");
    }
}