package state;

import model.Window;

public abstract class AbstractState implements State {

	private final Window master;

	protected AbstractState(Window master) {
		this.master = master;
	}

	public Window getMaster() {
		return master;
	}

}
