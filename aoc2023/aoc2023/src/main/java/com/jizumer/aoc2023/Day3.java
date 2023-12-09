package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

public class Day3 {

    private final List<PartNumber> partNumbers = new ArrayList<>();
    private final List<int[]> coordinatesOfSymbols = new ArrayList<>();

    private final List<Gear> gears = new ArrayList<>();


    public int calculateSumOfPartNumbers(String engineSchematicFilePath) throws IOException {
        processEngineSchematicFile(engineSchematicFilePath);
        calculateSurroundedPartNumbers();
        return calculateSumOfPartNumbers();

    }

    private void processEngineSchematicFile(String engineSchematicFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(engineSchematicFilePath));
        int lineNumber = 0;
        while (reader.ready()) {
            processLine(reader.readLine(), lineNumber);
            lineNumber++;
        }
        reader.close();
    }

    public int calculateSumOfGearRatios(String engineSchematicFilePath) throws IOException {
        processEngineSchematicFile(engineSchematicFilePath);
        return calculateSumOfGearRatios();
    }

    private int calculateSumOfGearRatios() {
        return gears
                .stream()
                .filter(gear -> gear.getNumberOfAdjacentPartNumbers() == 2)
                .mapToInt(Gear::getGearRatio)
                .sum();
    }


    private void calculateSurroundedPartNumbers() {
        for (PartNumber partNumber : this.partNumbers) {
            for (int[] coordinates : coordinatesOfSymbols) {
                if (isPartNumberAdjacentToSymbol(partNumber, coordinates)) {
                    partNumber.setSurroundedBySymbol(true);
                    break;
                }
            }
        }
    }

    private boolean isPartNumberAdjacentToSymbol(PartNumber partNumber, int[] coordinatesOfSymbol) {
        int gearNumberLength = String.valueOf(partNumber.getValue()).length();
        if (coordinatesOfSymbol[0] < partNumber.getX() - 1 || coordinatesOfSymbol[0] > partNumber.getX() + 1) {
            return false;
        }
        return coordinatesOfSymbol[1] >= partNumber.getY() - 1 && coordinatesOfSymbol[1] <= partNumber.getY() + gearNumberLength;
    }

    private int calculateSumOfPartNumbers() {
        return partNumbers.stream()
                .filter(PartNumber::isSurroundedBySymbol)
                .mapToInt(PartNumber::getValue)
                .sum();
    }

    private void processLine(String line, int lineNumber) {
        for (int i = 0; i < line.length(); i++) {
            if (isDigit(line.charAt(i))) {
                int value = extractNumber(line, i);
                partNumbers.add(new PartNumber(value, lineNumber, i));
                i += String.valueOf(value).length() - 1;
                continue;
            }
            if (isSymbol(line.charAt(i))) {
                if (line.charAt(i) == '*') {
                    gears.add(new Gear(lineNumber, i));
                }
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

    private class Gear {
        private final int x;
        private final int y;
        private int gearRatio = -1;
        private int numberOfAdjacentPartNumbers = -1;

        public Gear(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getGearRatio() {
            if (gearRatio == -1) {
                updateGearRatio();
            }
            return gearRatio;
        }

        public int getNumberOfAdjacentPartNumbers() {
            if (numberOfAdjacentPartNumbers == -1) {
                updateGearRatio();
            }
            return numberOfAdjacentPartNumbers;
        }

        private void updateGearRatio() {
            this.gearRatio = 1;
            this.numberOfAdjacentPartNumbers = 0;
            for (PartNumber partNumber : partNumbers) {
                if (isPartNumberAdjacentToSymbol(partNumber, this.toSymbol())) {
                    this.gearRatio *= partNumber.getValue();
                    this.numberOfAdjacentPartNumbers++;
                }
            }
        }

        private int[] toSymbol() {
            return new int[]{this.x, this.y};
        }
    }

    private static class PartNumber {
        private final int value;
        private final int x;
        private final int y;

        private boolean isSurroundedBySymbol = false;

        public PartNumber(int value, int x, int y) {
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
