package usantatecla.draughts.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.GameBuilder;
import usantatecla.draughts.models.State;
import usantatecla.draughts.models.StateValue;

public class ResumeControllerTest {

    private State state;
    private ResumeController resumeController;

    @Before
    public void before(){
        Game game = new GameBuilder().build();
        this.state = new State();
        this.resumeController = new ResumeController(game, state);
    }

    @Test
    public void givenResumeControllerWhenResumeGameMoveToInitialStateRequiereCorrectThenNotError() {
        assertEquals(StateValue.INITIAL, this.state.getValueState());
        resumeController.next();
        assertEquals(StateValue.IN_GAME, this.state.getValueState());
        resumeController.next();
        assertEquals(StateValue.FINAL, this.state.getValueState());
        resumeController.reset();
        assertEquals(StateValue.INITIAL, this.state.getValueState());
    }

    @Test(expected = AssertionError.class)
    public void givenResumeControllerWhenResumeGameMoveOutThenError() {
        assertEquals(StateValue.INITIAL, this.state.getValueState());
        resumeController.next();
        assertEquals(StateValue.IN_GAME, this.state.getValueState());
        resumeController.next();
        assertEquals(StateValue.FINAL, this.state.getValueState());
        resumeController.next();
        assertEquals(StateValue.EXIT, this.state.getValueState());
        resumeController.next();
    }
}