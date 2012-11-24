package state;

import philosopher.Philosopher;
import util.StateVisitor;

public class ThinkingState extends AbstractState {

	private ThinkingState(Philosopher master) {
		super(master);
	}

	public static ThinkingState create(Philosopher master) {
		return new ThinkingState(master);
	}

	@Override
	public State accept(StateVisitor v) {
		return v.visit(this);
	}
}
