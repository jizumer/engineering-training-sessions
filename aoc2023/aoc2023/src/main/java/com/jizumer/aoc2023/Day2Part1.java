package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.max;

public class Day2Part1 {
    List<String> loadGames(String s) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(s));
        return reader.lines().collect(Collectors.toList());
    }


}

class Game {
    private final int id;
    private int green;
    private int red;
    private int blue;

    public Game(String gameString) {
        this.id = Integer.parseInt(
                gameString.split(":")[0].split(" ")[1]);

        String[] strikes = gameString
                .split(":")[1]
                .split(";");
        Arrays.stream(strikes)
                .forEach(strike -> {
                    String[] cubes = strike.split(",");
                    Arrays.stream(cubes)
                            .forEach(cube -> {
                                String[] cubeData = cube.trim().split(" ");
                                switch (cubeData[1]) {
                                    case "green":
                                        this.green = max(this.green,
                                                Integer.parseInt(cubeData[0]));
                                        break;
                                    case "red":
                                        this.red = max(this.red,
                                                Integer.parseInt(cubeData[0]));
                                        break;
                                    case "blue":
                                        this.blue = max(this.blue,
                                                Integer.parseInt(cubeData[0]));
                                        break;
                                    default:
                                        throw new IllegalStateException("Unexpected value: " + cubeData[1]);
                                }
                            });

                });


    }

    public int getId() {
        return id;
    }

    public int getGreen() {
        return green;
    }

    public int getRed() {
        return red;
    }

    public int getBlue() {
        return blue;
    }

    public boolean isPossible(int red, int green, int blue) {
        return this.red <= red
                && this.green <= green
                && this.blue <= blue;
    }
}