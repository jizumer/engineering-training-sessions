package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsteroidCollisionTest {

    @Test
    void shouldCalculateHappyPath() {
        assertArrayEquals(new int[]{5, 10}, new AsteroidCollision().asteroidCollision(new int[]{5, 10, -5}));
    }

    @Test
    void shouldReturnEmptyArrayWithJustTwoOppositeAsteroids() {
        assertArrayEquals(new int[]{},
                new AsteroidCollision()
                        .asteroidCollision(new int[]{8, -8}));
    }

    @Test
    void shouldConsiderAsteroidsNeverColliding() {
        assertArrayEquals(new int[]{-2, -1, 1, 2},
                new AsteroidCollision()
                        .asteroidCollision(new int[]{-2, -1, 1, 2}));
    }


    @Test
    void shouldResultInOnlyNegativeAsteroids() {
        assertArrayEquals(new int[]{-2, -2, -2},
                new AsteroidCollision()
                        .asteroidCollision(new int[]{-2, -2, 1, -2}));
    }

    @Test
    void shouldPushAsteroidsEvenIfTheyDontCollide() {
        assertArrayEquals(new int[]{-2, -2, -2},
                new AsteroidCollision()
                        .asteroidCollision(new int[]{1, -2, -2, -2}));
    }
}