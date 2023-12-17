package be.yfrickx.app.day2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final int id;

    private List<CubeSet> cubeSets;

    // example input
    // Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
    public Game(String input) {
        String cleaned = input.replace("Game ", "");
        id = Integer.parseInt(cleaned.substring(0, cleaned.indexOf(":")));
        parseSets(input);
    }

    private void parseSets(String input) {
        String cleaned = input.substring(input.indexOf(":") + 2); // +2 to remove the : itself and the space afterwards
        String[] sets = cleaned.split(";");
        this.cubeSets = Arrays.stream(sets).map(CubeSet::new).collect(Collectors.toList());
    }

    public boolean isPossible(int red, int green, int blue) {
        return cubeSets.stream().allMatch(cubeset -> cubeset.isPossible(red, green, blue));
    }

    public int getPowerOfMinimumSet() {
        int maxBlue = cubeSets.stream().map(CubeSet::getAmountBlue).max(Integer::compareTo).get();
        int maxGreen = cubeSets.stream().map(CubeSet::getAmountGreen).max(Integer::compareTo).get();
        int maxRed = cubeSets.stream().map(CubeSet::getAmountRed).max(Integer::compareTo).get();

        return maxBlue * maxGreen * maxRed;
    }

    public int getId() {
        return id;
    }
}
