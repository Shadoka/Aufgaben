package model;

import java.util.Collection;
import java.util.Vector;

import observer.Observer;
import state.Cached;
import state.NotCached;
import state.State;
import state.StateVisitor;
import event.CalculationNeededEvent;
import event.Event;

public class Window extends RectangularArea implements Observer {

	private static int nextWindowNumber = 0;

	private static final int InitialWidth = 200;
	private static final int InitialHeight = 100;
	private static final int Origin = 0;

	private State state;

	private static final Point getInitialPosition() {
		return new Point(Origin, Origin);
	}

	final int number;
	private boolean open;
	Collection<Window> aboveMe;

	public Window() {
		super(getInitialPosition(), InitialWidth, InitialHeight);
		this.number = nextWindowNumber++;
		this.open = true;
		this.setAboveMe(new Vector<Window>());
		this.state = NotCached.create(this);
	}

	private boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
		this.update(CalculationNeededEvent.create());
		this.notifyObservers(CalculationNeededEvent.create());
		System.out.println("SetOpen(" + open + "): " + this);
	}

	public void move(int deltaX, int deltaY) {
		super.move(deltaX, deltaY);
		this.update(CalculationNeededEvent.create());
		this.notifyObservers(CalculationNeededEvent.create());
		System.out.println("Move: " + this);
	}

	public void resize(int width, int height) throws NegativeLengthException {
		super.resize(width, height);
		this.update(CalculationNeededEvent.create());
		this.notifyObservers(CalculationNeededEvent.create());
		System.out.println("Resize: " + this);
	}

	public RectangularPartCollection handleState(State state) {
		return state.accept(new StateVisitor() {

			@Override
			public RectangularPartCollection visit(Cached state) {
				System.out.println("Hab die Teile schon");
				return state.getParts();
			}

			@Override
			public RectangularPartCollection visit(NotCached state) {
				RectangularPartCollection parts = Window.this
						.getVisibleContextIntern();
				Window.this.setState(Cached.create(Window.this, parts));
				System.out.println("Neu berechnet!");
				return parts;
			}

		});
	}

	/**
	 * Returns a RectangularPart which has the same attribute values like
	 * <this>. This is possible because of the superclass RectangularArea, which
	 * are both extending.
	 * 
	 * @return : RectangularPart
	 */
	public RectangularPart toPart() {
		return new RectangularPart(this.getLeftUpperCorner(), this.getWidth(),
				this.getHeight());
	}

	/**
	 * Returns a collection of all RectangularParts of this Window, which are
	 * visible.
	 * 
	 * @return : RectangularPartCollection
	 */
	private RectangularPartCollection getVisibleContextIntern() {
		RectangularPartCollection result = new RectangularPartCollection();
		Vector<Window> aboveMe = this.aboveMeToVector();

		if (aboveMe.isEmpty()) {
			if (this.isOpen()) {
				RectangularPart meAsPart = this.toPart();
				try {
					meAsPart.setParent(this);
				} catch (HierarchyException e) {
					throw new Error("Hierarchy shall be guaranteed!");
				}
				result.add(meAsPart);
			}
		} else {
			if (this.isOpen()) {
				int counter = 1;
				Window windowBeforeMe = aboveMe.get(aboveMe.size() - counter);
				counter++;
				while (!windowBeforeMe.isOpen() && counter <= aboveMe.size()) {
					aboveMe.get(aboveMe.size() - counter);
				}
				RectangularPartCollection beforeMe = windowBeforeMe
						.getVisibleContext();
				if (beforeMe.getParts().isEmpty()) {
					RectangularPart meAsPart = this.toPart();
					try {
						meAsPart.setParent(this);
					} catch (HierarchyException e) {
						throw new Error("Hierarchy shall be guaranteed!");
					}
					result.add(meAsPart);
				} else {
					for (RectangularPart part : beforeMe.getParts()) {
						try {
							result.add(this.calculateVisibleContext(part));
						} catch (HierarchyException e) {
							throw new Error("Hierarchy shall be guaranteed!");
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Extern access on the visible parts of this window. Whether the parts have
	 * to be calculated or not will be decided by the state.
	 * 
	 * @return : RectangularPartCollection
	 */
	public RectangularPartCollection getVisibleContext() {
		return this.handleState(this.getState());
	}

	/**
	 * This method calculates all visible parts of this Window with the specific
	 * RectangularPart <part>. If the whole given Part is visible, it will be
	 * simply returned.
	 * 
	 * @param part
	 *            : RectangularPart
	 * @return : RectangularPartCollection
	 * @throws HierarchyException
	 */
	public RectangularPartCollection calculateVisibleContext(
			RectangularPart part) throws HierarchyException {
		RectangularPartCollection result = new RectangularPartCollection();

		if (this.overlaps(part)) {
			if (this.isSpaceWest(part)) {
				RectangularPart westPart = this.calculateSpaceWest(part);
				System.out.println("West");
				westPart.setParent(this);
				result.add(westPart);
			}
			if (this.isSpaceEast(part)) {
				RectangularPart eastPart = this.calculateSpaceEast(part);
				System.out.println("East");
				eastPart.setParent(this);
				result.add(eastPart);
			}
			if (this.isSpaceNorth(part)) {
				RectangularPart northPart = this.calculateSpaceNorth(part);
				System.out.println("North");
				northPart.setParent(this);
				result.add(northPart);
			}
			if (this.isSpaceSouth(part)) {
				RectangularPart southPart = this.calculateSpaceSouth(part);
				System.out.println("South");
				southPart.setParent(this);
				result.add(southPart);
			}
		} else {
			RectangularPart meAsPart = this.toPart();
			meAsPart.setParent(this);
			result.add(meAsPart);
		}

		return result;
	}

	/**
	 * Returns the collection of windows above this window as a Vector.
	 * 
	 * @return : Vector
	 */
	public Vector<Window> aboveMeToVector() {
		return (Vector<Window>) this.getAboveMe();
	}

	public String toString() {
		return "WINDOW " + this.getNumber() + " : " + super.toString() + " "
				+ (this.isOpen() ? "OPEN" : "CLOSED");
	}

	private int getNumber() {
		return this.number;
	}

	@Override
	boolean isInTransitively(RectangularPart part) {
		return false;
	}

	private Collection<Window> getAboveMe() {
		return aboveMe;
	}

	private void setAboveMe(Collection<Window> aboveMe) {
		this.aboveMe = aboveMe;
	}

	private void deregisterAtAllAbove() {
		for (Window current : this.getAboveMe()) {
			current.deregister(this);
		}
	}

	private void registerAllAboveMe() {
		for (Window current : this.getAboveMe()) {
			this.register(current);
		}
	}

	public void setAsTop(Window window) {
		if (this.equals(window)) {
			this.deregisterAtAllAbove();
			this.registerAllAboveMe();
			this.getAboveMe().clear();
			this.update(CalculationNeededEvent.create());
			this.notifyObservers(CalculationNeededEvent.create());
		} else {
			if (!this.getAboveMe().contains(window)) {
				window.register(this);
				this.getAboveMe().add(window);
				this.update(CalculationNeededEvent.create());
				this.notifyObservers(CalculationNeededEvent.create());
			}
		}
	}

	@Override
	public void update(Event e) {
		this.getState().handleNotification(e);
	}

	public void dispose() {
		this.deregisterAtAllAbove();
		System.out.println("Dispose: " + this);

	}

	public void newTop(Window window) {
		this.getAboveMe().add(window);
		window.register(this);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
