package com.jizumer.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Day8 {


    private final List<Direction> instructions = new ArrayList<>();
    private final Map<String, String[]> map = new HashMap<>();

    private BigInteger steps = BigInteger.valueOf(-1);

    Map<BigInteger, AtomicInteger> stepsTracker = new HashMap<>();
    BigInteger[] lastStepNotified;

    public int calculateSteps(String filePath) throws IOException {
        loadInstructionsMap(filePath);

        return traverseMap("AAA");
    }

    public BigInteger calculateStepsInGhostMode(String s) throws IOException {
        loadInstructionsMap(s);
        return traverseMapInGhostMode();
    }

    private BigInteger traverseMapInGhostMode() {
        this.steps = BigInteger.valueOf(-1);

        String[] initialNodes = findAllInitialNodes();
        lastStepNotified = new BigInteger[initialNodes.length];
        Arrays.fill(lastStepNotified, BigInteger.valueOf(0));

        try (ExecutorService executorService =
                     Executors.newFixedThreadPool(initialNodes.length + 1)) {
            AtomicInteger taskIndex = new AtomicInteger(0);
            for (String initialNode : initialNodes) {
                Runnable task =
                        () -> traverseMapInGhostModeStartingFrom(initialNode, taskIndex.getAndIncrement());
                executorService.submit(task);
            }

            executorService.submit(getPrunerRunnableTask());


            do {
                steps = evaluateIfEndNodeReached();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } while (steps.compareTo(BigInteger.valueOf(-1)) == 0);
            executorService.shutdownNow();
        }
        return steps;
    }

    private Runnable getPrunerRunnableTask() {
        return () -> {
            while (!Thread.currentThread().isInterrupted()) {
                BigInteger minimumStepNotified =
                        Arrays
                                .stream(lastStepNotified)
                                .min(Comparator
                                        .naturalOrder())
                                .get();
                stepsTracker
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getKey().compareTo(minimumStepNotified) < 0)
                        .forEach(stepsTracker::remove);
//                    System.out.println("Pruned to " + stepsTracker.size() + " elements");
//                    System.out.println(Arrays.toString(lastStepNotified));
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private synchronized BigInteger evaluateIfEndNodeReached() {
        BigInteger evaluateIfEndNodeReached = stepsTracker
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().get() == lastStepNotified.length)
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey)
                .orElse(BigInteger.valueOf((-1)));
        if (evaluateIfEndNodeReached.compareTo(BigInteger.valueOf(-1)) != 0) {
            System.out.println("End node reached in " + evaluateIfEndNodeReached + " steps");
            return evaluateIfEndNodeReached;
        } else {
            return BigInteger.valueOf(-1);
        }
    }

    private void traverseMapInGhostModeStartingFrom(String initialNode, int taskIndex) {
        BigInteger localSteps = BigInteger.ZERO;
        int instruction = 0;
        String current = initialNode;
        while (!Thread.currentThread().isInterrupted()) {
            String next = map
                    .get(current)
                    [instructions
                    .get(instruction)
                    .ordinal()];
            instruction = (instruction + 1) % instructions.size();

            localSteps = localSteps.add(BigInteger.ONE);

            current = next;
            if (current.charAt(2) == 'Z') {
                notifyPotentialEndNodeReached(localSteps, taskIndex);
            }
        }


    }

    private synchronized void notifyPotentialEndNodeReached(BigInteger steps,
                                                            int taskIndex) {
        lastStepNotified[taskIndex] = steps;

        if (!stepsTracker.containsKey(steps)) {
            stepsTracker.put(steps, new AtomicInteger(1));
        } else {
            int i = stepsTracker.get(steps).incrementAndGet();
            if (i == lastStepNotified.length) {
                this.steps = this.steps.min(steps);
            }
        }
    }

    private String[] findAllInitialNodes() {
        return map.keySet()
                .stream()
                .filter(s -> s.charAt(2) == 'A')
                .toArray(String[]::new);
    }

    private int traverseMap(String initialNode) {
        int steps = 0;
        int instruction = 0;
        String current = initialNode;

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
