package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import usantatecla.draughts.controllers.PlayController;
import usantatecla.draughts.utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class PlayViewTest {

    @Mock
    PlayController playController;
    @Mock
    Console console;
    @InjectMocks
    View playView;

    @Before
    public void initMocks() {
        // MockitoAnnotations.initMocks(this);
    }

    public void testGivenPlayViewWhenInteractThenCancel() {
        /*
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("-1").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).cancel();
         */
    }

    public void testGivenPlayViewWhenInteractWithEmptyThenError() {
        /*when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));*/
    }

    public void testGivenPlayViewWhenInteractWithNotPointThenError() {
        /*when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("87,68").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));*/
    }

    public void testGivenPlayViewWhenInteractWithBadFormatThenError() {
        /*when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("a3.42").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));*/
    }

    public void testGivenPlayViewWhenInteractWithBadRangeThenError() {
        /*when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("93.49").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));*/
    }

    public void testGivenPlayViewWhenInteractWithNegativeThenError() {
        /*when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("43.-34").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));*/
    }

    public void testGivenPlayViewWhenInteractWithSecondNegativeThenError() {
        /*when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("4-3.34").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));*/
    }

    public void testGivenPlayViewWhenInteractWithTwoCoordiantesThenOk() {
        /*
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
        */
    }

    public void testGivenPlayViewWhenInteractWithThirdCoordiantesThenOk() {
        /*when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("23.32.41");
        playView.interact(playController);
        verify(playController).move(
                new Coordinate(1, 2),
                new Coordinate(2, 1),
                new Coordinate(3, 0));*/
    }

}