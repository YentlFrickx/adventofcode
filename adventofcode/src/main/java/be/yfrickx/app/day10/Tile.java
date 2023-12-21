package be.yfrickx.app.day10;

import java.util.*;

public class Tile {

    public record Coords(int x, int y){
        public boolean isValid(int minX, int maxX, int minY, int maxY) {
            return x >= minX && x <= maxX && y >=minY && y <= maxY;
        }
    }

    private String tileValue;

    private final Coords coords;

    public Tile(String tileValue, Coords coords) {
        this.tileValue = tileValue;
        this.coords = coords;
    }

    public List<Coords> getConnectingCoords() {
        return switch (tileValue) {
            case "|" ->
                    new ArrayList<>(Arrays.asList(new Coords(this.coords.x, this.coords.y - 1), new Coords(this.coords.x, this.coords.y + 1)));
            case "L" ->
                    new ArrayList<>(Arrays.asList(new Coords(this.coords.x, this.coords.y - 1), new Coords(this.coords.x + 1, this.coords.y)));
            case "J" ->
                    new ArrayList<>(Arrays.asList(new Coords(this.coords.x - 1, this.coords.y), new Coords(this.coords.x, this.coords.y - 1)));
            case "-" ->
                    new ArrayList<>(Arrays.asList(new Coords(this.coords.x - 1, this.coords.y), new Coords(this.coords.x + 1, this.coords.y)));
            case "F" ->
                    new ArrayList<>(Arrays.asList(new Coords(this.coords.x, this.coords.y + 1), new Coords(this.coords.x + 1, this.coords.y)));
            case "7" ->
                    new ArrayList<>(Arrays.asList(new Coords(this.coords.x, this.coords.y + 1), new Coords(this.coords.x - 1, this.coords.y)));
            default -> Collections.singletonList(this.coords);
        };
    }

    public String getTileValueUsingConnectingTiles(List<Tile> connectingTiles) {
        int xDiff = connectingTiles
                .stream()
                .filter(tile -> tile.getCoords().x() != this.getCoords().x())
                .findFirst()
                .orElse(this)
                .getCoords()
                .x() - this.getCoords().x();
        int yDiff = connectingTiles
                .stream()
                .filter(tile -> tile.getCoords().y() != this.getCoords().y())
                .findFirst()
                .orElse(this)
                .getCoords()
                .y() - this.getCoords().y();

        if (xDiff == 0) {
            return "|";
        } else if (yDiff == 0) {
            return "-";
        } else if (xDiff == 1 && yDiff == -1) {
            return "L";
        } else if (xDiff == -1 && yDiff == -1) {
            return "J";
        } else if (xDiff == -1 && yDiff == 1) {
            return "7";
        } else if (xDiff == 1 && yDiff == 1) {
            return "F";
        }

        return ".";
    }

    public void setTileValueUsingConnectingTiles(List<Tile> connectingTiles) {
        this.tileValue = getTileValueUsingConnectingTiles(connectingTiles);
    }



    public boolean connectsWith(Coords coords) {
        return getConnectingCoords().contains(coords);
    }

    public List<Coords> getNeighbouringCoords() {
        return new ArrayList<>(Arrays.asList(
                new Coords(this.coords.x, this.coords.y - 1),
                new Coords(this.coords.x, this.coords.y + 1),
                new Coords(this.coords.x - 1, this.coords.y),
                new Coords(this.coords.x + 1, this.coords.y)));
    }

    public Coords getCoords() {
        return coords;
    }

    public String getTileValue() {
        return tileValue;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile tile)) return false;
        return Objects.equals(tileValue, tile.tileValue) && Objects.equals(coords, tile.coords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tileValue, coords);
    }
}
