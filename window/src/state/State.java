package state;

import model.RectangularPartCollection;
import event.Event;

/**
 * Class represents a state in which every Window is.
 * 
 * @author Tilmann
 * 
 */
public interface State {
	/**
	 * Method returns a RectangularPartCollection. When it´s a cached state, it
	 * will be a saved value. When it´s not saved, it will be calculated.
	 * 
	 * @param v
	 *            : StateVisitor
	 * @return : RectangularPartCollection
	 */
	public RectangularPartCollection accept(StateVisitor v);

	/**
	 * The state will change the state of his Window, depending on the event.
	 * 
	 * @param e
	 *            : Event
	 */
	public void handleNotification(Event e);
}
