package usantatecla.draughts;

import usantatecla.draughts.controllers.Logic;
import usantatecla.draughts.controllers.InteractorController;
import usantatecla.draughts.views.View;

class Draughts {
    
    private View view;

    private Logic logic;

    private Draughts(){
        this.view = new View();
        this.logic = new Logic();
    }

    private void play() {
        InteractorController controller;
		do {
			controller = this.logic.getController();
			if (controller != null)
				this.view.interact(controller);
		} while (controller != null); 
    }

    public static void main(String[] args){
        new Draughts().play();
    }
    
}