package be.yfrickx.app.day10;

import java.util.Objects;

public class Tile {

    private final String tileValue;

    public Tile(String tileValue) {
        this.tileValue = tileValue;
    }

    public boolean connectsNorth() {
        return tileValue.equals("|") || tileValue.equals("L") || tileValue.equals("J");
    }

    public boolean connectsEast() {
        return tileValue.equals("-") || tileValue.equals("L") || tileValue.equals("F");
    }

    public boolean connectsSouth() {
        return tileValue.equals("|") || tileValue.equals("7") || tileValue.equals("F");
    }

    public boolean connectsWest() {
        return tileValue.equals("-") || tileValue.equals("7") || tileValue.equals("J");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return tileValue.equals(tile.tileValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tileValue);
    }
}
