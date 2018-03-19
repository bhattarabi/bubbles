package plecs.games.bubbles;

public class Speed {
	
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_LEFT = -1;
	public static final int DIRECTION_UP = -1;
	public static final int DIRECTION_DOWN = 1;
	
	private double xV;
	private double yV;
	
	private int xDirection ;
	private int yDirection ;
	
	public Speed() {
		
		xV = 0;
		yV = 3;
		xDirection = 0;
		yDirection = 1;
	}
	
	public void reset() {

		xV = 0;
		yV = 3;
		xDirection = 0;
		yDirection = 1;
	}
	
	public void setXv(double d) {
		xV = d;
	}
	
	public double getXv() {
		return xV;
	}
	
	public void setYv(double d) {
		yV = d;
	}
	
	public double getYv() {
		return yV;
	}
	
	public int getXDirection() {
		return xDirection;
	}
	
	public void setXDirection(int d) {
		xDirection = d;
	}
	
	public int getYDirection() {
		return yDirection;
	}
	
	public void setYDirection(int d) {
		yDirection = d;
	}
	
	public void toggleXDirection() {
		xDirection *= -1;
	}
	
	public void toggleYDirection() {
		yDirection *= -1;
	}
}
