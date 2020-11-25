package usantatecla.draughts.utils;

public class ConcreteCoordinate implements Coordinate {

    protected int row;
    protected int column;

    public ConcreteCoordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}

