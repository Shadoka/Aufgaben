package behaviour;

import philosopher.Philosopher;
import state.EatingState;
import state.State;
import state.ThinkingState;
import util.StateVisitor;

public class DontCareBehaviour implements BehaviourPattern {

	private DontCareBehaviour() {

	}

	public static DontCareBehaviour create() {
		return new DontCareBehaviour();
	}

	@Override
	public void changeState(final Philosopher p) {
		p.setState(p.getState().accept(new StateVisitor() {

			@Override
			public State visit(EatingState state) {
				p.reportMeThinking();
				return ThinkingState.create(p);
			}

			@Override
			public State visit(ThinkingState state) {
				p.reportMeEating();
				return EatingState.create(p);
			}

		}));
	}

}
