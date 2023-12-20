package be.yfrickx.app.day10;

import be.yfrickx.app.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 extends Day {

    public Day10(String fileName) {
        super(fileName);
    }

    @Override
    public String solvePart1() {
        List<List<Tile>> map = new ArrayList<>();
        List<Tile> loop = new ArrayList<>();
        int currentX = 0;
        int currentY = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            map.add(Arrays.stream(line.split("")).map(Tile::new).collect(Collectors.toList()));
            if (line.contains("S")) {
                loop.add(new Tile("S"));
                currentY = i;
                currentX = line.indexOf("S");
            }
        }



        return "";
    }

    @Override
    public String solvePart2() {
        return null;
    }
}
