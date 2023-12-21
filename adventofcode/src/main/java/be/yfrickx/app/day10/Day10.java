package be.yfrickx.app.day10;

import be.yfrickx.app.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 extends Day {

    private Map map;
    public Day10(String fileName) {
        super(fileName);
        map = new Map(lines);
    }

    @Override
    public String solvePart1() {
        return String.valueOf(map.getFurthestSteps());
    }

    @Override
    public String solvePart2() {
        return String.valueOf(map.getNumEnclosedTiles());
    }
}
