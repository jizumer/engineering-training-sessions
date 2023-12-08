package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day2Part1 {
    List<String> loadGames(String s) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(s));
        return reader.lines().collect(Collectors.toList());
    }
}
