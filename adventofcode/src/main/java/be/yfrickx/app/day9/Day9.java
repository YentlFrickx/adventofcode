package be.yfrickx.app.day9;

import be.yfrickx.app.Day;

import java.util.ArrayList;
import java.util.List;

public class Day9 extends Day {


    public Day9(String fileName) {
        super(fileName);
    }

    @Override
    public String solvePart1() {
        List<Integer> results = new ArrayList<>();

        this.lines.forEach(line -> results.add(new Sequence(line).getNextValue()));
        return String.valueOf(results.stream().reduce(0, Integer::sum));
    }

    @Override
    public String solvePart2() {
        List<Integer> results = new ArrayList<>();

        this.lines.forEach(line -> results.add(new Sequence(line).getPreviousValue()));
        return String.valueOf(results.stream().reduce(0, Integer::sum));
    }


}
