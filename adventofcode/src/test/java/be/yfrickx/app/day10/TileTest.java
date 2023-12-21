package be.yfrickx.app.day10;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void getConnectingCoords() {
        Tile testTile = new Tile("|", new Tile.Coords(5, 5));
        assertEquals(Arrays.asList(new Tile.Coords(5, 4), new Tile.Coords(5, 6)), testTile.getConnectingCoords());
        testTile = new Tile("L", new Tile.Coords(5, 5));
        assertEquals(Arrays.asList(new Tile.Coords(5, 4), new Tile.Coords(6, 5)), testTile.getConnectingCoords());
        testTile = new Tile("J", new Tile.Coords(5, 5));
        assertEquals(Arrays.asList(new Tile.Coords(4, 5), new Tile.Coords(5, 4)), testTile.getConnectingCoords());
        testTile = new Tile("-", new Tile.Coords(5, 5));
        assertEquals(Arrays.asList(new Tile.Coords(4, 5), new Tile.Coords(6, 5)), testTile.getConnectingCoords());
        testTile = new Tile("F", new Tile.Coords(5, 5));
        assertEquals(Arrays.asList(new Tile.Coords(5, 6), new Tile.Coords(6, 5)), testTile.getConnectingCoords());
        testTile = new Tile("7", new Tile.Coords(5, 5));
        assertEquals(Arrays.asList(new Tile.Coords(5, 6), new Tile.Coords(4, 5)), testTile.getConnectingCoords());

    }

    @Test
    void getTileValueUsingConnectingTiles() {
        Tile tileS = new Tile("S", new Tile.Coords(5, 5));
        Tile tileEast = new Tile(".", new Tile.Coords(6, 5));
        Tile tileSouth = new Tile(".", new Tile.Coords(5, 6));
        Tile tileWest = new Tile(".", new Tile.Coords(4, 5));
        Tile tileNorth = new Tile(".", new Tile.Coords(5, 4));

        assertEquals("|", tileS.getTileValueUsingConnectingTiles(Arrays.asList(tileNorth, tileSouth)));
        assertEquals("-", tileS.getTileValueUsingConnectingTiles(Arrays.asList(tileEast, tileWest)));
        assertEquals("L", tileS.getTileValueUsingConnectingTiles(Arrays.asList(tileEast, tileNorth)));
        assertEquals("J", tileS.getTileValueUsingConnectingTiles(Arrays.asList(tileWest, tileNorth)));
        assertEquals("7", tileS.getTileValueUsingConnectingTiles(Arrays.asList(tileWest, tileSouth)));
        assertEquals("F", tileS.getTileValueUsingConnectingTiles(Arrays.asList(tileEast, tileSouth)));
    }

}