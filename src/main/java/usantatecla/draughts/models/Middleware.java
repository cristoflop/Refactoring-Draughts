package usantatecla.draughts.models;

import java.util.List;

public abstract class Middleware {

    protected Board board;

    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract Error check(Board board, Turn turn, List<Coordinate> removedCoordinates, Coordinate... coordinates);

    protected Error checkNext(Board board, Turn turn, List<Coordinate> removedCoordinates, Coordinate... coordinates) {
        if (next == null) {
            return null;
        }
        return next.check(board, turn, removedCoordinates, coordinates);
    }

    public Board getBoard() {
        return board;
    }

    protected void setBoard(Board board) {
        this.board = board;
    }

}
