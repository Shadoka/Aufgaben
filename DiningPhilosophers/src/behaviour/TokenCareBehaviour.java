package behaviour;

import philosopher.Philosopher;
import state.EatingState;
import state.State;
import state.ThinkingState;
import util.StateVisitor;

public class TokenCareBehaviour implements BehaviourPattern {

	private TokenCareBehaviour() {

	}

	public static TokenCareBehaviour create() {
		return new TokenCareBehaviour();
	}

	@Override
	public void changeState(final Philosopher p) {
		p.setState(p.getState().accept(new StateVisitor() {

			@Override
			public State visit(EatingState state) {
				p.getRight().put();
				p.getLeft().put();
				p.reportMeThinking();
				return ThinkingState.create(p);
			}

			@Override
			public State visit(ThinkingState state) {
				p.getLeft().get();
				p.getRight().get();
				p.reportMeEating();
				return EatingState.create(p);
			}

		}));
	}

}
