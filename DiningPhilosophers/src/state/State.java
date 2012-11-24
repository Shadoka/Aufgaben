package state;

import util.StateVisitor;

public interface State {

	public State accept(StateVisitor v);
}
