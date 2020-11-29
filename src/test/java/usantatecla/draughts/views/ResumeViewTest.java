package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import usantatecla.draughts.controllers.ResumeController;
import usantatecla.draughts.utils.YesNoDialog;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResumeViewTest {

    @Mock
    ResumeController resumeController;
    @Mock
    YesNoDialog yesNoDialog;
    @InjectMocks
    View resumeView;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    public void testGivenResumeViewWhenAnswerYesThenReset(){
        /*
        when(yesNoDialog.read(anyString())).thenReturn(true);
        resumeView.interact(resumeController);
        verify(resumeController).reset();
         */
    }

    public void testGivenResumeViewWhenAnswerNoThenNext(){
        /*when(yesNoDialog.read(anyString())).thenReturn(false);
        resumeView.interact(resumeController);
        verify(resumeController).next();
         */
    }

}