package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day11Part2 {

    private int[][] universe;


    private final List<long[]> galaxies = new ArrayList<>();

    public void loadUniverseFromFile(String path) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        universe = reader
                .lines()
                .map(line -> line.chars().toArray())
                .toArray(int[][]::new);
        findGalaxiesBeforeExpansion();
        expandUniverse();

    }

    private void findGalaxiesBeforeExpansion() {
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                if (universe[i][j] == '#') {
                    galaxies.add(new long[]{i, j});
                }
            }
        }
    }

    private void expandUniverse() {
        List<Integer> emptyRows = findEmptyRows();
        List<Integer> emptyColumns = findEmptyColumns();

        offsetGalaxies(emptyRows, emptyColumns);

    }

    private void offsetGalaxies(List<Integer> emptyRows, List<Integer> emptyColumns) {

        for (long[] galaxy : galaxies) {
            long rowsToExpand = countLowerThan(galaxy[0], emptyRows);
            galaxy[0] += (1000000L * rowsToExpand) - rowsToExpand;

            long columnsToExpand = countLowerThan(galaxy[1], emptyColumns);
            galaxy[1] += (1000000L * columnsToExpand) - columnsToExpand;
        }
    }

    private long countLowerThan(long i, List<Integer> empties) {
        return empties.stream().filter(empty -> empty < i).count();
    }

    private List<Integer> findEmptyColumns() {
        List<Integer> emptyColumns = new ArrayList<>();
        for (int i = 0; i < universe[0].length; i++) {
            boolean columnIsEmpty = true;
            for (int[] ints : universe) {
                if (ints[i] == '#') {
                    columnIsEmpty = false;
                    break;
                }
            }
            if (columnIsEmpty) {
                emptyColumns.add(i);
            }
        }
        return emptyColumns;
    }

    private List<Integer> findEmptyRows() {
        List<Integer> emptyRows = new ArrayList<>();
        for (int i = 0; i < universe.length; i++) {
            boolean rowIsEmpty = true;
            for (int j = 0; j < universe[i].length; j++) {
                if (universe[i][j] == '#') {
                    rowIsEmpty = false;
                    break;
                }
            }
            if (rowIsEmpty) {
                emptyRows.add(i);
            }
        }
        return emptyRows;
    }

    public long sumDistancesBetweenGalaxies() {

        long sum = 0;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                long distance = distanceBetweenGalaxies(galaxies.get(i), galaxies.get(j));
                sum += distance;
            }
        }
        return sum;
    }

    private long distanceBetweenGalaxies(long[] galaxy1, long[] galaxy2) {
        long verticalDistance = Math.abs(galaxy1[0] - galaxy2[0]);
        long horizontalDistance = Math.abs(galaxy1[1] - galaxy2[1]);
        return verticalDistance + horizontalDistance;
    }
}
