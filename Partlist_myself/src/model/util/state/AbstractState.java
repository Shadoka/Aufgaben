package model.util.state;

import model.main.ComponentCommon;

public abstract class AbstractState implements State{

	private ComponentCommon master;

	protected AbstractState(ComponentCommon master) {
		this.master = master;
	}
	
	public ComponentCommon getMaster() {
		return master;
	}

	public void setMaster(ComponentCommon master) {
		this.master = master;
	}
}
