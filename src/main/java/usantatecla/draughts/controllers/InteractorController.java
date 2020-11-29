package usantatecla.draughts.controllers;

import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.Piece;
import usantatecla.draughts.models.State;

import java.util.ArrayList;
import java.util.List;

public abstract class InteractorController extends Controller {

    protected InteractorController(Game game, State state) {
        super(game, state);
    }

    public Piece getPiece(Coordinate coordinate) {
        return this.game.getPiece(coordinate);
    }

    abstract public void control();

}
