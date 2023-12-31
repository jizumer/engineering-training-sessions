package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day12 {
    public int findNumberOfArrangementsByConditionRecord(String conditionRecord) {
        char[] springs = conditionRecord.split(" ")[0].toCharArray();
        String[] groupsSizes = conditionRecord.split(" ")[1].split(",");

        return numberOfArrangements(springs, groupsSizes);

    }

    private int numberOfArrangements(char[] springs, String[] groupsSizes) {
        List<Integer> unknownIndexes = locateUnknownIdexes(springs);
        if (unknownIndexes.isEmpty()) {
            return isValid(springs, groupsSizes) ? 1 : 0;
        }

        char[] operationalVariant = springs.clone();
        operationalVariant[unknownIndexes.get(0)] = '.';

        char[] nonOperationalVariant = springs.clone();
        nonOperationalVariant[unknownIndexes.get(0)] = '#';

        return numberOfArrangements(operationalVariant, groupsSizes)
                + numberOfArrangements(nonOperationalVariant, groupsSizes);

    }

    private boolean isValid(char[] springs, String[] expectedGroupSizes) {
        String[] detectedGroupsSizes = detectGroupOfBrokenSpringSizes(springs);
        return Arrays.equals(detectedGroupsSizes, expectedGroupSizes);
    }

    public String[] detectGroupOfBrokenSpringSizes(char[] springs) {
        // generate a list of the sizes of groups of consecutive characters '#' found in the array springs
        List<Integer> detectedGroupsSizes = new ArrayList<>();
        int groupSize = 0;
        for (char spring : springs) {
            if (spring == '#') {
                groupSize++;
            } else {
                if (groupSize > 0) {
                    detectedGroupsSizes.add(groupSize);
                    groupSize = 0;
                }
            }
        }
        if (groupSize > 0) {
            detectedGroupsSizes.add(groupSize);
        }
        return detectedGroupsSizes.stream().map(Object::toString).toArray(String[]::new);
    }

    private List<Integer> locateUnknownIdexes(char[] springs) {
        List<Integer> unknownIndexes = new ArrayList<>();
        for (int i = 0; i < springs.length; i++) {
            if (springs[i] == '?') {
                unknownIndexes.add(i);
            }
        }
        return unknownIndexes;
    }

    public int findSumOfArrangementsByConditionRecordFile(String s) throws FileNotFoundException {
        return new BufferedReader(new FileReader(s))
                .lines()
                .map(this::findNumberOfArrangementsByConditionRecord)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    public String expandConditionRecord(String conditionRecord) {
        String springs = conditionRecord.split(" ")[0];
        String groupsSizes = conditionRecord.split(" ")[1];


        StringBuilder expandedConditionRecord = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            expandedConditionRecord.append(springs);
            expandedConditionRecord.append("?");
        }
        expandedConditionRecord.append(springs);
        expandedConditionRecord.append(" ");
        for (int i = 0; i < 4; i++) {
            expandedConditionRecord.append(groupsSizes);
            expandedConditionRecord.append(",");
        }
        expandedConditionRecord.append(groupsSizes);
        return expandedConditionRecord.toString();
    }
}
