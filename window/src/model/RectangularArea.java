package model;

abstract public class RectangularArea {

	private Point leftUpperCorner;
	private int width;
	private int height;

	protected RectangularArea(Point position, int width, int height) {
		this.setLeftUpperCorner(position);
		this.setWidth(width < 0 ? 0 : width);
		this.setHeight(height < 0 ? 0 : height);
	}

	int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		this.height = height;
	}

	Point getLeftUpperCorner() {
		return leftUpperCorner;
	}

	private void setLeftUpperCorner(Point leftUpperCorner) {
		this.leftUpperCorner = leftUpperCorner;
	}

	int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		this.width = width;
	}

	public void move(int deltaX, int deltaY) {
		this.getLeftUpperCorner().move(deltaX, deltaY);
	}

	public void resize(int width, int height) throws NegativeLengthException {
		if (width <= 0)
			throw new NegativeLengthException();
		if (height <= 0)
			throw new NegativeLengthException();
		this.setHeight(height);
		this.setWidth(width);
	}

	/**
	 * Method provides true, if the two RectangularAreas are overlapping.
	 * Provides also true, when just the edges are overlapping.
	 * 
	 * @param parent
	 *            : RectangularArea
	 * @return : boolean
	 */
	public boolean isPartOf(RectangularArea parent) {
		if (this.getLeftUpperCorner().getY() < parent.getLeftUpperCorner()
				.getY()) {
			return haveSameSpace(this, parent);
		} else {
			return haveSameSpace(parent, this);
		}
	}

	private boolean haveSameSpace(RectangularArea area1, RectangularArea area2) {
		Point newCornerThis = new Point(area1.getLeftUpperCorner().getX(), 0);
		Point newCornerParent = new Point(area2.getLeftUpperCorner().getX(),
				area2.getLeftUpperCorner().getY()
						- area1.getLeftUpperCorner().getY());
		if (newCornerThis.getX() < newCornerParent.getX()) {
			newCornerParent.setX(newCornerParent.getX() - newCornerThis.getX());
			newCornerThis.setX(0);
			return newCornerThis.getY() + area1.getHeight() >= newCornerParent
					.getY()
					|| newCornerThis.getX() + area1.getWidth() >= newCornerParent
							.getX();
		} else {
			newCornerThis.setX(newCornerThis.getX() - newCornerParent.getX());
			newCornerParent.setX(0);
			return newCornerThis.getY() + area1.getHeight() >= newCornerParent
					.getY()
					|| newCornerParent.getX() + area2.getWidth() >= newCornerThis
							.getX();
		}
	}

	public String toString() {
		return "(WIDTH: " + this.getWidth() + ", HEIGHT: " + this.getHeight()
				+ ") at " + this.getLeftUpperCorner();
	}

	public boolean isIn(RectangularPart part) {
		if (this.equals(part))
			return true;
		return this.isInTransitively(part);
	}

	abstract boolean isInTransitively(RectangularPart part);
}
