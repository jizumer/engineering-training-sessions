package com.jizumer.dsa;

import java.util.Arrays;

//435. Non-overlapping Intervals
public class NonOverlappingIntervals {


    public int eraseOverlapIntervals(int[][] intervals) {
        // order intervals based on its end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int answer = 0;
        int prevEnd = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[0] < prevEnd) {
                answer++;
            } else {
                prevEnd = interval[1];
            }
        }
        return answer;

    }

}
