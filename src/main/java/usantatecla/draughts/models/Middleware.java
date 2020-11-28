package usantatecla.draughts.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Middleware {

    protected static List<Coordinate> removedCoordinates;
    protected static int pair;

    private Middleware next;

    public Middleware() {
        removedCoordinates = new ArrayList<>();
    }

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract Error check(Board board, Turn turn, Coordinate... coordinates);

    protected Error checkNext(Board board, Turn turn, Coordinate... coordinates) {
        if (next == null) {
            return null;
        }
        return next.check(board, turn, coordinates);
    }

    public List<Coordinate> getRemovedCoordinates() {
        return removedCoordinates;
    }

    public int getPair() {
        return pair;
    }

}
