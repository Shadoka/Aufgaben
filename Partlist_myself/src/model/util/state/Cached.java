package model.util.state;

import model.main.ComponentCommon;
import model.main.Materialmap;

public abstract class Cached extends AbstractState{

	private Materialmap materials;
	
	protected Cached(ComponentCommon master, Materialmap materials) {
		super(master);
		this.materials = materials;
	}

	public Materialmap getMaterials() {
		return materials;
	}

	protected void setMaterials(Materialmap materials) {
		this.materials = materials;
	}
}
