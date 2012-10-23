package model;

public class Point {

	private int x;
	private int y;

	public Point(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public void move(int deltaX, int deltaY) {
		this.setX(this.getX() + deltaX);
		this.setY(this.getY() + deltaY);
	}

	public static boolean linksVon(Point p1, Point p2) {
		return p1.getX() < p2.getX();
	}

	public static boolean unter(Point p1, Point p2) {
		return p1.getY() < p2.getY();
	}

	/**
	 * Returns a Point which has the same x- and y-values as <this>.
	 * 
	 * @return : Point
	 */
	public Point copyPoint() {
		return new Point(this.getX(), this.getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "[X: " + this.getX() + ", Y: " + this.getY() + "]";
	}
}
