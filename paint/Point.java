package ca.utoronto.utm.paint;

public class Point implements Cloneable  {
	int x, y;
	Point(int x, int y){
		this.x=x; this.y=y;
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
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
