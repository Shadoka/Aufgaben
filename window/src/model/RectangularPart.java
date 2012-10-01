package model;

public class RectangularPart extends RectangularArea {

	RectangularArea parent;
	
	protected RectangularPart(Point position, int width, int height) {
		super(position, width, height);
	}
	public String toString(){
		return super.toString() + " { PARENT: " + this.getParent().toString() + " }";
	}
	private RectangularArea getParent() {
		return this.parent;
	}
	void setParent(RectangularArea parent) throws HierarchyException {
		//TODO (1) it shall be checked that the parent contains "this"
		if (parent.isIn(this)) throw new HierarchyException();
		this.parent = parent;
	}
	boolean isInTransitively(RectangularPart part) {
		if (this.getParent() == null) return false;
		return this.getParent().isInTransitively(part);
	}
}

@SuppressWarnings("serial")
class HierarchyException extends Exception {
	public HierarchyException() {
		super("No cycles!!!");
	}
}
