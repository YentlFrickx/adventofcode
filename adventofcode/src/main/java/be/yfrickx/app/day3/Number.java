package be.yfrickx.app.day3;

public class Number {
    int row;
    int colStart;
    int colEnd;
    int value;

    public Number(int row, int colStart, int colEnd, int value) {
        this.row = row;
        this.colStart = colStart;
        this.colEnd = colEnd;
        this.value = value;
    }

    public boolean isAdjecent(Gear gear) {
        int row = gear.row;
        int col = gear.col;

        boolean possibleRow = row == this.row ||
                row == this.row - 1 ||
                row == this.row + 1;

        boolean possibleCol = (col >= this.colStart - 1 && col <= this.colEnd + 1);
        return possibleRow && possibleCol;
    }
}
