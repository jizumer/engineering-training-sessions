package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day7Test {

    @Test
    public void shouldLoadHandsFromFile() throws FileNotFoundException {

        assertEquals(List.of(new Day7.Hand("32T3K", 765L),
                        new Day7.Hand("T55J5", 684L),
                        new Day7.Hand("KK677", 28L),
                        new Day7.Hand("KTJJT", 220L),
                        new Day7.Hand("QQQJA", 483L)),
                new Day7().loadHandsFromFile("src/test/resources/day7-input-small.txt"));
    }

    @Test
    public void shouldCompareHands() {
        assertTrue(new Day7.Hand("AAAAA", 1L).compareTo(new Day7.Hand("AA8AA", 1L)) > 0);
        assertTrue(new Day7.Hand("AA8AA", 1L).compareTo(new Day7.Hand("23332", 1L)) > 0);
        assertTrue(new Day7.Hand("23332", 1L).compareTo(new Day7.Hand("TTT98", 1L)) > 0);
        assertTrue(new Day7.Hand("TTT98", 1L).compareTo(new Day7.Hand("23432", 1L)) > 0);
        assertTrue(new Day7.Hand("23432", 1L).compareTo(new Day7.Hand("A23A4", 1L)) > 0);
        assertTrue(new Day7.Hand("A23A4", 1L).compareTo(new Day7.Hand("23456", 1L)) > 0);

        assertTrue(new Day7.Hand("23456", 1L).compareTo(new Day7.Hand("23456", 1L)) == 0);

        assertTrue(new Day7.Hand("33332", 1L).compareTo(new Day7.Hand("2AAAA", 1L)) > 0);
        assertTrue(new Day7.Hand("77788", 1L).compareTo(new Day7.Hand("77888", 1L)) < 0);
    }

    @Test
    public void shouldCalculateHandsStrength() {
        assertEquals(7, new Day7.Hand("AAAAA", 1L).calculateStrength());
        assertEquals(6, new Day7.Hand("AA8AA", 1L).calculateStrength());
        assertEquals(5, new Day7.Hand("23332", 1L).calculateStrength());
        assertEquals(6, new Day7.Hand("T55J5", 1L).calculateStrength());
        assertEquals(4, new Day7.Hand("TTT98", 1L).calculateStrength());
        assertEquals(3, new Day7.Hand("23432", 1L).calculateStrength());
        assertEquals(2, new Day7.Hand("A23A4", 1L).calculateStrength());
        assertEquals(1, new Day7.Hand("23456", 1L).calculateStrength());
    }


    @Test
    public void shouldCalculateTotalWinnings() throws FileNotFoundException {
        assertEquals(5905, new Day7().calculateTotalWinnings("src/test/resources/day7-input-small.txt"));
    }

    @Test
    public void shouldCalculateStrengthForAGivenHand() {
        assertEquals(6, new Day7.Hand("KTJJT", 220L).calculateStrength());
    }

    @Test
    public void shouldCalculateTotalWinningsWithALargeInput() throws FileNotFoundException {
        assertEquals(250087440, new Day7().calculateTotalWinnings("src/test/resources/day7-input.txt"));
    }

}