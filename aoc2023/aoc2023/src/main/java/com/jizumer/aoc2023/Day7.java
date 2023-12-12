package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day7 {
    public long calculateTotalWinnings(String filePath) throws FileNotFoundException {
        List<Hand> hands = loadHandsFromFile(filePath);
        return 0;
    }

    protected List<Hand> loadHandsFromFile(String filePath) throws FileNotFoundException {

        return new BufferedReader(
                new FileReader(filePath))
                .lines()
                .map(line -> {
                    String[] handStrings = line.split(" ");
                    return new Hand(handStrings[0], Long.parseLong(handStrings[1]));
                }).toList();
    }

    protected static class Hand {
        private final char[] cards;
        private final Long bid;

        public Hand(String cards, Long bid) {
            this.cards = cards.toCharArray();
            this.bid = bid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Hand hand = (Hand) o;

            if (!Arrays.equals(cards, hand.cards)) return false;
            return Objects.equals(bid, hand.bid);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(cards);
            result = 31 * result + (bid != null ? bid.hashCode() : 0);
            return result;
        }
    }


}
