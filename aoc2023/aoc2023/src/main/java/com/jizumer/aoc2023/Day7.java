package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day7 {
    public long calculateTotalWinnings(String filePath) throws FileNotFoundException {
        List<Hand> hands = loadHandsFromFile(filePath);

        hands.stream().sorted(Hand::compareTo).forEach(System.out::println);

        AtomicInteger runner = new AtomicInteger(0);
        return hands.stream()
                .sorted(Hand::compareTo)
                .mapToLong(Hand::getBid)
                .map(bid -> bid * runner.incrementAndGet())
                .sum();
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

    protected static class Hand implements Comparable<Hand> {
        private final char[] cards;
        private final Long bid;

        public Hand(String cards, Long bid) {
            this.cards = cards.toCharArray();
            this.bid = bid;
        }

        public Long getBid() {
            return bid;
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

        @Override
        public String toString() {
            return "Hand{" +
                    "cards=" + Arrays.toString(cards) +
                    ", bid=" + bid +
                    '}' +
                    " strength=" + calculateStrength();
        }


        @Override
        public int compareTo(Hand o) {
            int comparison = this.calculateStrength().compareTo(o.calculateStrength());
            if (comparison == 0) {
                return compareCardByCard(o);
            }
            return comparison;
        }

        private int compareCardByCard(Hand o) {
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] != o.cards[i]) {
                    return compareLabels(cards[i], o.cards[i]);
                }
            }
            return 0;
        }

        private int compareLabels(char label1, char label2) {
            return labelToValue(label1) - labelToValue(label2);
        }

        private static int labelToValue(char label) {
            return label == 'J' ? 1
                    : label == 'T' ? 10
                    : label == 'Q' ? 11
                    : label == 'K' ? 12
                    : label == 'A' ? 13
                    : Character.getNumericValue(label);
        }


        protected Integer calculateStrength() {

            char[] cardsCopy = cards.clone();
            Arrays.sort(cardsCopy);
            applyJokerRule(cardsCopy);
            Map<Character, Integer> structure =
                    countCardsByGroups(cardsCopy);

            switch (structure.size()) {
                case 1:
                    // Five of a kind
                    return 7;
                case 2:
                    if (getBiggestGroup(structure).getValue() == 4) {
                        // Four of a kind
                        return 6;
                    } else {
                        // Full house
                        return 5;
                    }
                case 3:
                    if (getBiggestGroup(structure).getValue() == 3) {
                        // Three of a kind
                        return 4;
                    } else {
                        // Two pair
                        return 3;
                    }
                case 4:
                    // One pair
                    return 2;
                default:
                    // High card
                    return 1;
            }
        }

        private static Map<Character, Integer> countCardsByGroups(char[] cardsCopy) {
            Map<Character, Integer> structure =
                    new HashMap<>();
            new String(cardsCopy)
                    .chars()
                    .forEach(card ->
                            structure.merge(
                                    (char) card, 1, Integer::sum));
            return structure;
        }

        private void applyJokerRule(char[] cardsCopy) {
            if (new String(cardsCopy)
                    .chars()
                    .noneMatch(card -> card == 'J')) {
                return;
            }

            if(Arrays.equals(new char[]{'J', 'J', 'J', 'J', 'J'}, cardsCopy)) {
                Arrays.fill(cardsCopy, 'A');
                return;
            }

            char characterToReplace
                    = findCharacterToReplace(cardsCopy);
            for (int i = 0; i < cardsCopy.length; i++) {
                if (cardsCopy[i] == 'J') {
                    cardsCopy[i] = characterToReplace;
                }
            }

        }

        private char findCharacterToReplace(char[] cardsCopy) {
            return getBiggestGroup(countCardsByGroups(cardsCopy)).getKey();
        }

        private Map.Entry<Character, Integer> getBiggestGroup(Map<Character, Integer> structure) {
            return structure
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getKey() != 'J')
                    .max(Map.Entry.comparingByValue())
                    .orElseThrow();
        }
    }


}
