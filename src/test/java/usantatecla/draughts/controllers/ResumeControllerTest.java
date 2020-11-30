package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usantatecla.draughts.models.*;
import usantatecla.draughts.views.View;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ResumeControllerTest {

    private State state;

    @Mock
    private View resumeView;

    @InjectMocks
    private ResumeController resumeController;

    @Before
    public void before() {
        Game game = new GameBuilder().build();
        this.state = new StateBuilder().build(StateValue.FINAL);
        this.resumeController = new ResumeController(game, state);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenResumeControllerWhenResumeGameMoveToInitialStateRequiereCorrectThenNotError() {
        resumeController.reset();
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

    @Test
    public void testGivenResumeControllerWhenAnswerYesThenReset() {
        when(resumeView.readResume()).thenReturn(true);
        resumeController.control();
        assertEquals(this.state.getValueState(), StateValue.INITIAL);
    }

    @Test
    public void testGivenResumeControllerWhenAnswerNoThenNext() {
        when(resumeView.readResume()).thenReturn(false);
        resumeController.control();
        assertEquals(this.state.getValueState(), StateValue.EXIT);
    }
}