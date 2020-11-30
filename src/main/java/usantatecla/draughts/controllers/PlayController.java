package usantatecla.draughts.controllers;

import usantatecla.draughts.models.Error;
import usantatecla.draughts.models.*;
import usantatecla.draughts.views.View;

import java.util.ArrayList;
import java.util.List;

public class PlayController extends InteractorController {

    private CancelController cancelController;
    private MoveController moveController;
    private View playView;

    public PlayController(Game game, State state) {
        super(game, state);
        this.cancelController = new CancelController(game, state);
        this.moveController = new MoveController(game, state);
        this.playView = new View();
    }

    public Error move(Coordinate... coordinates) {
        return this.moveController.move(coordinates);
    }

    public void cancel() {
        this.cancelController.cancel();
    }

    public Color getColor() {
        return this.game.getTurnColor();
    }

    public boolean isBlocked() {
        return this.game.isBlocked();
    }

    @Override
    public void control() {
        Error error;
        do {
            error = null;
            String string = this.playView.read(this.getColor());
            if (this.playView.isCanceledFormat(string))
                this.cancel();
            else if (!this.playView.isMoveFormat(string)) {
                error = Error.BAD_FORMAT;
                this.playView.writeError();
            } else {
                error = this.move(this.getCoordinates(string));
                this.playView.writePieces(this);
                if (error == null && this.isBlocked())
                    this.playView.writeLost();
            }
        } while (error != null);
    }

    private Coordinate[] getCoordinates(String string) {
        assert playView.isMoveFormat(string);
        List<Coordinate> coordinateList = new ArrayList<>();
        while (string.length() > 0) {
            coordinateList.add(Coordinate.getInstance(string.substring(0, 2)));
            string = string.substring(2, string.length());
            if (string.length() > 0 && string.charAt(0) == '.')
                string = string.substring(1, string.length());
        }
        Coordinate[] coordinates = new Coordinate[coordinateList.size()];
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] = coordinateList.get(i);
        }
        return coordinates;
    }

}