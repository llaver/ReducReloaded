package com.reduc.alpha.util;

/**
 * Created by rbell on 7/14/2017.
 */
public class RoadPos {
	
	private Position bottomLeft;
	private Position bottomRight;
	private Position topLeft;
	private Position topRight;
	
	public RoadPos() {
		bottomLeft = new Position();
		bottomRight = new Position();
		topLeft = new Position();
		topRight = new Position();
	}
	
	public RoadPos(float blX, float blY, float brX, float brY, float tlX, float tlY, float trX, float trY) {
		bottomLeft = new Position(blX, blY);
		bottomRight = new Position(brX, brY);
		topLeft = new Position(tlX, tlY);
		topRight = new Position(tlX, tlY);
	}
	
	public RoadPos(Position bl, Position br, Position tl, Position tr) {
		bottomLeft = bl;
		bottomRight = br;
		topLeft = tl;
		topRight = tr;
	}
	
	public Position[] calculatePosition(RoadPos rp, Position endPoint) {
		Position[] newPos = new Position[4];
		
		float xDiff = newPos[1].getX() - endPoint.getX();
		double rad = Math.toRadians(2.5);
		
		newPos[0] = rp.getTopLeft();
		newPos[1] = rp.getTopRight();
		newPos[2] = new Position((float) (xDiff * Math.cos(rad)), endPoint.getY());
		newPos[3] = new Position((float) (xDiff * Math.cos(-rad)), endPoint.getY());
		
		return newPos;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RoadPos) {
			return(bottomLeft.equals(((RoadPos) obj).getBottomLeft()) && bottomRight.equals(((RoadPos) obj).getBottomRight())
			&& topLeft.equals(((RoadPos) obj).getTopLeft()) && topRight.equals(((RoadPos) obj).getTopRight()));
		}
		return false;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public Position getBottomLeft() {
		return bottomLeft;
	}
	
	public void setBottomLeft(Position bottomLeft) {
		this.bottomLeft = bottomLeft;
	}
	
	public Position getBottomRight() {
		return bottomRight;
	}
	
	public void setBottomRight(Position bottomRight) {
		this.bottomRight = bottomRight;
	}
	
	public Position getTopLeft() {
		return topLeft;
	}
	
	public void setTopLeft(Position topLeft) {
		this.topLeft = topLeft;
	}
	
	public Position getTopRight() {
		return topRight;
	}
	
	public void setTopRight(Position topRight) {
		this.topRight = topRight;
	}
	
}
