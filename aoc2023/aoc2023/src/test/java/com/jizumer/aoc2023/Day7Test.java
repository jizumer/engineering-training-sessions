package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}