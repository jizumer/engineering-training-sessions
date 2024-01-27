package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsteroidCollisionTest {

    @Test
    void shouldCalculateHappyPath() {
        assertArrayEquals(new int[]{5, 10}, new AsteroidCollision().asteroidCollision(new int[]{5, 10, -5}));
    }

}