package be.yfrickx.app.day8;

import be.yfrickx.app.day7.Day7;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        Map<String, Node> nodeMap = new HashMap<>();

        lines.remove(0);
        lines.remove(0);

        lines.forEach(line -> {
            String node = line.split("=")[0].trim();
            String left = line.split("=")[1].split(",")[0].replace("(", "").trim();
            String right = line.split("=")[1].split(",")[1].replace(")", "").trim();
            nodeMap.put(node, new Node(left, right));
        });


    }
}
