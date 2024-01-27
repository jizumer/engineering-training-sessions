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

        if (seen.isEmpty()) {
            seen.push(a);
            return;
        }

        while (!seen.isEmpty()) {
            if (!willCollide(seen.peek(), a)) {
                seen.push(a);
                return;
            }

            int peek = Math.abs(seen.peek());
            int asteroid = Math.abs(a);

            if (peek > asteroid) break;
            if (peek < asteroid) {
                seen.pop();
                continue;
            }
            seen.pop();
            break;
        }


    }

    private boolean willCollide(Integer a, int b) {
        return (a > 0 && b < 0);
    }
}
