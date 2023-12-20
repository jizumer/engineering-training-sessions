package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day9 {


    public Stream<Integer[]> loadReportFromFile(String s) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(s));
        return reader
                .lines()
                .map(line -> line.split(" "))
                .map(this::parseLine);


    }

    private Integer[] parseLine(String[] strings) {
        return Arrays.stream(strings)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    public int findNextValueForHistory(Integer[] history) {

        List<Integer[]> sequences = generateSequences(history);

        int delta = 0;
        for (Integer[] sequence : sequences) {
            delta += sequence[sequence.length - 1];
        }
        return delta;

    }

    private static List<Integer[]> generateSequences(Integer[] history) {
        List<Integer[]> sequences = new ArrayList<>();
        sequences.add(history);

        Integer[] sequenceRunner = sequences.get(0);
        do {
            Integer[] nextSequence = new Integer[sequenceRunner.length - 1];
            for (int i = 0; i < sequenceRunner.length - 1; i++) {
                nextSequence[i] = sequenceRunner[i + 1] - sequenceRunner[i];
            }
            sequences.add(nextSequence);
            sequenceRunner = nextSequence;
        } while (!Arrays
                .stream(sequenceRunner)
                .allMatch(sequenceRunner[0]::equals));
        return sequences;
    }

    public int sumOfNextValuesOfEachHistory(String s) throws FileNotFoundException {
        return loadReportFromFile(s)
                .map(this::findNextValueForHistory)
                .reduce(0, Integer::sum);
    }

    public int findPreviousValueForHistory(Integer[] history) {

        List<Integer[]> sequences = generateSequences(history);

        int delta = 0;
        for (int i = sequences.size() - 1; i >= 0; i--) {
            delta = sequences.get(i)[0] - delta;
        }
        return delta;
    }

    public int sumOfPreviousValuesOfEachHistory(String report) throws FileNotFoundException {
        return loadReportFromFile(report)
                .map(this::findPreviousValueForHistory)
                .reduce(0, Integer::sum);
    }
}
