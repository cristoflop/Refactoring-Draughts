package usantatecla.draughts.views;

import usantatecla.draughts.controllers.PlayController;
import usantatecla.draughts.controllers.ResumeController;
import usantatecla.draughts.controllers.StartController;
import usantatecla.draughts.models.Color;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Error;
import usantatecla.draughts.utils.YesNoDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class View extends SubView {

    private static final String TITTLE = "Draughts";
    private static final String RESUME_MESSAGE = "¿Queréis jugar otra";

    private static final String COLOR_PARAM = "#color";
    private static final String[] COLOR_VALUES = {"blancas", "negras"};
    private static final String PROMPT = "Mueven las " + View.COLOR_PARAM + ": ";
    private static final String CANCEL_FORMAT = "-1";
    private static final String MOVEMENT_FORMAT = "[1-8]{2}(\\.[1-8]{2}){1,2}";
    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    private String string;

    private YesNoDialog yesNoDialog;

    public View() {
        super();
        this.yesNoDialog = new YesNoDialog();
    }

    public void interact(StartController startController) {
        assert startController != null;
        this.console.writeln(View.TITTLE);
        new GameView().write(startController);
        startController.start();
    }

    public void interact(PlayController playController) {
        assert playController != null;
        Error error;
        do {
            error = null;
            this.string = this.read(playController.getColor());
            if (this.isCanceledFormat())
                playController.cancel();
            else if (!this.isMoveFormat()) {
                error = Error.BAD_FORMAT;
                this.writeError();
            } else {
                error = playController.move(this.getCoordinates());
                new GameView().write(playController);
                if (error == null && playController.isBlocked())
                    this.writeLost();
            }
        } while (error != null);
    }

    public void interact(ResumeController resumeController) {
        assert resumeController != null;
        if (this.yesNoDialog.read(View.RESUME_MESSAGE))
            resumeController.reset();
        else
            resumeController.next();
    }

    private String read(Color color) {
        final String titleColor = View.PROMPT.replace(View.COLOR_PARAM, View.COLOR_VALUES[color.ordinal()]);
        return this.console.readString(titleColor);
    }

    private boolean isCanceledFormat() {
        return string.equals(View.CANCEL_FORMAT);
    }

    private boolean isMoveFormat() {
        return Pattern.compile(View.MOVEMENT_FORMAT).matcher(string).find();
    }

    private void writeError() {
        this.console.writeln(View.ERROR_MESSAGE);
    }

    private Coordinate[] getCoordinates() {
        assert this.isMoveFormat();
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

    private void writeLost() {
        this.console.writeln(LOST_MESSAGE);
    }

    public void writeTitle() {
        this.console.writeln(View.TITTLE);
    }

    public void writePieces(int row, String pieces) {
        GameView gameView = new GameView();
    }

}
