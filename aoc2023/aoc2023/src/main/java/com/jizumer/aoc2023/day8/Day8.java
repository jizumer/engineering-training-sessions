package com.jizumer.aoc2023.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Day8 {


    private final Direction[] instructions;
    private final Map<String, Node> network = new HashMap<>();

    private final Node[] currentNodes;

    private final Node[] terminalNodes;

    private long steps = 0;

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
        System.out.println("Initial nodes: " + network.values()
                .stream()
                .filter(Node::isInitial)
                .count());
        System.out.println("Terminal nodes: " + network.values()
                .stream()
                .filter(Node::isTerminal)
                .count());
        reader.close();

        currentNodes = findAllInitialNodes();
        terminalNodes = findAllTerminalNodes();
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

    public long traverseMapInGhostMode() {
        int instructionsLimit = instructions.length;
        int instructionsRunner = 0;

        while (!allPeriodsAreCalculated()) {

            for (int i = 0; i < currentNodes.length; i++) {
                currentNodes[i] = currentNodes[i].next(instructions[instructionsRunner], steps);
            }
            instructionsRunner = (instructionsRunner + 1) % instructionsLimit;

            steps++;
        }

        return calculateLcm();
    }

    private long calculateLcm() {
        return Arrays.stream(terminalNodes)
                .parallel()
                .map(Node::getPeriod)
                .map(BigInteger::valueOf)
                .reduce(this::lcm)
                .map(BigInteger::longValue)
                .orElseThrow(() -> new RuntimeException("Error calculating LCM"));

    }

    private BigInteger lcm(BigInteger... values) {
        if (values.length == 0)
            return BigInteger.ONE;
        BigInteger lcm = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i].signum() != 0) {
                final BigInteger gcd = lcm.gcd(values[i]);
                if (gcd.equals(BigInteger.ONE)) {
                    lcm = lcm.multiply(values[i]);
                } else {
                    if (!values[i].equals(gcd)) {
                        lcm = lcm.multiply(values[i].divide(gcd));
                    }
                }
            }
        }
        return lcm;
    }


    private Node[] findAllInitialNodes() {
        return network.values()
                .stream()
                .parallel()
                .filter(Node::isInitial)
                .toArray(Node[]::new);
    }

    private Node[] findAllTerminalNodes() {
        return network.values()
                .stream()
                .parallel()
                .filter(Node::isTerminal)
                .toArray(Node[]::new);
    }

    private Boolean allPeriodsAreCalculated() {
        return Arrays.stream(this.terminalNodes)
                .parallel()
                .map(Node::getPeriod)
                .noneMatch(period -> period == 0);
    }

}