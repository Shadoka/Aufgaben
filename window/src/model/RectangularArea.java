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
	 * Returns true if and only if this overlaps part or part overlaps this.
	 */
	public boolean overlaps(RectangularArea part) {
		RectangularArea aroundRect = this.getBoundingRectangle(part);

		boolean overlapsHorizontal = aroundRect.getWidth() < this.getWidth()
				+ part.getWidth();
		boolean overlapsVertical = aroundRect.getHeight() < this.getHeight()
				+ part.getHeight();

		return overlapsHorizontal && overlapsVertical;
	}

	/**
	 * Returns the smallest RectangularPart that contains this and part.
	 */
	private RectangularArea getBoundingRectangle(RectangularArea part) {
		return new RectangularPart(
				this.getPointOfBoundingRectangularPart(part),
				this.getWidthOfBoundingRectangularPart(part),
				this.getHeightOfBoundingRectangularPart(part));
	}

	/**
	 * Returns the upperLeftCorner as a Point of the smallest RectangularPart
	 * that contains this and part.
	 */
	private Point getPointOfBoundingRectangularPart(RectangularArea part) {
		return new Point((this.getLeftUpperCorner().getX() < part
				.getLeftUpperCorner().getX()) ? this.getLeftUpperCorner()
				.getX() : part.getLeftUpperCorner().getX(),
				(this.getLeftUpperCorner().getY() < part.getLeftUpperCorner()
						.getY()) ? this.getLeftUpperCorner().getY() : part
						.getLeftUpperCorner().getY());
	}

	/**
	 * Returns the width as an int of the smallest RectangularPart that contains
	 * this and part.
	 */
	private int getWidthOfBoundingRectangularPart(RectangularArea part) {
		if (this.getLeftUpperCorner().getX() < part.getLeftUpperCorner().getX())
			return part.getLeftUpperCorner().getX() + part.getWidth()
					- this.getLeftUpperCorner().getX();
		else
			return this.getLeftUpperCorner().getX() + this.getWidth()
					- part.getLeftUpperCorner().getX();
	}

	/**
	 * Returns the height as an int of the smallest RectangularPart that
	 * contains this and part.
	 */
	private int getHeightOfBoundingRectangularPart(RectangularArea part) {
		if (this.getLeftUpperCorner().getY() < part.getLeftUpperCorner().getY())
			return part.getLeftUpperCorner().getY() + part.getHeight()
					- this.getLeftUpperCorner().getY();
		else
			return this.getLeftUpperCorner().getY() + this.getHeight()
					- part.getLeftUpperCorner().getY();
	}

	/**
	 * Returns true, if there is visible space between the left border of <this>
	 * and the left border of <r>.
	 * 
	 * @param r
	 *            : RectangularArea
	 * @return : boolean
	 */
	public boolean isSpaceWest(RectangularArea r) {
		return this.leftUpperCorner.getX() < r.leftUpperCorner.getX();
	}

	/**
	 * Returns true, if there is visible space between the right border of
	 * <this> and <r>.
	 * 
	 * @param r
	 *            : RectangularArea
	 * @return : boolean
	 */
	public boolean isSpaceEast(RectangularArea r) {
		return (this.leftUpperCorner.getX() + this.width) > (r.leftUpperCorner
				.getX() + r.width);
	}

	/**
	 * Returns true, if there is visible space between the upper border of
	 * <this> and the upper border of <r>.
	 * 
	 * @param r
	 *            : RectangularArea
	 * @return : boolean
	 */
	public boolean isSpaceNorth(RectangularArea r) {
		return this.leftUpperCorner.getY() < r.leftUpperCorner.getY();
	}

	/**
	 * Returns true, if there is visible space between the lower border of
	 * <this> and the lower border of <r>.
	 * 
	 * @param r
	 *            : RectangularArea
	 * @return : boolean
	 */
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
		Point point = this.getLeftUpperCorner().copyPoint();
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
		Point point = this.getLeftUpperCorner().copyPoint();
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
