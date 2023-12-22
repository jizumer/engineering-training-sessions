package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 {

    private int[][] universe;
    private int[][] expandedUniverse;

    private final List<int[]> galaxies = new ArrayList<>();

    public void loadUniverseFromFile(String path) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        universe = reader
                .lines()
                .map(line -> line.chars().toArray())
                .toArray(int[][]::new);

        expandUniverse();
        findGalaxies();
    }

    private void findGalaxies() {
        for (int i = 0; i < expandedUniverse.length; i++) {
            for (int j = 0; j < expandedUniverse[i].length; j++) {
                if (expandedUniverse[i][j] == '#') {
                    galaxies.add(new int[]{i, j});
                }
            }
        }
    }

    private void expandUniverse() {
        List<Integer> emptyRows = findEmptyRows();
        List<Integer> emptyColumns = findEmptyColumns();

        initializeExpandedUniverse(emptyRows, emptyColumns);
        fillExpandedUniverse(emptyRows, emptyColumns);

    }

    private void fillExpandedUniverse(List<Integer> emptyRows, List<Integer> emptyColumns) {
        int rowOffset = 0;
        int columnOffset = 0;
        for (int i = 0; i < universe.length; i++) {
            if (emptyRows.contains(i)) {
                fillEmptyRow(i + rowOffset);
                rowOffset++;
            }
            for (int j = 0; j < universe[i].length; j++) {
                if (emptyColumns.contains(j)) {
                    expandedUniverse[i + rowOffset][j + columnOffset] = '.';
                    columnOffset++;
                }
                expandedUniverse[i + rowOffset][j + columnOffset] = universe[i][j];
            }
            columnOffset = 0;
        }

    }

    private void fillEmptyRow(int row) {
        Arrays.fill(expandedUniverse[row], '.');
    }


    private void initializeExpandedUniverse(List<Integer> emptyRows, List<Integer> emptyColumns) {
        expandedUniverse = new int[universe.length + emptyRows.size()]
                [universe[0].length + emptyColumns.size()];
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

    public int[][] getUniverse() {
        return universe;
    }

    public int[][] getExpandedUniverse() {
        return expandedUniverse;
    }

    public int sumDistancesBetweenGalaxies() {

        int sum = 0;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                sum += distanceBetweenGalaxies(galaxies.get(i), galaxies.get(j));
            }
        }
        return sum;
    }

    private int distanceBetweenGalaxies(int[] galaxy1, int[] galaxy2) {
        return Math.abs(galaxy1[0] - galaxy2[0]) + Math.abs(galaxy1[1] - galaxy2[1]);
    }
}
