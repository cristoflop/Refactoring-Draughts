package usantatecla.draughts.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.GameBuilder;
import usantatecla.draughts.models.State;
import usantatecla.draughts.models.StateValue;

public class StartControllerTest {

    private State state;
    private StartController startController;

    @Before
    public void before(){
        Game game = new GameBuilder().build();
        this.state = new State();
        this.startController = new StartController(game, state);
    }

     @Test
    public void givenStartControllerWhenStartGameThenChangeState() {
        assertEquals(StateValue.INITIAL, state.getValueState());
        startController.start();
        assertEquals(StateValue.IN_GAME, state.getValueState());
    }

}