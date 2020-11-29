package usantatecla.draughts.models;

public class StateBuilder {

    private State state;

    public StateBuilder() {
        state = new State();
    }

    public State build(StateValue stateValue) {
        while (this.state.getValueState() != stateValue) {
            this.state.next();
        }
        return this.state;
    }

}
