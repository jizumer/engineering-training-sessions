package com.jizumer.aoc2023.day10;

public enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);

    private final int rowIncrement;
    private final int columnIncrement;

    Direction(int rowIncrement, int columnIncrement) {
        this.rowIncrement = rowIncrement;
        this.columnIncrement = columnIncrement;
    }

    public static Direction[] getPossibleDirectionsByPipe(int pipe) {
        return switch (pipe) {
            case '|' -> new Direction[]{NORTH, SOUTH};
            case '-' -> new Direction[]{EAST, WEST};
            case 'L' -> new Direction[]{NORTH, EAST};
            case 'J' -> new Direction[]{NORTH, WEST};
            case '7' -> new Direction[]{SOUTH, WEST};
            case 'F' -> new Direction[]{SOUTH, EAST};
            default -> throw new RuntimeException("Invalid pipe");
        };
    }

    public int getRowIncrement() {
        return rowIncrement;
    }

    public int getColumnIncrement() {
        return columnIncrement;
    }
}
