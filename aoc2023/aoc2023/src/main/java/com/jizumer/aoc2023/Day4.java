package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.IntStream;

public class Day4 {
    public static int calculateWinningPointsForLine(String line) {
        List<Integer> matchingNumbers = findMatchingNumbersByLine(line);
        // 0 matches = 0 points. 1 match = by 1, and any subsequent match multiplies the points by 2
        return matchingNumbers.isEmpty() ? 0 : (int) Math.pow(2, matchingNumbers.size() - 1);

    }

    private static List<Integer> findMatchingNumbersByLine(String line) {
        String[] lineSplit = line.split("\\|");
        List<Integer> playerNumbers = new ArrayList<>(Arrays.stream(lineSplit[0]
                        .split(":")[1]
                        .trim()
                        .split(" "))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList());

        List<Integer> winningNumbers = Arrays.stream(lineSplit[1]
                        .trim()
                        .split(" "))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();


        playerNumbers.retainAll(winningNumbers);
        return playerNumbers;
    }

    public static int calculateSumOfWinningPointsForFile(String filePath) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        return br.lines()
                .mapToInt(Day4::calculateWinningPointsForLine)
                .sum();
    }


    public static int calculateTotalNumOfCardsForAGivenFile(String filePath) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        List<Integer> pointsPerCard = new ArrayList<>(br.lines()
                .map(line -> findMatchingNumbersByLine(line).size())
                .toList());

        int[] numOfCards = IntStream.generate(() -> 1)
                .limit(pointsPerCard.size())
                .toArray();


        for (int i = 0; i < pointsPerCard.size(); i++) {
            for (int j = i + 1; j <= i + pointsPerCard.get(i); j++) {
                numOfCards[j] = numOfCards[j] + numOfCards[i];
            }
        }

        return Arrays.stream(numOfCards)
                .boxed()
                .mapToInt(Integer::intValue)
                .sum();
    }


}
