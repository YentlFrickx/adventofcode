package be.yfrickx.app.day8;

import be.yfrickx.app.day7.Day7;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Day8 {

    public static String solve() {
        ClassLoader classLoader = Day8.class.getClassLoader();
        File file = new File(classLoader.getResource("day8.txt").getFile());
        List<String> lineList;
        try {
            lineList = FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parseLines(lineList);
    }

    private static String parseLines(List<String> lines) {
        String directions = lines.get(0);

    }
}
