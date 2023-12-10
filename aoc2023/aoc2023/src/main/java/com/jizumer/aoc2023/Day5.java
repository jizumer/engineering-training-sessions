package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {

    public Almanac loadAlmanac(String filePath) throws IOException {
        return new Almanac(filePath);
    }

    public static class Almanac {
        private final List<Integer> seeds;
        private final List<Map> maps;

        public Almanac(String filePath) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            this.seeds = Arrays.stream(reader
                            .readLine()
                            .substring(7)
                            .trim()
                            .split(" "))
                    .map(Integer::parseInt)
                    .toList();

            long skipped = reader.skip(1);
            if (skipped != 1) {
                throw new RuntimeException("Error skipping line");
            }

            this.maps = new ArrayList<>();

            while (reader.ready()) {
                String[] fromTo = reader.readLine().split("-");
                String from = fromTo[0].trim();
                String to = fromTo[2]
                        .trim()
                        .split(" ")[0];

                List<Range> ranges = new ArrayList<>();

                while (true) {
                    String line = reader.readLine();
                    if (line == null || line.isBlank()) {
                        break;
                    }
                    String[] rangeValues = line.split(" ");
                    ranges.add(new Range(Integer.parseInt(rangeValues[0]),
                            Integer.parseInt(rangeValues[1]),
                            Integer.parseInt(rangeValues[2])));
                }

                this.maps.add(
                        new Map(from, to, ranges));
            }

            reader.close();
        }

        public List<Integer> getSeeds() {
            return seeds;
        }

        public List<Day5.Map> getMaps() {
            return maps;
        }
    }

    public static class Map {
        private final String from;
        private final String to;
        private final List<Range> ranges;

        public Map(String from, String to, List<Range> ranges) {
            this.from = from;
            this.to = to;
            this.ranges = ranges;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public List<Range> getRanges() {
            return ranges;
        }

        public int map(int from) {
            return ranges
                    .stream()
                    .filter(range -> range.appliesTo(from))
                    .findFirst()
                    .map(range -> range.map(from))
                    .orElse(from);
        }
    }

    public static class Range {
        private final int destinationRangeStart;
        private final int sourceRangeStart;
        private final int rangeLength;

        public Range(int destinationRangeStart,
                     int sourceRangeStart,
                     int rangeLength) {
            this.destinationRangeStart = destinationRangeStart;
            this.sourceRangeStart = sourceRangeStart;
            this.rangeLength = rangeLength;
        }

        public boolean appliesTo(int value) {
            return value >= sourceRangeStart
                    && value < sourceRangeStart + rangeLength;
        }

        public int map(int from) {
            return destinationRangeStart
                    + (from - sourceRangeStart);
        }
    }
}
