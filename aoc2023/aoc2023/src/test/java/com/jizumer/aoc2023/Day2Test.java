package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    public void shouldLoadTheListOfGamesByFileName() throws FileNotFoundException {
        assertEquals(100,
                new Day2()
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
                new Day2()
                        .loadGames("src/test/resources/day2-input.txt")
                        .stream()
                        .map(Game::new)
                        .count());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green*12*13*14*true",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue*12*13*14*true",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red*12*13*14*false",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red*12*13*14*false",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green*12*13*14*true"
    }, delimiter = '*')
    public void shouldDetectIfAGameIsPossibleGivenTheCubes(String gameString,
                                                           String red,
                                                           String green,
                                                           String blue,
                                                           String expected) {
        Game game = new Game(gameString);
        assertEquals(Boolean.parseBoolean(expected),
                game.isPossible(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue)));
    }

    @Test
    public void shouldSumTheIdsOfGamesThatArePossible() throws FileNotFoundException {
        assertEquals(2265,
                new Day2()
                        .loadGames("src/test/resources/day2-input.txt")
                        .stream()
                        .map(Game::new)
                        .filter(game -> game.isPossible(12, 13, 14))
                        .mapToInt(Game::getId)
                        .sum());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green*4*2*6",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue*1*3*4",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red*20*13*6",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red*14*3*15",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green*6*3*2"
    }, delimiter = '*')
    public void shouldCalculateTheMinimumBagConfigurationToMakeGamePossible(
            String gameString,
            int red,
            int green,
            int blue) {
        Game game = new Game(gameString);

        assertEquals(red, game.getRed());
        assertEquals(green, game.getGreen());
        assertEquals(blue, game.getBlue());

    }

    @ParameterizedTest
    @CsvSource(value = {
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green*48",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue*12",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red*1560",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red*630",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green*36"
    }, delimiter = '*')
    public void shouldCalculatePowerOfAGame(String gameString,
                                            int expected) {
        Game game = new Game(gameString);
        assertEquals(expected, game.getPower());

    }

    @Test
    public void shouldCalculateTheSumOfThePowerOfAllGames() throws FileNotFoundException {
        assertEquals(64097,
                new Day2()
                        .loadGames("src/test/resources/day2-input.txt")
                        .stream()
                        .map(Game::new)
                        .mapToInt(Game::getPower)
                        .sum());
    }

}