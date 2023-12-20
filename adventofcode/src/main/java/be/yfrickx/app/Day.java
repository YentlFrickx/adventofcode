package be.yfrickx.app;

import be.yfrickx.app.day8.Day8;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Day {

    protected List<String> lines;
    public Day(String fileName) {
        ClassLoader classLoader = Day8.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try {
            this.lines = FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String solvePart1();

    public abstract String solvePart2();
}
