package usantatecla.draughts.models;

import java.util.List;

public class MiddlewareCorrectGlobalMove extends Middleware {

    @Override
    public Error check(Board board, Turn turn, Coordinate... coordinates) {
        return isCorrectGlobalMove(removedCoordinates, coordinates);
    }

    private Error isCorrectGlobalMove(List<Coordinate> removedCoordinates, Coordinate... coordinates) {
        if (coordinates.length > 2 && coordinates.length > removedCoordinates.size() + 1)
            return Error.TOO_MUCH_JUMPS;
        return null;
    }

}
