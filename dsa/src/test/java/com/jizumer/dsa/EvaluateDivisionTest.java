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

}