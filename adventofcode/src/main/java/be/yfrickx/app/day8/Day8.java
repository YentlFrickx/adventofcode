package be.yfrickx.app.day8;

import be.yfrickx.app.day7.Day7;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    protected static String parseLines(List<String> lines) {
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



        String[] dirArray = directions.split("");

        int stepsA = solveA(nodeMap, dirArray);
        return solveB(nodeMap, dirArray);
    }

    private static int pathCountForNode(String nodeName, Map<String, Node> nodeMap, String[] dirArray) {
        int stepCount = 0;
        int dirIndex = 0;

        while (!nodeName.endsWith("Z")) {
            Node currentNode = nodeMap.get(nodeName);
            if (dirArray[dirIndex].equals("L")) {
                nodeName = currentNode.getLeftNode();
            } else {
                nodeName = currentNode.getRightNode();
            }
            stepCount++;
            dirIndex++;
            if (dirIndex >= dirArray.length) {
                dirIndex = 0;
            }
        }
        return stepCount;
    }


    private static long lcm(long x, long y) {
        long max = Math.max(x, y);
        long min = Math.min(x, y);
        long lcm = max;
        while (lcm % min != 0) {
            lcm += max;
        }
        return lcm;
    }

    private static String solveB(Map<String, Node> nodeMap, String[] dirArray) {
        List<String> currentNodes = nodeMap.keySet().stream().filter(str -> str.endsWith("A")).collect(Collectors.toList());

        List<Integer> stepsToFirstGoal = currentNodes.stream().map(node -> pathCountForNode(node, nodeMap, dirArray)).collect(Collectors.toList());

        long result = stepsToFirstGoal.get(0);

        for (int i = 1; i < stepsToFirstGoal.size(); i++) {
            result = lcm(result, stepsToFirstGoal.get(i));
        }

        return String.valueOf(result);
    }

    private static int solveA(Map<String, Node> nodeMap, String[] dirArray) {
        String currentNodeName = "AAA";
        Node nextNode = nodeMap.get(currentNodeName);
        int steps = 0;
        int i = 0;
        while (!currentNodeName.equals("ZZZ")) {
            if (dirArray[i].equals("L")) {
                currentNodeName = nextNode.getLeftNode();
            } else {
                currentNodeName = nextNode.getRightNode();
            }
            nextNode = nodeMap.get(currentNodeName);
            i++;
            steps++;
            if (i >= dirArray.length) {
                i = 0;
            }
        }
        return steps;
    }
}
