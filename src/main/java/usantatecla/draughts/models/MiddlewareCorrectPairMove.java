package usantatecla.draughts.models;

import java.util.ArrayList;
import java.util.List;

public class MiddlewareCorrectPairMove extends Middleware {

    private List<Coordinate> removedCoordinates;

    public MiddlewareCorrectPairMove() {
        this.removedCoordinates = new ArrayList<>();
    }

    @Override
    public Error check(Board other, Turn turn, List<Coordinate> x, Coordinate... coordinates) {
        this.setBoard(other);
        this.removedCoordinates.clear();
        int pair = 0;
        Error error = null;
        do {
            error = this.isCorrectPairMove(turn, pair, coordinates);
            if (error == null) {
                this.pairMove(pair, coordinates);
                pair++;
            }
        } while (pair < coordinates.length - 1 && error == null);
        if (error != null) {
            return error;
        }
        return checkNext(board, turn, this.removedCoordinates, coordinates);
    }

    private Error isCorrectPairMove(Turn turn, int pair, Coordinate... coordinates) {
        assert coordinates[pair] != null;
        assert coordinates[pair + 1] != null;
        if (this.board.isEmpty(coordinates[pair]))
            return Error.EMPTY_ORIGIN;
        if (turn.getOppositeColor() == board.getColor(coordinates[pair]))
            return Error.OPPOSITE_PIECE;
        if (!this.board.isEmpty(coordinates[pair + 1]))
            return Error.NOT_EMPTY_TARGET;
        List<Piece> betweenDiagonalPieces =
                this.board.getBetweenDiagonalPieces(coordinates[pair], coordinates[pair + 1]);
        return this.board.getPiece(coordinates[pair]).isCorrectMovement(betweenDiagonalPieces, pair, coordinates);
    }

    private void pairMove(int pair, Coordinate... coordinates) {
        Coordinate forRemoving = this.getBetweenDiagonalPiece(pair, coordinates);
        if (forRemoving != null) {
            this.removedCoordinates.add(0, forRemoving);
            this.board.remove(forRemoving);
        }
        this.board.move(coordinates[pair], coordinates[pair + 1]);
        if (this.board.getPiece(coordinates[pair + 1]).isLimit(coordinates[pair + 1])) {
            Color color = this.board.getColor(coordinates[pair + 1]);
            this.board.remove(coordinates[pair + 1]);
            this.board.put(coordinates[pair + 1], new Draught(color));
        }
    }

    private Coordinate getBetweenDiagonalPiece(int pair, Coordinate... coordinates) {
        assert coordinates[pair].isOnDiagonal(coordinates[pair + 1]);
        List<Coordinate> betweenCoordinates = coordinates[pair].getBetweenDiagonalCoordinates(coordinates[pair + 1]);
        if (betweenCoordinates.isEmpty())
            return null;
        for (Coordinate coordinate : betweenCoordinates) {
            if (this.board.getPiece(coordinate) != null)
                return coordinate;
        }
        return null;
    }

}
