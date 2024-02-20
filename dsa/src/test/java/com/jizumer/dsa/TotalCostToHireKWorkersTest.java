package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalCostToHireKWorkersTest {

    /**
     * costs: [31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58]
     * k: 11
     * candidates: 2
     * Expected: 423
     */
    @Test
    void totalCost() {
        int[] costs = {31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58};
        int k = 11;
        int candidates = 2;
        TotalCostToHireKWorkers totalCostToHireKWorkers = new TotalCostToHireKWorkers();
        long result = totalCostToHireKWorkers.totalCost(costs, k, candidates);
        assertEquals(423, result);
    }



}