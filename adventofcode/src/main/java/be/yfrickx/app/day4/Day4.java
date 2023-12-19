package be.yfrickx.app.day4;

import be.yfrickx.app.day1.Day1;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    public static String solve() {
        ClassLoader classLoader = Day4.class.getClassLoader();
        File file = new File(classLoader.getResource("day4.txt").getFile());

        ArrayList<Integer> cards = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        int sum = 0;
        int index = 0;
        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")) {
            while (it.hasNext()) {
                cards.add(1);
                lines.add(it.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line: lines) {
            String cleanLine = line.substring(line.indexOf(":") + 2);
            List<Integer> winningNumbers = getWinningNumbers(cleanLine);
            List<Integer> ownNumbers = getOwnNumbers(cleanLine);
            List<Integer> commonNumbers = new ArrayList<>(ownNumbers);
            commonNumbers.retainAll(winningNumbers);

            addWinningCards(index, commonNumbers.size(), cards);
            //part 1
//                if (!commonNumbers.isEmpty()) {
//                    sum += Math.pow(2, commonNumbers.size()-1);
//                }

            index++;
        }

        return String.valueOf(cards.stream().mapToLong(Integer::longValue).sum());
    }

    private static void addWinningCards(int currentIndex, int amountWinning, List<Integer> cards) {
        for (int i = 0; i < amountWinning; i++) {
            int indexToAdjust = currentIndex + 1 + i;
            if (indexToAdjust < cards.size()) {
                cards.set(indexToAdjust, cards.get(indexToAdjust) + cards.get(currentIndex));
            }
        }
    }

    private static List<Integer> getWinningNumbers(String numberString) {
        List<String> winningStrings  = Arrays.asList(numberString.split("\\|")[0].trim().split(" "));

        return winningStrings.stream().filter(str -> !str.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static List<Integer> getOwnNumbers(String numberString) {
        List<String> winningStrings  = Arrays.asList(numberString.split("\\|")[1].trim().split(" "));

        return winningStrings.stream().filter(str -> !str.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
    }
}
