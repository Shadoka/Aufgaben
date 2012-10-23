package state;

import model.RectangularPartCollection;
import model.Window;
import event.CalculationNeededEvent;
import event.Event;
import event.EventVisitor;

/**
 * Class represents a state a Window can be in. In this state are the visible
 * Parts of the given Window already saved.
 * 
 * @author Tilmann
 * 
 */
public class Cached extends AbstractState {

	private RectangularPartCollection parts;

	private Cached(Window master, RectangularPartCollection parts) {
		super(master);
		this.parts = parts;
	}

	/**
	 * Factory-Method for Cached states.
	 * 
	 * @param master
	 *            : Window
	 * @param parts
	 *            : RectangularPartCollection
	 * @return : Cached
	 */
	public static Cached create(Window master, RectangularPartCollection parts) {
		return new Cached(master, parts);
	}

	@Override
	public RectangularPartCollection accept(StateVisitor v) {
		return v.visit(this);
	}

	public RectangularPartCollection getParts() {
		return parts;
	}

	public void setParts(RectangularPartCollection parts) {
		this.parts = parts;
	}

	@Override
	public void handleNotification(Event e) {
		e.accept(new EventVisitor() {

			@Override
			public void visit(CalculationNeededEvent e) {
				Cached.this.getMaster().setState(
						NotCached.create(Cached.this.getMaster()));
			}

		});
	}

}
