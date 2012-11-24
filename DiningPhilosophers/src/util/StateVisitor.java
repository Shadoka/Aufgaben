package util;

import state.EatingState;
import state.State;
import state.ThinkingState;

public interface StateVisitor {

	public State visit(EatingState state);

	public State visit(ThinkingState state);
}
