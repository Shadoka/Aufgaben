package state;

import model.RectangularPartCollection;
import event.Event;

public interface State {
	public RectangularPartCollection accept(StateVisitor v);

	public void handleNotification(Event e);
}
