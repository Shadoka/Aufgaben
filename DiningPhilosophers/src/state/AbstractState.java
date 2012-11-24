package state;

import philosopher.Philosopher;

public abstract class AbstractState implements State {

	private Philosopher master;

	protected AbstractState(Philosopher master) {
		this.master = master;
	}

	public Philosopher getMaster() {
		return master;
	}

	public void setMaster(Philosopher master) {
		this.master = master;
	}
}
