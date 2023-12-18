package be.yfrickx.app.day3;

import be.yfrickx.app.day1.Day1;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static String solve() {
        ClassLoader classLoader = Day1.class.getClassLoader();
        File file = new File(classLoader.getResource("day3.txt").getFile());

        List<String> lines = new ArrayList<>();
        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")) {
            while (it.hasNext()) {
                lines.add(it.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        int sum = analyzeLines(lines);


        return String.valueOf(sum);
    }

    private static int analyzeLines(List<String> lines) {
        int sum = 0;
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);

            StringBuilder currentMatch = new StringBuilder();
            char[] charArray = line.toCharArray();

            String lineWithoutNumbers = line;
            for (int x = 0; x < charArray.length; x++) {

                // Build new number
                if (Character.isDigit(charArray[x])) {
                    currentMatch.append(charArray[x]);
                }

                if (!Character.isDigit(charArray[x]) || x == charArray.length-1){
                    // End of number is reached, start checking if we need to add up
                    if (!currentMatch.isEmpty()) {
                        if (findAdjacentSymbols(lines, (x-currentMatch.length()), x-1, y)) {
                            sum += Integer.parseInt(currentMatch.toString());
                        }

                        lineWithoutNumbers = lineWithoutNumbers.replaceFirst(currentMatch.toString(), "");
                        // Reset string builder
                        currentMatch = new StringBuilder();
                    }
                }
            }
        }
        return sum;
    }

    private static boolean findAdjacentSymbols(List<String> lines, int start, int end, int line) {
        int searchStart = Math.max(0, start - 1);
        int searchEnd = Math.min(end + 2, lines.get(line).length()-1); // +2 to actually include the next char in the substring
        boolean previousLine = line != 0 && findSymbols(lines.get(line - 1), searchStart, searchEnd);
        boolean currentLine = findSymbols(lines.get(line), searchStart, searchEnd);
        boolean nextLine = line != lines.size() - 1 && findSymbols(lines.get(line + 1), searchStart, searchEnd);

        return previousLine || currentLine || nextLine;
    }

    private static boolean findSymbols(String line, int start, int end) {
        return line.substring(start, end).matches(".*[^0-9.].*");
    }


}
