package usantatecla.draughts.controllers;

public interface InteractorControllersVisitor {
	
	void visit(StartController startController);
	void visit(PlayController playController);
	void visit(ResumeController resumeController);
}