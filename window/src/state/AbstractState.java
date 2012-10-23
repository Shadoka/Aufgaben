package state;

import model.Window;

/**
 * Class represents a abstract state in which every Window is. It is extended by
 * the given Window.
 * 
 * @author Tilmann
 * 
 */
public abstract class AbstractState implements State {

	private final Window master;

	protected AbstractState(Window master) {
		this.master = master;
	}

	public Window getMaster() {
		return master;
	}

}
