package com.jizumer.dsa;

import java.util.Stack;

//735. Asteroid Collision
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> seen = new Stack<>();

        for (int a : asteroids) {
            simulateCollision(seen, a);
        }

        return seen.stream().mapToInt(Integer::intValue).toArray();
    }

    private void simulateCollision(Stack<Integer> seen, int a) {
        if (seen.isEmpty() || !areOpposite(seen.peek(), a)) {
            seen.push(a);
            return;
        }

        while(!seen.isEmpty()) {
            if(seen.peek() > a) break;
            if(seen.peek() < a) {
                seen.pop();
                continue;
            }
            seen.pop();
            break;
        }


    }

    private boolean areOpposite(Integer a, int b) {
        return ((a ^ b) < 0);
    }
}
