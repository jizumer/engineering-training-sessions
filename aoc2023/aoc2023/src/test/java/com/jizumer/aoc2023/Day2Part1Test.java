package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day2Part1Test {

    @Test
    public void shouldLoadTheListOfGamesByFileName() throws FileNotFoundException {
        assertEquals(100,
                new Day2Part1()
                        .loadGames("src/test/resources/day2-input.txt")
                        .size());
    }

    @Test
    public void shouldGenerateAGameFromAString() {
        String gameString = "Game 83: 2 green, 11 red, 20 blue; 20 blue, 1 green, 4 red; 2 green, 6 red, 20 blue; 17 blue, 10 red";
        Game game = new Game(gameString);
        assertEquals(83, game.getId());
        assertEquals(game.getGreen(), 2);
        assertEquals(game.getRed(), 11);
        assertEquals(game.getBlue(), 20);
    }

    @Test
    public void shouldGenerateAListOfGamesFromAListOfStrings() throws FileNotFoundException {
        assertEquals(100,
                new Day2Part1()
                        .loadGames("src/test/resources/day2-input.txt")
                        .stream()
                        .map(Game::new)
                        .count());
    }

    @Test
    public void shouldDetectIfAGameIsPossibleGivenTheCubes() {
        String gameString = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        Game game = new Game(gameString);
        assertTrue(game.isPossible(12, 13,14));
    }


}