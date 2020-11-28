package usantatecla.draughts.models;

import java.util.List;

public class MiddlewareCorrectPairMove extends Middleware {

    @Override
    public Error check(Board board, Turn turn, Coordinate... coordinates) {
        removedCoordinates.clear();
        pair = 0;
        Error error = null;
        do {
            error = this.isCorrectPairMove(board, turn, pair, coordinates);
            if (error == null) {
                this.pairMove(board, turn, pair, coordinates);
                pair++;
            }
        } while (pair < coordinates.length - 1 && error == null);
        if (error != null) {
            return error;
        }
        return checkNext(board, turn, coordinates);
    }

    private Error isCorrectPairMove(Board board, Turn turn, int pair, Coordinate... coordinates) {
        assert coordinates[pair] != null;
        assert coordinates[pair + 1] != null;
        if (board.isEmpty(coordinates[pair]))
            return Error.EMPTY_ORIGIN;
        if (turn.getOppositeColor() == board.getColor(coordinates[pair]))
            return Error.OPPOSITE_PIECE;
        if (!board.isEmpty(coordinates[pair + 1]))
            return Error.NOT_EMPTY_TARGET;
        List<Piece> betweenDiagonalPieces =
                board.getBetweenDiagonalPieces(coordinates[pair], coordinates[pair + 1]);
        return board.getPiece(coordinates[pair]).isCorrectMovement(betweenDiagonalPieces, pair, coordinates);
    }

    private void pairMove(Board board, Turn turn, int pair, Coordinate... coordinates) {
        Coordinate forRemoving = this.getBetweenDiagonalPiece(board, turn, pair, coordinates);
        if (forRemoving != null) {
            removedCoordinates.add(0, forRemoving);
            board.remove(forRemoving);
        }
        board.move(coordinates[pair], coordinates[pair + 1]);
        if (board.getPiece(coordinates[pair + 1]).isLimit(coordinates[pair + 1])) {
            Color color = board.getColor(coordinates[pair + 1]);
            board.remove(coordinates[pair + 1]);
            board.put(coordinates[pair + 1], new Draught(color));
        }
    }

    private Coordinate getBetweenDiagonalPiece(Board board, Turn turn, int pair, Coordinate... coordinates) {
        assert coordinates[pair].isOnDiagonal(coordinates[pair + 1]);
        List<Coordinate> betweenCoordinates = coordinates[pair].getBetweenDiagonalCoordinates(coordinates[pair + 1]);
        if (betweenCoordinates.isEmpty())
            return null;
        for (Coordinate coordinate : betweenCoordinates) {
            if (board.getPiece(coordinate) != null)
                return coordinate;
        }
        return null;
    }

}
