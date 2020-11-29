package usantatecla.draughts.views;

import usantatecla.draughts.controllers.*;

public class View extends SubView implements InteractorControllersVisitor {

    private static final String TITTLE = "Draughts";

    // private StartView startView;
    private PlayView playView;
    private ResumeView resumeView;

    public View() {
        super();
        // this.startView = new StartView();
        this.playView = new PlayView();
        this.resumeView = new ResumeView();
    }

    public void interact(InteractorController controller) {
        assert controller != null;
        controller.accept(this);
    }

    void interact(StartController startController) {
        assert startController != null;
        this.console.writeln(this.TITTLE);
        new GameView().write(startController);
        startController.start();
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
        this.resumeView.interact(resumeController);
    }

}
