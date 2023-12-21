package be.yfrickx.app.day11;

import be.yfrickx.app.Day;

public class Day11 extends Day {
    public Day11(String fileName) {
        super(fileName);
    }

    @Override
    public String solvePart1() {
        Universe universe = new Universe(lines, 2);
        return String.valueOf(universe.getSumOfPaths());
    }

    @Override
    public String solvePart2() {
        Universe universe = new Universe(lines, 1000000);
        return String.valueOf(universe.getSumOfPaths());
    }
}
