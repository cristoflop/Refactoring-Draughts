package usantatecla.draughts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import usantatecla.draughts.controllers.AllControllerTest;
import usantatecla.draughts.models.AllModelTest;
import usantatecla.draughts.views.AllViewTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    AllModelTest.class, 
    AllControllerTest.class, 
    AllViewTest.class } )
public final class AllTest {
}