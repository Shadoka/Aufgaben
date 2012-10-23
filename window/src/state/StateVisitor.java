package state;

import model.RectangularPartCollection;

/**
 * Represents a visitor for all kinds of states.
 * 
 * @author Tilmann
 * 
 */
public interface StateVisitor {
	/**
	 * Calcution of the visible Parts of a Window based on a Cached State.
	 * 
	 * @param state
	 *            : StateVisitor
	 * @return : RectangularPartCollection
	 */
	public RectangularPartCollection visit(Cached state);

	/**
	 * Calcution of the visible Parts of a Window based on a NotCached State.
	 * 
	 * @param state
	 *            : State
	 * @return : RectangularPartCollection
	 */
	public RectangularPartCollection visit(NotCached state);
}
