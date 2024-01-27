package com.jizumer.dsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EqualRowAndColumnsPairsTest {


    @Test
    void shouldTestHappyPath() {
        assertEquals(1, new EqualRowAndColumnsPairs()
                .equalPairs(new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}}));
    }

    @Test
    void shouldCountEqualColsAndPairs() {
        assertEquals(0, new EqualRowAndColumnsPairs().equalPairs(new int[][]{{2, 1}, {3, 32}}));
    }

}