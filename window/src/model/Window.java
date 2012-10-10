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

	Window() {
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
		// TODO (1) implement calculation of visible parts
		System.out.println("ich werd aufgerufen");
		RectangularPartCollection result = new RectangularPartCollection();
		if (this.isOpen()) {
			if (this.getAboveMe().isEmpty()) {
				RectangularPart meAsPart = new RectangularPart(
						this.getLeftUpperCorner(), this.getWidth(),
						this.getHeight());
				try {
					meAsPart.setParent(this);
				} catch (HierarchyException e) {
					throw new Error("Hierarchy shall be guaranteed!");
				}
				result.add(meAsPart);
			} else {
				Iterator<Window> i = this.getAboveMe().iterator();
				while (i.hasNext()) {
					Window current = i.next();
					RectangularPartCollection before = current
							.getVisibleContext();
					result.add(this.calculateVisibleParts(before));
				}
			}
		}
		return result;
	}

	private RectangularPartCollection calculateVisibleParts(
			RectangularPartCollection before) {
		RectangularPartCollection result = new RectangularPartCollection();
		RectangularPart meAsPart = this.toPart();

		Iterator<RectangularPart> i = before.getParts().iterator();
		while (i.hasNext()) {
			RectangularPart current = i.next();
			if (current.isPartOf(meAsPart)) {
				if (this.isSpaceEast(current)) {
					result.add(this.calculateSpaceEast(current));
				}
				if (this.isSpaceWest(current)) {
					result.add(this.calculateSpaceWest(current));
				}
				if (this.isSpaceNorth(current)) {
					result.add(this.calculateSpaceNorth(current));
				}
				if (this.isSpaceSouth(current)) {
					result.add(this.calculateSpaceSouth(current));
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
