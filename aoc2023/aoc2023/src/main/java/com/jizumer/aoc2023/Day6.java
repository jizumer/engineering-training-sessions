package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 {
    public Map<Integer, Integer> loadInputFile(String inputFilePath) throws IOException {
        BufferedReader br = new BufferedReader(
                new FileReader(inputFilePath));
        int[] times = Arrays.stream(br.readLine()
                        .substring(5)
                        .split(" "))
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .parallel()
                .toArray();

        int[] distances = Arrays.stream(br.readLine()
                        .substring(9)
                        .split(" "))
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .parallel()
                .toArray();

        return IntStream.range(0, times.length)
                .boxed()
                .collect(Collectors.toMap(i -> times[i], i -> distances[i]));

    }
}
