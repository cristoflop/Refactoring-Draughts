package usantatecla.draughts.views;

import usantatecla.draughts.controllers.InteractorController;
import usantatecla.draughts.models.Color;
import usantatecla.draughts.utils.YesNoDialog;

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

    private YesNoDialog yesNoDialog;

    public View() {
        super();
        this.yesNoDialog = new YesNoDialog();
    }

    public String read(Color color) {
        final String titleColor = View.PROMPT.replace(View.COLOR_PARAM, View.COLOR_VALUES[color.ordinal()]);
        return this.console.readString(titleColor);
    }

    public boolean isCanceledFormat(String string) {
        return string.equals(View.CANCEL_FORMAT);
    }

    public boolean isMoveFormat(String string) {
        return Pattern.compile(View.MOVEMENT_FORMAT).matcher(string).find();
    }

    public void writeError() {
        this.console.writeln(View.ERROR_MESSAGE);
    }

    public void writeLost() {
        this.console.writeln(LOST_MESSAGE);
    }

    public void writeTitle() {
        this.console.writeln(View.TITTLE);
    }

    public void writePieces(InteractorController controller) {
        GameView gameView = new GameView();
        gameView.write(controller);
    }

    public boolean readResume() {
        return this.yesNoDialog.read(View.RESUME_MESSAGE);
    }

}
