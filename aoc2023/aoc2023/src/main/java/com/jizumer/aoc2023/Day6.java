package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 {
    public static Map<Integer, Integer> loadInputFile(String inputFilePath) throws IOException {
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

    public static int calculatePossibleWaysToWin(long time, long distance) {
        int ways = 0;
        for (int timeHoldingButton = 0; timeHoldingButton < time; timeHoldingButton++) {

            if (measure(timeHoldingButton, time) > distance) {
                ways++;
            }
        }
        return ways;
    }

    private static long measure(long timeHoldingButton, long time) {
        return timeHoldingButton * (time - timeHoldingButton);
    }

    public static int calculateMultOfPossibleWays(String file) throws IOException {
        return loadInputFile(file)
                .entrySet()
                .stream()
                .mapToInt(entry -> calculatePossibleWaysToWin(entry.getKey(), entry.getValue()))
                .reduce((a, b) -> a * b)
                .orElseThrow();
    }

    public static int calculateMultOfPossibleWaysMergingRaces(String file) throws IOException {
        long[] raceParameters = loadInputFileMergingRaces(file);
        return calculatePossibleWaysToWin(raceParameters[0], raceParameters[1]);


    }

    private static long[] loadInputFileMergingRaces(String file) throws IOException {
        BufferedReader br = new BufferedReader(
                new FileReader(file));
        long time = Long.parseLong(
                br.readLine()
                        .substring(5)
                        .replace(" ", ""));

        long distance = Long.parseLong(
                br.readLine()
                        .substring(9)
                        .replace(" ", ""));
        return new long[]{time, distance};

    }
}
