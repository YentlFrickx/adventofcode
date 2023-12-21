package be.yfrickx.app.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Map {

    List<List<Tile>> map = new ArrayList<>();
    List<Tile> loop = new ArrayList<>();

    public Map(List<String> lines) {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);

            List<Tile> tileList = new ArrayList<>();
            String[] strArray =  line.split("");
            for (int x = 0; x < strArray.length; x++) {
                tileList.add(new Tile(strArray[x], new Tile.Coords(x, y)));
                if (strArray[x].equals("S")) {
                    loop.add(tileList.get(tileList.size()-1));
                }
            }
            map.add(tileList);
        }

        findLoop();
        fixStartTile();
    }

    public int getFurthestSteps() {
        return loop.size()/2;
    }

    private void fixStartTile() {
        List<Tile> connectingTiles = loop.get(0)
                .getNeighbouringCoords() // List of coords adjacent to the starting position
                .stream()
                .filter(coords -> coords.isValid(0, map.get(0).size()-1, 0, map.size()-1))
                .map(coords -> map.get(coords.y()).get(coords.x())) // Stream of tiles adjacent to the starting position
                .filter(tile -> tile.connectsWith(loop.get(0).getCoords())) // Stream of tiles that connect to the starting position
                .toList();
        loop.get(0).setTileValueUsingConnectingTiles(connectingTiles);
    }

    private void findLoop() {
        Tile nextTile = loop.get(0)
                .getNeighbouringCoords() // List of coords adjacent to the starting position
                .stream()
                .filter(coords -> coords.isValid(0, map.get(0).size()-1, 0, map.size()-1))
                .map(coords -> map.get(coords.y()).get(coords.x())) // Stream of tiles adjacent to the starting position
                .filter(tile -> tile.connectsWith(loop.get(0).getCoords()))
                .findFirst()
                .get();

        while (!loop.get(0).equals(nextTile)) {
            loop.add(nextTile);
            // get connected tile that isn't already in the loop
            nextTile = nextTile
                    .getConnectingCoords()
                    .stream()
                    .filter(coords -> !coords.equals(loop.get(loop.size()-2).getCoords()))
                    .map(coords -> map.get(coords.y()).get(coords.x()))
                    .findFirst().get();
        }
    }

    public long getNumEnclosedTiles() {
        List<Tile> templist = map.stream().flatMap(Collection::parallelStream).filter(this::isEnclosedInLoop).toList();
        return map.stream().flatMap(Collection::parallelStream).filter(this::isEnclosedInLoop).count();
    }

    protected boolean isEnclosedInLoop(Tile tile) {
        if (loop.contains(tile)) {
            return false;
        }
        List<Tile> verticalTiles = loop.parallelStream()
                .filter(loopTile -> loopTile.getCoords().x() == tile.getCoords().x()
                        && loopTile.getCoords().y() > tile.getCoords().y()) // only get tiles beneath the tile to compare
                .filter(loopTile -> !loopTile.getTileValue().equals("|")) // only interested in tiles going horizontally
                .toList();

        int rightCount = 0;

        for (Tile vertTile : verticalTiles) {
            if (vertTile.getTileValue().matches("[-FL]")) {
                rightCount++;
            }
        }

        List<Tile> horizontalTiles = loop.parallelStream()
                .filter(loopTile -> loopTile.getCoords().y() == tile.getCoords().y()
                        && loopTile.getCoords().x() > tile.getCoords().x())
                .filter(loopTile -> !loopTile.getTileValue().equals("-")) // only interested in tiles going vertically
                .toList();

        int upCount = 0;

        for (Tile horTile : horizontalTiles) {
            if (horTile.getTileValue().matches("[|LJ]")) {
                upCount++;
            }
        }

        return rightCount%2 != 0 && upCount%2 != 0;
    }
}
