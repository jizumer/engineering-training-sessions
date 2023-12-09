package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

public class Day3 {

    private final List<GearNumber> gearNumbers = new ArrayList<>();
    private final List<int[]> coordinatesOfSymbols = new ArrayList<>();


    public int calculateGearRatios(String engineSchematicFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(engineSchematicFilePath));
        int lineNumber = 0;
        while (reader.ready()) {
            processLine(reader.readLine(), lineNumber);
            lineNumber++;
        }
        reader.close();
        calculateSurroundedNumbers();
        return calculateSumOfGearNumbers();

    }

    private void calculateSurroundedNumbers() {
        for(GearNumber gearNumber : gearNumbers){
            for(int[] coordinates : coordinatesOfSymbols){
                if(isAdjacent(gearNumber, coordinates)){
                    gearNumber.setSurroundedBySymbol(true);
                    break;
                }
            }
        }
    }

    private boolean isAdjacent(GearNumber gearNumber, int[] coordinatesOfSymbol) {
        int gearNumberLength = String.valueOf(gearNumber.getValue()).length();
        if(coordinatesOfSymbol[0] < gearNumber.getX() - 1 || coordinatesOfSymbol[0] > gearNumber.getX() + 1){
            return false;
        }
        return coordinatesOfSymbol[1] >= gearNumber.getY() - 1 && coordinatesOfSymbol[1] <= gearNumber.getY() + gearNumberLength;
    }

    private int calculateSumOfGearNumbers() {
        return gearNumbers.stream()
                .filter(GearNumber::isSurroundedBySymbol)
                .mapToInt(GearNumber::getValue)
                .sum();
    }

    private void processLine(String line, int lineNumber) {
        for (int i = 0; i < line.length(); i++) {
            if (isDigit(line.charAt(i))) {
                int value = extractNumber(line, i);
                gearNumbers.add(new GearNumber(value, lineNumber, i));
                i += String.valueOf(value).length() - 1;
                continue;
            }
            if (isSymbol(line.charAt(i))) {
                coordinatesOfSymbols.add(new int[]{lineNumber, i});
            }
        }
    }

    int extractNumber(String line, int i) {
        int endOfNumber = i;
        while (endOfNumber < line.length() && isDigit(line.charAt(endOfNumber))) {
            endOfNumber++;
        }

        return Integer.parseInt(line.substring(i, endOfNumber));
    }

    private boolean isSymbol(char c) {
        return !Character.isDigit(c) && c != '.';
    }

    private static class GearNumber {
        private final int value;
        private final int x;
        private final int y;

        private boolean isSurroundedBySymbol = false;

        public GearNumber(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        public int getValue() {
            return value;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean isSurroundedBySymbol() {
            return isSurroundedBySymbol;
        }

        public void setSurroundedBySymbol(boolean surroundedBySymbol) {
            isSurroundedBySymbol = surroundedBySymbol;
        }
    }
}
