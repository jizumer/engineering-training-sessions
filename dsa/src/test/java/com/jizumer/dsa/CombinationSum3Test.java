package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinationSum3Test {

    @Test
    void shouldCalculateHappyPath() {
        assertEquals(List.of(List.of(1, 2, 4)), new CombinationSum3().combinationSum3(3, 7));
    }

    @Test
    void shouldNotConsiderNumbersBiggersThanNine() {
        assertEquals(List.of(),
                new CombinationSum3().combinationSum3(2, 18));
    }

}