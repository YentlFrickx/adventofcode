package be.yfrickx.app.day11;

import java.util.*;

public class Universe {

    private final List<List<Space>> map;

    private final int expensionRate;

    public Universe(List<String> lines, int expensionRate) {
        this.expensionRate = expensionRate;
        List<List<String>> inputMap = generateAndExpandStringMap(lines);
        map = generateSpaceMap(inputMap);
    }

    protected List<List<String>> generateAndExpandStringMap(List<String> lines) {
        List<List<String>> inputMap = new ArrayList<>();
        lines.forEach( line -> {
            if (!line.contains("#")) {
                inputMap.add(new ArrayList<>(Collections.nCopies(line.length(), "X")));
            } else {
                inputMap.add(new ArrayList<>(Arrays.asList(line.split(""))));
            }
        });

        for (int i = inputMap.get(0).size() - 1; i >= 0; i--) {
            int searchIndex = i;
            boolean emptyLine = inputMap.parallelStream().allMatch(list -> list.get(searchIndex).matches("[.X]"));
            if (emptyLine) {
                inputMap.parallelStream().forEach(list -> list.set(searchIndex, "X"));
            }
        }

        return inputMap;
    }

    protected List<List<Space>> generateSpaceMap(List<List<String>> stringMap) {
        List<List<Space>> map = new ArrayList<>();
        for (int y = 0; y < stringMap.size(); y++) {
            List<String> currentLine = stringMap.get(y);
            List<Space> spaces = new ArrayList<>();
            for (int x = 0; x < currentLine.size(); x++) {
                String currentValue = currentLine.get(x);
                if (currentValue.equals("#")) {
                    spaces.add(new Space(x, y, true, 1));
                } else if (currentValue.equals("X")) {
                    spaces.add(new Space(x, y, false, this.expensionRate));
                } else {
                    spaces.add(new Space(x, y, false, 1));
                }

            }
            map.add(spaces);
        }
        return map;
    }

    protected List<List<Space>> getMap() {
        return map;
    }

    private List<Space> getGalaxies() {
        return map.stream().flatMap(Collection::parallelStream).filter(Space::isGalaxy).toList();
    }

    protected long getPathLengthBetweenSpaces(Space spaceA, Space spaceB) {
        int startX = spaceA.x();
        int startY = spaceA.y();
        int endX = spaceB.x();
        int endY = spaceB.y();

        int xMod = (endX - startX) > 0 ? 1 : -1;
        int yMod = (endY - startY) > 0 ? 1 : -1;

        List<Space> path = new ArrayList<>();

        for (int x = startX; x != endX; x+=xMod) {
            path.add(map.get(startY).get(x));
        }


        for (int y = startY; y != endY; y+=yMod) {
            path.add(map.get(y).get(endX));
        }
        return path.stream().mapToLong(Space::size).sum();
    }

    public long getSumOfPaths() {
        LinkedList<Space> galaxies = new LinkedList<>(getGalaxies());

        long sum = 0;
        while(!galaxies.isEmpty()) {
            Space galaxy = galaxies.pop();
            sum += galaxies.parallelStream().mapToLong(glx -> getPathLengthBetweenSpaces(galaxy, glx)).sum();
        }
        return sum;
    }

}
