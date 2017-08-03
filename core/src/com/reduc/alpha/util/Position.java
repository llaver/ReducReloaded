package com.reduc.alpha.util;

/**
 * Position class
 *
 * Created by rbell on 7/14/2017.
 */
public class Position {
	
	private float x;
	private float y;
	
	/**
	 * Create a default Position
	 */
	public Position() {
		x = 0;
		y = 0;
	}
	
	/**
	 * Create a Position with the specified coordinates
	 *
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Compares the coordinates of two position objects
	 *
	 * @param obj the object to be compared to
	 * @return True if the coordinates are the same
	 */
	public boolean equals(Object obj) {
		return obj instanceof Position && (x == ((Position) obj).getX() && y == ((Position) obj).getY());
	}
	
	@Override
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
}
