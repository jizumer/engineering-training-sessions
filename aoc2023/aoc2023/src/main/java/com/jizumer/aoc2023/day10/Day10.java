package com.jizumer.aoc2023.day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Day10 {

    private int[][] map;
    private int[][] loop;

    private int stepsToReachTheLoop;

    public int[][] loadMapFromFile(final String mapFilePath) throws FileNotFoundException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(
                mapFilePath));

        this.map = bufferedReader
                .lines()
                .map(line -> line.chars().toArray())
                .toArray(int[][]::new);
        loop = new int[map.length][map[0].length];
        traverseTheLoop();
        return this.map;
    }

    public int getFurthestNumberOfSteps() {
        return stepsToReachTheLoop;
    }

    private void traverseTheLoop() {
        int[] initialPoint = findStartingPoint();
        List<PipeRunner> runners = PipeRunner.locateRunnersStartingPoints(map, initialPoint);
        PipeRunner runnerA = runners.get(0);
        PipeRunner runnerB = runners.get(1);

        for (PipeRunner runner : runners) {
            loop[runner.getCurrentPosition()[0]][runner.getCurrentPosition()[1]] = 1;
        }

        int[] positionOfRunnerA;
        int[] positionOfRunnerB;
        this.stepsToReachTheLoop = 1;
        do {
            positionOfRunnerA = runnerA.move(map);
            positionOfRunnerB = runnerB.move(map);

            loop[positionOfRunnerA[0]][positionOfRunnerA[1]] = 1;
            loop[positionOfRunnerB[0]][positionOfRunnerB[1]] = 1;
            stepsToReachTheLoop++;
        } while (!isSamePosition(positionOfRunnerA, positionOfRunnerB));
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
        int numberOfTilesEnclosedByTheLoop = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (loop[i][j] == 0
                        && !isBorder(i, j)
                        && isEnclosed(i, j)) {
                    numberOfTilesEnclosedByTheLoop++;
                }
            }
        }

        return numberOfTilesEnclosedByTheLoop;

    }

    private boolean isEnclosed(int i, int j) {
        // Starting from the given position, we will check if there is a path to the border of the map,
        // and how many times it crosses the loop.
        // If it crosses the loop an odd number of times, it means that the tile is enclosed by the loop.
        int numberOfTimesCrossed = 0;
        int[] currentPosition = new int[]{i, j};
        int[] nextPosition;
        do {
            nextPosition = getNextPosition(currentPosition);
            if (loop[nextPosition[0]][nextPosition[1]] == 1) {
                numberOfTimesCrossed++;
            }
            currentPosition = nextPosition;
        } while (!isBorder(nextPosition[0], nextPosition[1]));

        System.out.println("Position: " + i + "," + j + " crosses the loop "
                + numberOfTimesCrossed + " times, so it is " + (numberOfTimesCrossed % 2 == 1 ? "enclosed"
                : "not enclosed") + ".");
        return numberOfTimesCrossed % 2 == 1;
    }

    private boolean isBorder(int i, int j) {
        return i == 0 || j == 0 || i == map.length - 1 || j == map[0].length - 1;
    }

    private int[] getNextPosition(int[] currentPosition) {
        //we will trace a path to the border of the map towards the right, and count how many times it crosses the loop.
        // If it crosses the loop an odd number of times, it means that the tile is enclosed by the loop.
        return new int[]{currentPosition[0], currentPosition[1] + 1};
    }
}