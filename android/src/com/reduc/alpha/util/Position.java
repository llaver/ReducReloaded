package com.reduc.alpha.util;

/**
 * Created by rbell on 7/14/2017.
 */
public class Position {
	
	private float x;
	private float y;
	
	public Position() {
		x = 0;
		y = 0;
	}
	
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Position) {
			return(x == ((Position) obj).getX() && y == ((Position) obj).getY());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
