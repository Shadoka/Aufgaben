package state;

import philosopher.Philosopher;
import util.StateVisitor;

public class EatingState extends AbstractState {

	private EatingState(Philosopher master) {
		super(master);
	}

	public static EatingState create(Philosopher master) {
		return new EatingState(master);
	}

	@Override
	public State accept(StateVisitor v) {
		return v.visit(this);
	}
}
