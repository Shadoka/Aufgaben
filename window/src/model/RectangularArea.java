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

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		this.height = height;
	}

	public Point getLeftUpperCorner() {
		return leftUpperCorner;
	}

	private void setLeftUpperCorner(Point leftUpperCorner) {
		this.leftUpperCorner = leftUpperCorner;
	}

	public int getWidth() {
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
		// if (this.getLeftUpperCorner().getY() < parent.getLeftUpperCorner()
		// .getY()) {
		// return haveSameSpace(this, parent);
		// } else {
		// return haveSameSpace(parent, this);
		// }

		return Point.weiterDraußen(this.getLeftUpperCorner(),
				parent.getLeftUpperCorner())
				& this.getLeftUpperCorner().getY() + this.getHeight() <= parent
						.getLeftUpperCorner().getY() + parent.getHeight()
				& this.getLeftUpperCorner().getX() + this.getWidth() <= parent
						.getLeftUpperCorner().getX() + parent.getWidth();
	}

	private boolean haveSameSpace(RectangularArea area1, RectangularArea area2) {
		Point newCornerThis = new Point(area1.getLeftUpperCorner().getX(), 0);
		Point newCornerParent = new Point(area2.getLeftUpperCorner().getX(),
				area2.getLeftUpperCorner().getY()
						- area1.getLeftUpperCorner().getY());
		if (newCornerThis.getX() < newCornerParent.getX()) {
			newCornerParent.setX(newCornerParent.getX() - newCornerThis.getX());
			newCornerThis.setX(0);
			return newCornerThis.getY() + area1.getHeight() > newCornerParent
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

	public boolean isSpaceWest(RectangularArea r) {
		return this.leftUpperCorner.getX() < r.leftUpperCorner.getX();
	}

	public boolean isSpaceEast(RectangularArea r) {
		return (this.leftUpperCorner.getX() + this.width) > (r.leftUpperCorner
				.getX() + r.width);
	}

	public boolean isSpaceNorth(RectangularArea r) {
		return this.leftUpperCorner.getY() < r.leftUpperCorner.getY();
	}

	public boolean isSpaceSouth(RectangularArea r) {
		return (this.leftUpperCorner.getY() + this.height) > (r.leftUpperCorner
				.getX() + r.height);
	}

	/**
	 * Returns a RecangularArea of the western space between the RectangularArea
	 * <this> and <r>. It must be sure, that <this> has a lower x-value of the
	 * upper left corner than <r>.
	 * 
	 * @param r
	 * @return
	 */
	public RectangularPart calculateSpaceWest(RectangularArea r) {
		Point point = this.getLeftUpperCorner();
		int height = this.height;
		int width = r.getLeftUpperCorner().getX()
				- this.getLeftUpperCorner().getX();
		return new RectangularPart(point, width, height);
	}

	/**
	 * Returns a RecangularArea of the eastern space between the RectangularArea
	 * <this> and <r>. It must be sure, that <this> has a greater maximum
	 * x-value than <r>.
	 * 
	 * @param r
	 * @return
	 */
	public RectangularPart calculateSpaceEast(RectangularArea r) {
		Point point = new Point(r.getLeftUpperCorner().getX() + r.getWidth(),
				this.getLeftUpperCorner().getY());
		int height = this.getHeight();
		int width = this.getLeftUpperCorner().getX() + this.getWidth()
				- (r.getLeftUpperCorner().getX() + r.getWidth());
		return new RectangularPart(point, width, height);
	}

	/**
	 * Returns a RecangularArea of the northern space between the
	 * RectangularArea <this> and <r>. It must be sure, that <this> has a lower
	 * y-value of the upper left corner than <r>.
	 * 
	 * @param r
	 * @return
	 */
	public RectangularPart calculateSpaceNorth(RectangularArea r) {
		Point point = this.getLeftUpperCorner();
		if (point.getX() < r.getLeftUpperCorner().getX()) {
			point.setX(r.getLeftUpperCorner().getX());
		}

		int height = r.getLeftUpperCorner().getY()
				- this.getLeftUpperCorner().getY();
		int width;
		if (r.getWidth() >= this.getWidth()
				&& r.getLeftUpperCorner().getX() <= this.getLeftUpperCorner()
						.getX()
				&& (r.getLeftUpperCorner().getX() + r.getWidth()) >= (this
						.getLeftUpperCorner().getX() + this.getWidth())) {
			width = this.getWidth();
		} else {
			width = r.getWidth();
		}
		return new RectangularPart(point, width, height);
	}

	/**
	 * Returns a RecangularArea of the southern space between the
	 * RectangularArea <this> and <r>. It must be sure, that <this> has a
	 * greater maximum y-value than <r>.
	 * 
	 * @param r
	 * @return
	 */
	public RectangularPart calculateSpaceSouth(RectangularArea r) {
		Point point = new Point(r.getLeftUpperCorner().getX(), r
				.getLeftUpperCorner().getY() + r.getHeight());
		if (point.getX() < this.getLeftUpperCorner().getX()) {
			point.setX(this.getLeftUpperCorner().getX());
		}
		int height = this.getLeftUpperCorner().getY() + this.getHeight()
				- (r.getLeftUpperCorner().getY() + r.getHeight());
		int width;
		if (r.getWidth() >= this.getWidth()
				&& r.getLeftUpperCorner().getX() <= this.getLeftUpperCorner()
						.getX()
				&& (r.getLeftUpperCorner().getX() + r.getWidth()) >= (this
						.getLeftUpperCorner().getX() + this.getWidth())) {
			width = this.getWidth();
		} else {
			width = r.getWidth();
		}
		return new RectangularPart(point, width, height);
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
