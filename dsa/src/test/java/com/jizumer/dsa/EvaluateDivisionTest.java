package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EvaluateDivisionTest {

    @Test
    void shouldLoadGraph() {
        List<List<String>> equations = List.of(List.of("a", "b"), List.of("b", "c"));
        double[] values = new double[]{2.0, 3.0};

        Map<String, EvaluateDivision.Node> graph = new EvaluateDivision().loadGraph(equations, values);
        assertNotNull(graph);
        assertEquals(3, graph.size());
        assertEquals(1, graph.get("a").getNeighbours().size());
        assertEquals(2, graph.get("b").getNeighbours().size());
    }


    @Test
    void shouldCalculatePaths() {
        List<List<String>> equations = List.of(List.of("a", "b"), List.of("b", "c"));
        double[] values = new double[]{2.0, 3.0};
        List<List<String>> queries = List.of(
                List.of("a", "c"),
                List.of("b", "a"),
                List.of("a", "e"),
                List.of("a", "a"),
                List.of("x", "x"));
        assertArrayEquals(new double[]{6.0, 0.5, -1.0, 1.0, -1.0},
                new EvaluateDivision().calcEquation(equations, values, queries));
    }

    // equations [["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]]
    // values [3.0,4.0,5.0,6.0]
    // queries [["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
    @Test
    void shouldCalculatePathsWithMoreComplicatedLabelsInItsNodes() {
        List<List<String>> equations = List.of(List.of("x1", "x2"), List.of("x2", "x3"), List.of("x3", "x4"), List.of("x4", "x5"));
        double[] values = new double[]{3.0, 4.0, 5.0, 6.0};
        List<List<String>> queries = List.of(List.of("x1", "x5"),
                List.of("x5", "x2"),
                List.of("x2", "x4"),
                List.of("x2", "x2"),
                List.of("x2", "x9"),
                List.of("x9", "x9"));
        assertArrayEquals(new double[]{360.0, 0.008333333333333333, 20.0, 1.0, -1.0, -1.0},
                new EvaluateDivision().calcEquation(equations, values, queries));
    }
}