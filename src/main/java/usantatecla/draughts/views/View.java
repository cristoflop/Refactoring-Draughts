package usantatecla.draughts.views;

import usantatecla.draughts.controllers.*;
import usantatecla.draughts.utils.YesNoDialog;

public class View extends SubView implements InteractorControllersVisitor {

    private static final String TITTLE = "Draughts";
    private static final String RESUME_MESSAGE = "¿Queréis jugar otra";

    private YesNoDialog yesNoDialog;

    private PlayView playView;

    public View() {
        super();
        this.playView = new PlayView();
        this.yesNoDialog = new YesNoDialog();
    }

    public void interact(InteractorController controller) {
        assert controller != null;
        controller.accept(this);
    }

    void interact(StartController startController) {
        assert startController != null;
        this.console.writeln(TITTLE);
        new GameView().write(startController);
        startController.start();
    }

    void interact(ResumeController resumeController) {
        assert resumeController != null;
        if (this.yesNoDialog.read(RESUME_MESSAGE))
            resumeController.reset();
        else
            resumeController.next();
    }

    @Override
    public void visit(StartController startController) {
        assert startController != null;
        this.interact(startController);
    }

    @Override
    public void visit(PlayController playController) {
        assert playController != null;
        this.playView.interact(playController);
    }

    @Override
    public void visit(ResumeController resumeController) {
        assert resumeController != null;
        this.interact(resumeController);
    }

}
