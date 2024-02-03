package com.jizumer.dsa;

import java.util.ArrayList;
import java.util.List;

//1466. Reorder Routes to Make All Paths Lead to the City Zero
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        for (List<Integer> combination : combinations(k, n, 1)) {
            combination.sort(Integer::compareTo);
            combinations.add(combination);
        }
        return combinations;
    }

    private List<List<Integer>> combinations(int k, int n, int min) {
        List<List<Integer>> combinations = new ArrayList<>();
        if (n < min) return combinations;
        if (k == 1) {
            if (n > 9) return combinations;
            List<Integer> c = new ArrayList<>();
            c.add(n);
            combinations.add(c);
            return combinations;
        }

        for (int i = min; i < Math.min(n - k, 9); i++) {
            for (List<Integer> c : combinations(k - 1, n - i, i + 1)) {
                c.add(i);
                combinations.add(c);
            }
        }
        return combinations;
    }


}
