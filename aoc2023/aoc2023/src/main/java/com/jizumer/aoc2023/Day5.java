package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day5 {

    public Almanac loadAlmanac(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        //the first line of the file has the format "seeds: 79 14 55 13..."
        //we can skip the first 7 characters and split the rest by spaces
        List<Integer> seeds = Arrays.stream(reader
                        .readLine()
                        .substring(7)
                        .trim()
                        .split(" "))
                .map(Integer::parseInt)
                .toList();

        return new Almanac(seeds);

    }

    public static class Almanac {
        private final List<Integer> seeds;

        public Almanac(List<Integer> seeds) {
            this.seeds = seeds;
        }

        public List<Integer> getSeeds() {
            return seeds;
        }
    }
}
