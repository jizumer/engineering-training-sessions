package com.jizumer.dsa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//399. Evaluate Division
public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Node> graph = loadGraph(equations, values);
        double[] costs = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {

            Node start = graph.get(queries.get(i).getFirst());
            Node end = graph.get(queries.get(i).get(1));

            if (start == null || end == null) {
                costs[i] = -1.0;
                continue;
            }
            costs[i] = dfs(graph, start, end);
        }

        return costs;
    }

    private double dfs(Map<String, Node> graph, Node start, Node end) {
        if (start.neighbours.containsKey(end)) return start.neighbours.get(end);
        for (Map.Entry<Node, Double> neighbour : start.neighbours.entrySet()) {

            double attempt = dfs(graph, neighbour.getKey(), end);
            if (attempt != -1.0) return neighbour.getValue() * attempt;
        }

        return -1.0;
    }

    Map<String, Node> loadGraph(List<List<String>> equations, double[] values) {
        Map<String, Node> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double cost = values[i];

            Node start = graph.get(equation.getFirst());
            if (start == null) {
                start = new Node(equation.getFirst());
                graph.put(equation.getFirst(), start);
            }

            Node end = graph.get(equation.getLast());
            if (end == null) {
                end = new Node(equation.getLast());
                graph.put(equation.getLast(), end);
            }

            start.addEdge(end, cost);
            end.addEdge(start, 1.0 / cost);
        }

        return graph;
    }

    class Node {
        private final String label;
        private final Map<Node, Double> neighbours = new HashMap<>();

        public Node(String label) {
            this.label = label;
        }

        public void addEdge(Node neighbour, double cost) {
            this.neighbours.put(neighbour, cost);
        }

        public String getLabel() {
            return label;
        }

        public Map<Node, Double> getNeighbours() {
            return neighbours;
        }
    }
}
