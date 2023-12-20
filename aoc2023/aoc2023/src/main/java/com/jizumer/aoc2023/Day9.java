package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
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
}
