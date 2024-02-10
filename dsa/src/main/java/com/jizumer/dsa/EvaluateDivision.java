package com.jizumer.dsa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//399. Evaluate Division
public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Node> graph = loadGraph(equations, values);
        double[] costs = new double[queries.size()];

        return costs;
    }

    Map<String, Node> loadGraph(List<List<String>> equations, double[] values) {
        Map<String, Node> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double cost = values[i];

            Node start;
            if (graph.containsKey(equation.getFirst()))
                start = graph.get(equation.getFirst());
            else {
                start = new Node(equation.getFirst());
                graph.put(equation.getFirst(), start);
            }

            Node end;
            if (graph.containsKey(equation.get(1)))
                end = graph.get(equation.get(1));
            else {
                end = new Node(equation.getFirst());
                graph.put(equation.get(1), end);
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
