package usantatecla.draughts.controllers;

import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.views.View;

public class ResumeController extends InteractorController {

    private View view;

    public ResumeController(Game game, State state) {
        super(game, state);
        this.view = new View();
    }

    public void next() {
        this.state.next();
    }

    public void reset() {
        this.state.reset();
        this.game.reset();
    }

    @Override
    public void control() {
        if (this.view.readResume())
            this.reset();
        else
            this.next();
    }

}
