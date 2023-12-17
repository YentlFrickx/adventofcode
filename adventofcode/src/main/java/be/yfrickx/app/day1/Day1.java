package be.yfrickx.app.day1;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.*;

public class Day1 {

    static Map<String, Integer> numberMap  = new HashMap<String, Integer>() {{
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
    }};

    public static String solve() {

        int sum = 0;

        ClassLoader classLoader = Day1.class.getClassLoader();
        File file = new File(classLoader.getResource("day1a.txt").getFile());
        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")) {
            while (it.hasNext()) {
                String line = it.nextLine();
                String combined = getFirstIntSmart(line) + getLastIntSmart(line);
                sum += Integer.parseInt(combined);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return String.valueOf(sum);
    }

    // Simple first part
    private static String getFirstInt(String str) {
        return str.replaceAll("[^0-9]+", "").charAt(0) + "";
    }

    private static int findFirstOccurrence(String strValue, int intValue, String searchString) {
        int strIndex = searchString.indexOf(strValue);
        int intIndex = searchString.indexOf("" + intValue);

        if (strIndex == -1) {
            return intIndex;
        } else if (intIndex == -1) {
            return strIndex;
        }
        return Integer.min(strIndex, intIndex);
    }

    private static int findLastOccurrence(String strValue, int intValue, String searchString) {
        int strIndex = searchString.lastIndexOf(strValue);
        int intIndex = searchString.lastIndexOf("" + intValue);

        if (strIndex == -1) {
            return intIndex;
        } else if (intIndex == -1) {
            return strIndex;
        }
        return Integer.max(strIndex, intIndex);
    }

    private static String getFirstIntSmart(String str) {
        int number = numberMap.entrySet().stream().min(getMinEntryComparator(str)).get().getValue();
        return "" + number;
    }

    private static String getLastIntSmart(String str) {
        int number = numberMap.entrySet().stream().max(getMaxEntryComparator(str)).get().getValue();
        return "" + number;
    }

    private static Comparator<Map.Entry<String, Integer>> getMinEntryComparator(String str) {
        return (entry1, entry2) -> {
            int firstOccurence1 = findFirstOccurrence(entry1.getKey(), entry1.getValue(), str);
            int firstOccurence2 = findFirstOccurrence(entry2.getKey(), entry2.getValue(), str);

            if (firstOccurence1 == -1 && firstOccurence2 != -1) {
                return 1;
            } else if (firstOccurence2 == -1 && firstOccurence1 != -1) {
                return -1;
            }
            return Integer.compare(firstOccurence1, firstOccurence2);
        };
    }

    private static Comparator<Map.Entry<String, Integer>> getMaxEntryComparator(String str) {
        return (entry1, entry2) -> {
            int lastOccurrence1 = findLastOccurrence(entry1.getKey(), entry1.getValue(), str);
            int lastOccurrence2 = findLastOccurrence(entry2.getKey(), entry2.getValue(), str);

            if (lastOccurrence1 == -1 && lastOccurrence2 != -1) {
                return -1;
            } else if (lastOccurrence2 == -1 && lastOccurrence1 != -1) {
                return 1;
            }
            return Integer.compare(lastOccurrence1, lastOccurrence2);
        };
    }

    // Simple first part
    private static String getLastInt(String str) {
        String temp = str.replaceAll("[^0-9]+", "");
        return temp.charAt(temp.length()-1) + "";
    }
}