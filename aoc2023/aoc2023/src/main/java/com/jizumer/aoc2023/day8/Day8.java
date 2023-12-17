package com.jizumer.aoc2023.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Day8 {


    private final Direction[] instructions;
    private final Map<String, Node> network = new HashMap<>();

    private final Node[] currentNodes;

    public Day8(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        instructions = loadInstructions(reader);

        long skipped = reader.skip(1);
        if (skipped != 1) {
            throw new RuntimeException("Error skipping first line");
        }
        reader.lines()
                .map(this::lineToNode)
                .forEach(node -> network.put(node.getLabel(), node));

        completeNetwork();
        System.out.println("Network size: " + network.size() + " nodes.");
        reader.close();

        currentNodes = findAllInitialNodes();
    }

    private void completeNetwork() {
        network.values()
                .stream()
                .parallel()
                .filter(Node::isNotReady)
                .forEach(node -> {
                    Node left = network.get(node.getLeftLabel());
                    Node right = network.get(node.getRightLabel());
                    node.setLeft(left);
                    node.setRight(right);
                });

        network.values()
                .stream()
                .filter(Node::isNotReady)
                .forEach(node ->
                        System.out.println("Node "
                                + node.getLabel()
                                + " is not ready."));


    }

    private Node lineToNode(String line) {
        String[] split = line.split(" = ");

        String label = split[0];
        String leftLabel = split[1].substring(1, 4);
        String rightLabel = split[1].substring(6, 9);

        return new Node(label, leftLabel, rightLabel);
    }

    private Direction[] loadInstructions(BufferedReader reader) throws IOException {
        return reader.readLine()
                .chars()
                .mapToObj(c -> Direction.valueOf(String.valueOf((char) c)))
                .toArray(Direction[]::new);
    }

    public Long traverseMapInGhostMode() {

        int instructionsLimit = instructions.length;

        AtomicInteger instructionRunner = new AtomicInteger(0);
        int steps = IntStream.generate(() -> instructionRunner
                        .getAndIncrement() % instructionsLimit)
                .mapToObj(instruction -> instructions[instruction])
                .filter(this::makeStep)
                .findFirst()
                .get()
                .ordinal();
        return (long) steps;
    }

    private Node[] findAllInitialNodes() {
        return network.values()
                .stream()
                .parallel()
                .filter(Node::isInitial)
                .toArray(Node[]::new);
    }

    private Boolean makeStep(Direction direction) {
        for (int i = 0; i < currentNodes.length; i++) {
            currentNodes[i] = currentNodes[i].next(direction);
        }

        return Arrays.stream(currentNodes)
                .allMatch(Node::isTerminal);
    }

}