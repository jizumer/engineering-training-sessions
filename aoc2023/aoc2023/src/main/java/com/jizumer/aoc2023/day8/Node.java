package com.jizumer.aoc2023.day8;

public class Node {
    private final String label;

    private final String leftLabel;
    private final String rightLabel;
    private Node left;
    private Node right;

    private final Boolean initial;
    private final Boolean terminal;

    public Node(String label, String leftLabel, String rightLabel) {
        this.label = label;
        this.leftLabel = leftLabel;
        this.rightLabel = rightLabel;

        this.terminal = label.charAt(2) == 'Z';
        this.initial = label.charAt(2) == 'A';
    }

    public Boolean isTerminal() {
        return terminal;
    }

    public Boolean isInitial() {
        return initial;
    }


    public String getLabel() {
        return label;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getLeftLabel() {
        return leftLabel;
    }

    public String getRightLabel() {
        return rightLabel;
    }

    public Boolean isNotReady() {
        return left == null && right == null;
    }

    public Node next(Direction direction) {
        switch (direction) {
            case L -> {
                return left;
            }
            case R -> {
                return right;
            }
            default -> throw new RuntimeException("Invalid direction.");
        }
    }
}
