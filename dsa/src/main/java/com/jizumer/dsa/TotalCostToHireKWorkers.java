package com.jizumer.dsa;

import java.util.PriorityQueue;

//2462. Total Cost to Hire K Workers
public class TotalCostToHireKWorkers {


    public long totalCost(int[] costs, int k, int candidates) {

        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();

        int l = 0;
        int r = costs.length - 1;

        int count = candidates;
        while (l < r && count > 0) {
            left.add(costs[l]);
            right.add(costs[r]);
            l++;
            r--;
            count--;
        }

        int cost = 0;
        count = k;
        while (count > 0) {
            int next;
            if (left.peek() > right.peek()) {
                next = right.poll();
                if (r >= 0) {
                    right.add(costs[r]);
                    r--;
                }
            } else {
                next = left.poll();
                if (l < costs.length) {
                    left.add(costs[l]);
                    l++;
                }
            }
            cost += next;
            count--;
        }
        return cost;
    }
}
