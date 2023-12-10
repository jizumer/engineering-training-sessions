package com.jizumer.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {
    public static int calculateWinningPointsForLine(String line) {
        String[] lineSplit = line.split("\\|");
        List<Integer> playerNumbers = new ArrayList<>(Arrays.stream(lineSplit[0]
                        .split(":")[1]
                        .trim()
                        .split(" "))
                .map(Integer::parseInt)
                .toList());

        List<Integer> winningNumbers = Arrays.stream(lineSplit[1]
                        .trim()
                        .split(" "))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();


        playerNumbers.retainAll(winningNumbers);
        // 0 matches = 0 points. 1 match = by 1, and any subsequent match multiplies the points by 2
        return playerNumbers.isEmpty() ? 0 : (int) Math.pow(2, playerNumbers.size() - 1);

    }
}
