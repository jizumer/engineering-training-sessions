package com.jizumer.aoc2023.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * | is a vertical pipe connecting north and south.
 * - is a horizontal pipe connecting east and west.
 * L is a 90-degree bend connecting north and east.
 * J is a 90-degree bend connecting north and west.
 * 7 is a 90-degree bend connecting south and west.
 * F is a 90-degree bend connecting south and east.
 * Depending on the direction of the pipe, the runner will move from one end to the other.
 * As we know the last position, and the direction of the pipe, we can calculate the next position.
 **/

public class PipeRunner {
    private int[] currentPosition;
    private int[] lastPosition;

    private Character currentPipe = null;

    public PipeRunner(int[] startingPosition, int[] lastPosition) {
        this.currentPosition = startingPosition;
        this.lastPosition = lastPosition;
    }

    public static List<PipeRunner> locateRunnersStartingPoints(int[][] map, int[] initialPoint) {
        List<PipeRunner> runners = new ArrayList<>();

        int northPipe = map[initialPoint[0] - 1][initialPoint[1]];
        int westPipe = map[initialPoint[0]][initialPoint[1] - 1];
        int southPipe = map[initialPoint[0] + 1][initialPoint[1]];
        int eastPipe = map[initialPoint[0]][initialPoint[1] + 1];

        if (northPipe == '|' || northPipe == '7' || northPipe == 'F') {
            runners.add(new PipeRunner(new int[]{initialPoint[0] - 1, initialPoint[1]}, initialPoint));
        }
        if (westPipe == '-' || westPipe == 'L' || westPipe == 'F') {
            runners.add(new PipeRunner(new int[]{initialPoint[0], initialPoint[1] - 1}, initialPoint));
        }
        if (southPipe == '|' || southPipe == 'L' || southPipe == 'J') {
            runners.add(new PipeRunner(new int[]{initialPoint[0] + 1, initialPoint[1]}, initialPoint));
        }
        if (eastPipe == '-' || eastPipe == 'J' || eastPipe == '7') {
            runners.add(new PipeRunner(new int[]{initialPoint[0], initialPoint[1] + 1}, initialPoint));
        }

        if (runners.size() != 2) {
            throw new RuntimeException("Invalid number of starting points found");
        }
        return runners;
    }

    public int[] move(int[][] map) {

        if (currentPipe == null) {
            currentPipe = (char) map[currentPosition[0]][currentPosition[1]];
        }
        Direction[] possibleDirections = Direction.getPossibleDirectionsByPipe(currentPipe);
        int[] possibleNextPosition = simulateMove(possibleDirections[0]);
        if (Arrays.equals(possibleNextPosition, lastPosition)) {
            possibleNextPosition = simulateMove(possibleDirections[1]);
        }

        lastPosition = currentPosition;
        currentPosition = possibleNextPosition;
        currentPipe = (char) map[currentPosition[0]][currentPosition[1]];
        System.out.println(this);
        return currentPosition;
    }

    private int[] simulateMove(Direction direction) {
        return new int[]{currentPosition[0] + direction.getRowIncrement(),
                currentPosition[1] + direction.getColumnIncrement()};
    }

    @Override
    public String toString() {
        return "PipeRunner{" +
                "currentPosition=" + Arrays.toString(currentPosition) +
                ", lastPosition=" + Arrays.toString(lastPosition) +
                ", currentPipe=" + currentPipe +
                ", " + this.hashCode() +
                '}';
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }
}
