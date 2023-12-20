package be.yfrickx.app.day6;

import be.yfrickx.app.day5.Day5;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {

    public static String solve() {

        List<Race> races = new ArrayList<>();

        ClassLoader classLoader = Day6.class.getClassLoader();
        File file = new File(classLoader.getResource("day6.txt").getFile());
        List<String> lineList;
        try {
            lineList = FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // partA
        List<Integer> times = Arrays.asList(lineList.get(0)
                .replace("Time: ", "")
                .trim()
                .split(" "))
                .stream()
                .filter(str -> !str.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // partA
        List<Integer> distances = Arrays.asList(lineList.get(1)
                .replace("Distance: ", "")
                .trim()
                .split(" "))
                .stream()
                .filter(str -> !str.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

//        for (int i = 0; i < times.size(); i++) {
//            races.add(new Race(times.get(i), distances.get(i)));
//        }
//
//        int result = 1;
//
//        for (Race race : races) {
//            result *= race.getNumWaysToBeat();
//        }

        long time = Long.parseLong(lineList.get(0)
                .replace("Time: ", "")
                .replace(" ", ""));

        long distance = Long.parseLong(lineList.get(1)
                .replace("Distance: ", "")
                .replace(" ", ""));


        return String.valueOf(new Race(time, distance).getNumWaysToBeat());
    }
}
