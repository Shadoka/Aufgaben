package state;

import model.RectangularPartCollection;
import model.Window;
import event.CalculationNeededEvent;
import event.Event;
import event.EventVisitor;

public class NotCached extends AbstractState {

	private NotCached(Window master) {
		super(master);
	}

	public static NotCached create(Window master) {
		return new NotCached(master);
	}

	@Override
	public RectangularPartCollection accept(StateVisitor v) {
		return v.visit(this);
	}

	@Override
	public void handleNotification(Event e) {
		e.accept(new EventVisitor() {

			@Override
			public void visit(CalculationNeededEvent e) {
				// Nothing left to do here.
			}

		});
	}
}
