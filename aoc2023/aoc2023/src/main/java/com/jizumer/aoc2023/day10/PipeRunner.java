package com.jizumer.aoc2023.day10;

import java.util.Arrays;

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

    public PipeRunner(int[] startingPosition, int[] lastPosition) {
        this.currentPosition = startingPosition;
        this.lastPosition = lastPosition;
    }

    public static PipeRunner[] locateRunnersStartingPoints(int[][] map, int[] initialPoint) {
        // We will traverse all the surrounding positions of the initial point to identify the possible starting points.
        throw new RuntimeException("Not implemented");

    }

    public int[] move(int[][] map) {

        int pipe = map[currentPosition[0]][currentPosition[1]];
        Direction[] possibleDirections = Direction.getPossibleDirectionsByPipe(pipe);
        int[] possibleNextPosition = simulateMove(possibleDirections[0]);
        if (Arrays.equals(possibleNextPosition, lastPosition)) {
            possibleNextPosition = simulateMove(possibleDirections[1]);
        }

        lastPosition = currentPosition;
        currentPosition = possibleNextPosition;
        return currentPosition;
    }

    private int[] simulateMove(Direction direction) {
        return new int[]{currentPosition[0] + direction.getRowIncrement(),
                currentPosition[1] + direction.getColumnIncrement()};
    }
}
