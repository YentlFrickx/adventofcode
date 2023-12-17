package be.yfrickx.app.day2;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

public class Day2 {

    public static String solve() {
        ClassLoader classLoader = Day2.class.getClassLoader();
        File file = new File(classLoader.getResource("day2.txt").getFile());
        int sum = 0;
        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")) {
            while (it.hasNext()) {
                String line = it.nextLine();
                Game game = new Game(line);
//                if (game.isPossible(12, 13, 14)) {
//                    sum += game.getId();
//                }
                sum += game.getPowerOfMinimumSet();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return String.valueOf(sum);
    }

}
