package be.yfrickx.app.day11;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UniverseTest {

    @Test
    void getPathLengthBetweenSpaces() {
        List<String> lines = new ArrayList<>(
                Arrays.asList(
                        "...#......",
                        ".......#..",
                        "#.........",
                        "..........",
                        "......#...",
                        ".#........",
                        ".........#",
                        "..........",
                        ".......#..",
                        "#...#....."
                ));
        Universe universe = new Universe(lines, 2);

        assertEquals(9, universe.getPathLengthBetweenSpaces(
                new Space(1, 5, true, 1), new Space(4, 9, true, 1)
        ));

    }

    @Test
    void getSumOfPaths() {
        List<String> lines = new ArrayList<>(
                Arrays.asList(
                        "...#......",
                        ".......#..",
                        "#.........",
                        "..........",
                        "......#...",
                        ".#........",
                        ".........#",
                        "..........",
                        ".......#..",
                        "#...#....."
                ));
        Universe universe = new Universe(lines, 2);

        assertEquals(374, universe.getSumOfPaths());
    }

    @Test
    void getSumOfPaths2() {
        List<String> lines = new ArrayList<>(
                Arrays.asList(
                        "...#......",
                        ".......#..",
                        "#.........",
                        "..........",
                        "......#...",
                        ".#........",
                        ".........#",
                        "..........",
                        ".......#..",
                        "#...#....."
                ));
        Universe universe = new Universe(lines, 10);

        assertEquals(1030, universe.getSumOfPaths());
    }

    @Test
    void getSumOfPaths3() {
        List<String> lines = new ArrayList<>(
                Arrays.asList(
                        "...#......",
                        ".......#..",
                        "#.........",
                        "..........",
                        "......#...",
                        ".#........",
                        ".........#",
                        "..........",
                        ".......#..",
                        "#...#....."
                ));
        Universe universe = new Universe(lines, 100);

        assertEquals(8410, universe.getSumOfPaths());
    }

}