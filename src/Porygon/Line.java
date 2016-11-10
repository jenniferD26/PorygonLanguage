package Porygon;

public class Line {
	Point x;
	Point y;
	public Line() {
		x = new Point();
		y = new Point();
	}
	public Line(int x, int y, int w, int z) {
		this.x = new Point(x, y);
		this.y = new Point(w, z);
	}
	public Line(Point a, Point b) {
		this.x = a;
		this.y = b;
	}
	public Point getX() {
		return x;
	}
	public void setX(Point x) {
		this.x = x;
	}
	public Point getY() {
		return y;
	}
	public void setY(Point y) {
		this.y = y;
	}
}