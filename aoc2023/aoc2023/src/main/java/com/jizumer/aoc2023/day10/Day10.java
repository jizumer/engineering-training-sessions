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
        int numberOfTimesCrossedTowardsEast = getNumberOfTimesCrossedTowardsEast(i, j);
        int numberOfTimesCrossedTowardsSouth = getNumberOfTimesCrossedTowardsSouth(i, j);
        int numberOfTimesCrossedTowardsWest = getNumberOfTimesCrossedTowardsWest(i, j);
        int numberOfTimesCrossedTowardsNorth = getNumberOfTimesCrossedTowardsNorth(i, j);


        return (numberOfTimesCrossedTowardsSouth % 2 == 1
                || numberOfTimesCrossedTowardsEast % 2 == 1
                || numberOfTimesCrossedTowardsWest % 2 == 1
                || numberOfTimesCrossedTowardsNorth % 2 == 1);
    }

    private int getNumberOfTimesCrossedTowardsNorth(int i, int j) {
        int longitudinalPipes = '|';
        Direction direction = Direction.NORTH;
        return countIntersectionsTowardsDirection(i, j, direction, longitudinalPipes);
    }

    private int getNumberOfTimesCrossedTowardsWest(int i, int j) {
        int longitudinalPipes = '-';
        Direction direction = Direction.WEST;
        return countIntersectionsTowardsDirection(i, j, direction, longitudinalPipes);
    }


    private int getNumberOfTimesCrossedTowardsSouth(int i, int j) {
        int longitudinalPipes = '|';
        Direction direction = Direction.SOUTH;
        return countIntersectionsTowardsDirection(i, j, direction, longitudinalPipes);
    }

    private int getNumberOfTimesCrossedTowardsEast(int i, int j) {
        int longitudinalPipes = '-';
        Direction direction = Direction.EAST;
        return countIntersectionsTowardsDirection(i, j, direction, longitudinalPipes);
    }

    private int countIntersectionsTowardsDirection(int i, int j, Direction direction, int longitudinalPipes) {
        int numberOfTimesCrossed = 0;
        int[] currentPosition = new int[]{i, j};
        int[] nextPosition;
        int lastCorner = 0;
        do {
            nextPosition = getNextPosition(currentPosition, direction);
            if (map[nextPosition[0]][nextPosition[1]] == (int) 'S') {
                //we can ignore the case when we intersect the S
                return 0;
            }
            if (loop[nextPosition[0]][nextPosition[1]] == 1
                    && map[nextPosition[0]][nextPosition[1]] != longitudinalPipes) {
                if (isCorner(map[nextPosition[0]][nextPosition[1]])) {
                    if (lastCorner == 0) {
                        lastCorner = map[nextPosition[0]][nextPosition[1]];
                    } else if (isOppositeCorner(lastCorner, map[nextPosition[0]][nextPosition[1]])) {
                        //if both corners are opposite, drawing an S, it means that we are crossing the loop.
                        numberOfTimesCrossed++;
                        lastCorner = 0;
                    } else {
                        //if they are not opposite, it means that we are drawing a C,
                        // and we are not crossing the loop (or crossing it twice).
                        lastCorner = 0;
                    }
                } else {
                    numberOfTimesCrossed++;
                }
            }
            currentPosition = nextPosition;
        } while (!isBorder(nextPosition[0], nextPosition[1]));
        return numberOfTimesCrossed;
    }

    private boolean isOppositeCorner(int lastCorner, int corner) {
        return (lastCorner == (int) 'L' && corner == (int) '7')
                || (lastCorner == (int) '7' && corner == (int) 'L')
                || (lastCorner == (int) 'J' && corner == (int) 'F')
                || (lastCorner == (int) 'F' && corner == (int) 'J');
    }

    private boolean isCorner(int i) {
        return i == (int) 'L' || i == (int) 'J' || i == (int) '7' || i == (int) 'F';
    }


    private boolean isBorder(int i, int j) {
        return i == 0 || j == 0 || i == map.length - 1 || j == map[0].length - 1;
    }

    private int[] getNextPosition(int[] currentPosition, Direction direction) {
        //we will trace a path to the border of the map towards the right, and count how many times it crosses the loop.
        // If it crosses the loop an odd number of times, it means that the tile is enclosed by the loop.
        return switch (direction) {
            case NORTH -> new int[]{currentPosition[0] - 1, currentPosition[1]};
            case SOUTH -> new int[]{currentPosition[0] + 1, currentPosition[1]};
            case WEST -> new int[]{currentPosition[0], currentPosition[1] - 1};
            case EAST -> new int[]{currentPosition[0], currentPosition[1] + 1};
        };
    }
}