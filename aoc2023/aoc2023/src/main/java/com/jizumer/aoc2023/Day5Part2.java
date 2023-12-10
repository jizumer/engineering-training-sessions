package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;

public class Day5Part2 {

    public Almanac loadAlmanac(String filePath) throws IOException {
        return new Almanac(filePath);
    }

    public static class Almanac {
        //Format for sees will now be pairs of initial value and length of ranges
        private final List<SeedRange> seedRanges;
        private final List<Map> maps;

        public Almanac(String filePath) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            this.seedRanges = loadSeedRanges(reader.readLine());

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
                    ranges.add(new Range(parseLong(rangeValues[0]),
                            parseLong(rangeValues[1]),
                            parseLong(rangeValues[2])));
                }

                this.maps.add(new Map(from, to, ranges));
            }

            reader.close();
        }

        private List<SeedRange> loadSeedRanges(String seedsLine) {
            List<SeedRange> seedRanges = new ArrayList<>();
            String[] seedParameters = seedsLine.substring(7)
                    .trim()
                    .split(" ");

            for (int i = 0; i < seedParameters.length; i += 2) {
                seedRanges.add(new SeedRange(parseLong(seedParameters[i]),
                        parseLong(seedParameters[i + 1])));
            }
            return seedRanges;
        }

        public Stream<Long> getSeedRanges() {

            return this.seedRanges
                    .stream()
                    .map(seedRange -> LongStream.range(seedRange.getStart(),
                            seedRange.getStart() + seedRange.getLength()))
                    .flatMap(LongStream::boxed);

        }

        public long calculateLowestLocation() {
            return getSeedRanges()
                    .map(this::applyMaps).min(Long::compareTo).orElseThrow();
        }

        private long applyMaps(Long seed) {
            for (Map map : maps) {
                seed = map.map(seed);
            }
            return seed;
        }
    }

    private static class SeedRange {
        private final long start;
        private final long length;

        public SeedRange(long start, long length) {
            this.start = start;
            this.length = length;
        }

        public long getStart() {
            return start;
        }

        public long getLength() {
            return length;
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

        public long map(long from) {
            return ranges
                    .stream()
                    .filter(range -> range.appliesTo(from))
                    .findAny()
                    .map(range -> range.map(from))
                    .orElse(from);
        }
    }

    public static class Range {
        private final long destinationRangeStart;
        private final long sourceRangeStart;
        private final long rangeLength;

        public Range(long destinationRangeStart,
                     long sourceRangeStart,
                     long rangeLength) {
            this.destinationRangeStart = destinationRangeStart;
            this.sourceRangeStart = sourceRangeStart;
            this.rangeLength = rangeLength;
        }

        public boolean appliesTo(long value) {
            return value >= sourceRangeStart
                    && value < sourceRangeStart + rangeLength;
        }

        public long map(long from) {
            return destinationRangeStart
                    + (from - sourceRangeStart);
        }
    }
}
