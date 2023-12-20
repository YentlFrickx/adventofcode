package be.yfrickx.app.day8;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {


    @Test
    void parseLines() {
        List<String> lines = new ArrayList<>(Arrays.asList("LLR", "", "AAA = (BBB, BBB)", "BBB = (AAA, ZZZ)", "ZZZ = (ZZZ, ZZZ)"));

        assertEquals("6", Day8.parseLines(lines));
    }

}