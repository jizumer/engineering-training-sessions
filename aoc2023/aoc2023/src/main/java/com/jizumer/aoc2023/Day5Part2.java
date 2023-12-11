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

                this.maps.add(new Map(ranges));
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
                    .parallelStream()
                    .map(seedRange -> LongStream.range(seedRange.getStart(),
                            seedRange.getStart() + seedRange.getLength()))
                    .flatMapToLong(longStream -> longStream)
                    .boxed();

        }

        public long calculateLowestLocation() {
            return getSeedRanges()
                    .parallel()
                    .map(this::applyMaps)
                    .min(Long::compareTo)
                    .orElseThrow();
        }

        private Long applyMaps(Long seed) {
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
        private final List<Range> ranges;

        public Map(List<Range> ranges) {
            this.ranges = ranges;
            this.ranges.sort(Range::compareTo);
        }


        public long map(long from) {

            for (Range range : ranges) {
                if (range.appliesTo(from)) {
                    return range.map(from);
                }
            }
            return from;
        }
    }

    public static class Range {
        private final long upperLimit;
        private final long lowerLimit;
        private final long delta;

        public Range(long destinationRangeStart,
                     long sourceRangeStart,
                     long rangeLength) {
            this.lowerLimit = sourceRangeStart;
            this.upperLimit = sourceRangeStart + rangeLength;
            this.delta = destinationRangeStart - sourceRangeStart;
        }

        public boolean appliesTo(long value) {
            return value >= lowerLimit
                    && value < upperLimit;
        }

        public long map(long from) {
            return from + delta;
        }

        public static int compareTo(Range a, Range b) {
            return Long.compare(a.lowerLimit, b.lowerLimit);
        }
    }
}
