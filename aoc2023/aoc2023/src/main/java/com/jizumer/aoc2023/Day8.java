package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {


    private final List<Direction> instructions = new ArrayList<>();
    private final Map<String, String[]> map = new HashMap<>();

    public int calculateSteps(String filePath) throws IOException {
        loadInstructionsMap(filePath);

        return traverseMap();
    }

    private int traverseMap() {
        int steps = 0;
        int instruction = 0;
        String current = "AAA";

        while (!"ZZZ".equals(current)) {

            String next = map
                    .get(current)
                    [instructions
                    .get(instruction)
                    .ordinal()];
            instruction = (instruction + 1) % instructions.size();
            steps++;
            current = next;
        }
        return steps;
    }

    private void loadInstructionsMap(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        reader.readLine()
                .chars()
                .forEachOrdered(c ->
                        instructions.add(Direction.valueOf(String.valueOf((char) c))));
        long skipped = reader.skip(1);
        if (skipped != 1) {
            throw new RuntimeException("Error skipping first line");
        }
        reader.lines()
                .forEachOrdered(line -> {
                    String[] split = line.split(" = ");
                    map.put(split[0], new String[]{
                            split[1].substring(1, 4),
                            split[1].substring(6, 9),
                    });
                });

        reader.close();
    }

}

enum Direction {
    L, R
}
