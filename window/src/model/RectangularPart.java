package model;

public class RectangularPart extends RectangularArea {

	RectangularArea parent;

	public RectangularPart(Point position, int width, int height) {
		super(position, width, height);
	}

	public String toString() {
		return super.toString() + " { PARENT: " + this.getParent().toString()
				+ " }";
	}

	private RectangularArea getParent() {
		return this.parent;
	}

	void setParent(RectangularArea parent) throws HierarchyException {
		if (parent.isIn(this))
			throw new HierarchyException();

		if (this.overlaps(parent)) {
			this.parent = parent;
		} else {
			try {
				throw new PartMemberException();
			} catch (PartMemberException e) {
				throw new Error("Is not member of part.");
			}
		}
	}

	boolean isInTransitively(RectangularPart part) {
		if (this.getParent() == null)
			return false;
		return this.getParent().isInTransitively(part);
	}
}

@SuppressWarnings("serial")
class HierarchyException extends Exception {
	public HierarchyException() {
		super("No cycles!!!");
	}
}
