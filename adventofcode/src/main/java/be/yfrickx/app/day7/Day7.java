package be.yfrickx.app.day7;

import be.yfrickx.app.day6.Day6;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day7 {

    public static String solve() {
        ClassLoader classLoader = Day7.class.getClassLoader();
        File file = new File(classLoader.getResource("day7.txt").getFile());
        List<String> lineList;
        try {
            lineList = FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parseLines(lineList);
    }

    private static String parseLines(List<String> lines) {
        List<Hand> hands = new ArrayList<>();

        lines.forEach(line -> hands.add(new Hand(line)));

        Collections.sort(hands);

        long sum = 0;
        for (int i = 0; i < hands.size(); i++) {
            sum += hands.get(i).getBid() * (i + 1);
        }

        return String.valueOf(sum);
    }
}
