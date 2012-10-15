package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class Window extends RectangularArea {

	private static int nextWindowNumber = 0;

	private static final int InitialWidth = 200;
	private static final int InitialHeight = 100;
	private static final int Origin = 0;

	private static final Point getInitialPosition() {
		return new Point(Origin, Origin);
	}

	final int number;
	private boolean open;
	Collection<Window> aboveMe;

	public Window() {
		super(getInitialPosition(), InitialWidth, InitialHeight);
		this.number = nextWindowNumber++;
		this.setOpen(true);
		this.setAboveMe(new Vector<Window>());
	}

	private boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
		// TODO (2) possibly send event to all windows below
		System.out.println("SetOpen(" + open + "): " + this);
	}

	public void move(int deltaX, int deltaY) {
		super.move(deltaX, deltaY);
		// TODO (2) possibly send event to all windows below
		System.out.println("Move: " + this);
	}

	public void resize(int width, int height) throws NegativeLengthException {
		super.resize(width, height);
		// TODO (2) possibly send event to all windows below
		System.out.println("Resize: " + this);
	}

	public RectangularPart toPart() {
		return new RectangularPart(this.getLeftUpperCorner(), this.getWidth(),
				this.getHeight());
	}

	public RectangularPartCollection getVisibleContext() {
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
			int counter = 1;
			Window windowBeforeMe = aboveMe.get(aboveMe.size() - counter);
			counter++;
			while (!windowBeforeMe.isOpen() && counter <= aboveMe.size()) {
				aboveMe.get(aboveMe.size() - counter);
			}
			RectangularPartCollection beforeMe = windowBeforeMe
					.getVisibleContext();
			for (RectangularPart part : beforeMe.getParts()) {
				try {
					result.add(this.calculateVisibleContext(part));
				} catch (HierarchyException e) {
					throw new Error("Hierarchy shall be guaranteed!");
				}
			}
		}

		return result;
	}

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

	public Vector<Window> aboveMeToVector() {
		return (Vector<Window>) this.getAboveMe();
	}

	public RectangularPartCollection calculateVisibleParts(
			RectangularPartCollection before) throws HierarchyException {
		RectangularPartCollection result = new RectangularPartCollection();
		RectangularPart meAsPart = this.toPart();

		Iterator<RectangularPart> i = before.getParts().iterator();
		while (i.hasNext()) {
			RectangularPart current = i.next();
			if (current.overlaps(meAsPart)) {
				if (this.isSpaceEast(current)) {
					RectangularPart eastPart = this.calculateSpaceEast(current);
					eastPart.setParent(this);
					result.add(eastPart);
				}
				if (this.isSpaceWest(current)) {
					RectangularPart westPart = this.calculateSpaceWest(current);
					westPart.setParent(this);
					result.add(westPart);
				}
				if (this.isSpaceNorth(current)) {
					RectangularPart northPart = this
							.calculateSpaceNorth(current);
					northPart.setParent(this);
					result.add(northPart);
				}
				if (this.isSpaceSouth(current)) {
					RectangularPart southPart = this
							.calculateSpaceSouth(current);
					southPart.setParent(this);
					result.add(southPart);
				}
			} else {
				result.add(current);
			}
		}

		return result;
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

	public void setAsTop(Window window) {
		if (this.equals(window)) {
			this.getAboveMe().clear();
		} else {
			if (!this.getAboveMe().contains(window))
				this.getAboveMe().add(window);
		}
	}

	public void dispose() {
		// TODO (2) implement dispose: possibly send event to all windows below
		System.out.println("Dispose: " + this);

	}

	public void newTop(Window window) {
		this.getAboveMe().add(window);
	}
}
