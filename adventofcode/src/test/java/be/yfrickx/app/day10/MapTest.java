package be.yfrickx.app.day10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void getFurthestStepsSimple() {
        List<String> lines = new ArrayList<>(Arrays.asList(".....", ".S-7.", ".|.|.", ".L-J.", "....."));

        Map map = new Map(lines);

        assertEquals(4, map.getFurthestSteps());
    }
    @Test
    void getFurthestStepsComplex() {
        List<String> lines = new ArrayList<>(Arrays.asList("..F7.", ".FJ|.", "SJ.L7", "|F--J", "LJ..."));

        Map map = new Map(lines);

        assertEquals(8, map.getFurthestSteps());
    }

    @Test
    void isEnclosedInLoopSimple() {
        List<String> lines = new ArrayList<>(
                Arrays.asList(
                        ".....",
                        ".S-7.",
                        ".|.|.",
                        ".L-J.",
                        "....."));

        Map map = new Map(lines);

        assertTrue(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(2, 2))));
        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(0, 0))));
        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(0, 2))));
        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(4, 2))));
    }

    @Test
    void isEnclosedInLoopComplex() {
        List<String> lines = new ArrayList<>(
                Arrays.asList(
                        "..F7.",
                        ".FJ|.",
                        "SJ.L7",
                        "|F--J",
                        "LJ..."));

        // enkel boven
        // 2 'verticale' pipes
        // 1 naar rechts, 1 naar links -> contained
        //
        Map map = new Map(lines);

        assertTrue(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(2, 2))));
        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(0, 0))));
        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(0, 1))));
        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(2, 4))));
    }

    @Test
    void isEnclosedInLoopComplex2() {
        List<String> lines = new ArrayList<>(
                Arrays.asList(
                        "..........",
                        ".S------7.",
                        ".|F----7|.",
                        ".||OOOO||.",
                        ".||OOOO||.",
                        ".|L-7F-J|.",
                        ".|II||II|.",
                        ".L--JL--J.",
                        ".........."
                ));

        Map map = new Map(lines);

        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(4, 4))));
        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(3, 3))));
        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(0, 0))));
        assertFalse(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(0, 1))));
        assertTrue(map.isEnclosedInLoop(new Tile(".", new Tile.Coords(2, 6))));
    }

    @Test
    void getNumEnclosedTiles() {
        List<String> lines = new ArrayList<>(
                Arrays.asList(
                        "..........",
                        ".S------7.",
                        ".|F----7|.",
                        ".||OOOO||.",
                        ".||OOOO||.",
                        ".|L-7F-J|.",
                        ".|II||II|.",
                        ".L--JL--J.",
                        ".........."
                ));

        Map map = new Map(lines);

        assertEquals(4, map.getNumEnclosedTiles());
    }


}