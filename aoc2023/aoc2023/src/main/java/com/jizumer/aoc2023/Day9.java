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

        List<Integer[]> sequences = new ArrayList<>();
        sequences.add(history);

        Integer[] sequenceRunner = sequences.get(0);
        do {
            Integer[] nextSequence = new Integer[history.length - 1];
            for (int i = 0; i < sequenceRunner.length - 1; i++) {
                nextSequence[i] = sequenceRunner[i + 1] - sequenceRunner[i];
            }
            sequences.add(nextSequence);
            sequenceRunner = nextSequence;
        } while (!Arrays
                .stream(sequenceRunner)
                .allMatch(sequenceRunner[0]::equals));

        int delta = 0;
        for (Integer[] sequence : sequences) {
            delta += sequence[sequence.length - 1];
        }
        return delta;

    }
}
