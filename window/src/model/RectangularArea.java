package model;

abstract public class RectangularArea {
	
	private Point leftUpperCorner;
	private int width;
	private int height;
	
	protected RectangularArea(Point position, int width, int height) {
		this.setLeftUpperCorner(position);
		this.setWidth(width < 0 ? 0 :width);
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
	public void move(int deltaX, int deltaY){
		this.getLeftUpperCorner().move(deltaX, deltaY);
	}
	public void resize(int width, int height) throws NegativeLengthException{
		if (width <= 0) throw new NegativeLengthException();
		if (height <= 0) throw new NegativeLengthException();
		this.setHeight(height);
		this.setWidth(width);
	}
	public String toString(){
		return  "(WIDTH: " + this.getWidth() + ", HEIGHT: " + this.getHeight() + ") at " + this.getLeftUpperCorner();
	}
	public boolean isIn(RectangularPart part) {
		if (this.equals(part)) return true;
		return this.isInTransitively(part);
	}
	abstract boolean isInTransitively(RectangularPart part);
}
