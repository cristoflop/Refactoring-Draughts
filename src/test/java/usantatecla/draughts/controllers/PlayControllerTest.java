package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usantatecla.draughts.models.*;
import usantatecla.draughts.utils.Console;
import usantatecla.draughts.views.View;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayControllerTest {

    private Game game;
    private State state;
    @Mock
    private Console console;
    @Mock
    private View playView;
    @InjectMocks
    private PlayController playController;

    @Before
    public void initMocks() {
        this.game = new GameBuilder().build();
        this.state = new StateBuilder().build(StateValue.IN_GAME);
        this.playController = new PlayController(this.game, this.state);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenPlayControllerWhenMoveThenOk() {
        playController = new PlayController(this.game, this.state);
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        playController.move(origin, target);
        assertEquals(playController.getColor(target), Color.WHITE);
        assertFalse(game.isBlocked());
    }

    @Test
    public void testGivenPlayControllerWhenMoveWithoutPiecesThenIsBlocked() {
        Game game = new GameBuilder().rows(
                "        ",
                "        ",
                "        ",
                "        ",
                " n      ",
                "b       ",
                "        ",
                "        ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(3, 2);
        playController.move(origin, target);
        assertEquals(playController.getColor(target), Color.WHITE);
        assertTrue(game.isBlocked());
    }

    @Test
    public void testGivenPlayControllerWhenMoveWithoutMovementsThenIsBlocked() {
        Game game = new GameBuilder().rows(
                "        ",
                "        ",
                "   n    ",
                "  b b   ",
                "     b  ",
                "b       ",
                "        ",
                "        ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        playController.move(origin, target);
        assertEquals(playController.getColor(target), Color.WHITE);
        assertTrue(game.isBlocked());
    }

    @Test
    public void testGivenPlayControllerWhenCancelThenOk() {
        Game game = new GameBuilder().build();
        playController = new PlayController(game, new State());
        playController.cancel();
        assertEquals(Color.BLACK, playController.getColor());
        assertFalse(game.isBlocked());
    }

    @Test
    public void testGivenPlayControllerWhenControlThenCancel() {
        when(this.playView.read(Color.WHITE)).thenReturn("-1");
        when(this.playView.isCanceledFormat(anyString())).thenReturn(true);
        this.playController.control();
        assertEquals(this.state.getValueState(), StateValue.FINAL);
    }

    @Test
    public void testGivenPlayControllerWhenControlWithEmptyThenError() {
        when(this.playView.read(Color.WHITE)).thenReturn("").thenReturn("-1");
        when(this.playView.isCanceledFormat(anyString())).thenReturn(false).thenReturn(true);
        when(this.playView.isMoveFormat(anyString())).thenReturn(false);
        this.playController.control();
        verify(this.playView).writeError();
    }

    @Test
    public void testGivenPlayControllerWhenControlWithNotPointThenError() {
        when(this.playView.read(Color.WHITE)).thenReturn("87,68").thenReturn("-1");
        when(this.playView.isCanceledFormat(anyString())).thenReturn(false).thenReturn(true);
        when(this.playView.isMoveFormat(anyString())).thenReturn(false);
        this.playController.control();
        verify(this.playView).writeError();
    }


    @Test
    public void testGivenPlayControllerWhenControlWithBadFormatThenError() {
        when(this.playView.read(Color.WHITE)).thenReturn("a3.42").thenReturn("-1");
        when(this.playView.isCanceledFormat(anyString())).thenReturn(false).thenReturn(true);
        when(this.playView.isMoveFormat(anyString())).thenReturn(false);
        this.playController.control();
        verify(this.playView).writeError();
    }

    @Test(expected = AssertionError.class)
    public void testGivenPlayControllerWhenControlWithBadRangeThenError() {
        when(this.playView.read(Color.WHITE)).thenReturn("93.49").thenReturn("-1");
        when(this.playView.isCanceledFormat(anyString())).thenReturn(false).thenReturn(true);
        when(this.playView.isMoveFormat(anyString())).thenReturn(true);
        this.playController.control();
        verify(this.playView).writeError();
    }

    @Test
    public void testGivenPlayControllerWhenControlWithNegativeThenError() {
        when(this.playView.read(Color.WHITE)).thenReturn("43.-34").thenReturn("-1");
        when(this.playView.isCanceledFormat(anyString())).thenReturn(false).thenReturn(true);
        when(this.playView.isMoveFormat(anyString())).thenReturn(false);
        this.playController.control();
        verify(this.playView).writeError();
    }

    @Test
    public void testGivenPlayControllerWhenControlWithSecondNegativeThenError() {
        when(this.playView.read(Color.WHITE)).thenReturn("4-3.34").thenReturn("-1");
        when(this.playView.isCanceledFormat(anyString())).thenReturn(false).thenReturn(true);
        when(this.playView.isMoveFormat(anyString())).thenReturn(false);
        this.playController.control();
        verify(this.playView).writeError();
    }

    @Test
    public void testGivenPlayControllerWhenControlWithTwoCoordiantesThenOk() {
        when(this.playView.read(Color.WHITE)).thenReturn("61.52");
        when(this.playView.isCanceledFormat(anyString())).thenReturn(false);
        when(this.playView.isMoveFormat(anyString())).thenReturn(true);
        this.playController.control();
        verify(this.playView).writePieces(this.playController);
    }

    @Test
    public void testGivenPlayControllerWhenControlWithThirdCoordiantesThenErrorTooMuchJumps() {
        when(this.playView.read(Color.WHITE)).thenReturn("61.52.43").thenReturn("-1");
        when(this.playView.isCanceledFormat(anyString())).thenReturn(false).thenReturn(true);
        when(this.playView.isMoveFormat(anyString())).thenReturn(true);
        this.playController.control();
        verify(this.playView).writePieces(this.playController);
        assertEquals(this.state.getValueState(), StateValue.FINAL);
    }

}