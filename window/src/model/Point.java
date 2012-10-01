package model;

public class Point {
	
	private int x;
	private int y;
	
	Point(int x, int y){
		this.setX(x);
		this.setY(y);
	}
	public void move(int deltaX, int deltaY){
		this.setX(this.getX() + deltaX);
		this.setY(this.getY() + deltaY);
	}
	private int getX() {
		return x;
	}
	private void setX(int x) {
		this.x = x;
	}
	private int getY() {
		return y;
	}
	private void setY(int y) {
		this.y = y;
	}
	public String toString(){
		return "[X: " + this.getX() + ", Y: " + this.getY() + "]";
	}
}
