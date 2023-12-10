package com.jizumer.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    public static final String SMALL_INPUT_PATH = "src/test/resources/day5-input-small.txt";
    public static final String LARGE_INPUT_PATH = "src/test/resources/day5-input.txt";

    @Test
    public void shouldLoadAlmanacFromFileContainingInitialSeeds() throws IOException {
        assertEquals(List.of(79, 14, 55, 13), new Day5().loadAlmanac(SMALL_INPUT_PATH).getSeeds());
    }

    @Test
    public void shouldLoadAlmanacContainingListOfMaps() throws IOException {
        assertEquals(7,new Day5().loadAlmanac(SMALL_INPUT_PATH).getMaps().size());
    }

    @Test
    public void shouldLoadAlmanacContainingAMapFromSeedToSoil() throws IOException {
        Day5.Almanac almanac = new Day5().loadAlmanac(SMALL_INPUT_PATH);
        assertEquals("seed", almanac.getMaps().get(0).getFrom());
        assertEquals("soil", almanac.getMaps().get(0).getTo());
        assertEquals("soil", almanac.getMaps().get(1).getFrom());
        assertEquals("fertilizer", almanac.getMaps().get(1).getTo());
    }

    @Test
    public void shouldLoadAlmanacContainingAMapWithRanges() throws IOException {
        List<Day5.Map> maps = new Day5().loadAlmanac(SMALL_INPUT_PATH).getMaps();
        assertEquals(2, maps.get(0).getRanges().size());
        assertEquals(3, maps.get(1).getRanges().size());
        assertEquals(4, maps.get(2).getRanges().size());
    }

    @Test
    public void shouldMapCorrectlyFromSeedToSoil() throws IOException {
        Day5.Map map = new Day5().loadAlmanac(SMALL_INPUT_PATH).getMaps().get(0);
        assertEquals(50, map.map(98));
        assertEquals(51, map.map(99));
        assertEquals(52, map.map(50));
        assertEquals(53, map.map(51));
        assertEquals(54, map.map(52));
        assertEquals(55, map.map(53));
        assertEquals(10, map.map(10));
    }

}