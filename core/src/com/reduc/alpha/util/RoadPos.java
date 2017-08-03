package com.reduc.alpha.util;

/**
 * Road Position Util Class
 * <p>
 * Created by Ryan Bell
 */
public class RoadPos {
	
	private Position bottomLeft;
	private Position bottomRight;
	private Position topLeft;
	private Position topRight;
	
	/**
	 * Create a new default RoadPos
	 */
	public RoadPos() {
		bottomLeft = new Position();
		bottomRight = new Position();
		topLeft = new Position();
		topRight = new Position();
	}
	
	/**
	 * Create a new RoadPos with individual x and y coordinates for all 4 corners
	 *
	 * @param blX Bottom Left X
	 * @param blY Bottom Left Y
	 * @param brX Bottom Right X
	 * @param brY Bottom Right Y
	 * @param tlX Top Left X
	 * @param tlY Top Left Y
	 * @param trX Top Right X
	 * @param trY Top Right Y
	 */
	public RoadPos(float blX, float blY, float brX, float brY, float tlX, float tlY, float trX, float trY) {
		bottomLeft = new Position(blX, blY);
		bottomRight = new Position(brX, brY);
		topLeft = new Position(tlX, tlY);
		topRight = new Position(tlX, tlY);
	}
	
	/**
	 * Create a new RoadPos with 4 corner Position objects
	 *
	 * @param bl Bottom Left
	 * @param br Bottom Right
	 * @param tl Top Left
	 * @param tr Top Right
	 */
	public RoadPos(Position bl, Position br, Position tl, Position tr) {
		bottomLeft = bl;
		bottomRight = br;
		topLeft = tl;
		topRight = tr;
	}
	
	/**
	 * Create a new RoadPos with an array of Position objects
	 * <p>
	 * Order should always be Bottom Left, Bottom Right, Top Left, Top Right
	 * This should mainly be used with the calculatePosition() method.
	 *
	 * @param position An array of position objects. Best obtained from calculatePosition().
	 */
	public RoadPos(Position[] position) {
		bottomLeft = position[0];
		bottomRight = position[1];
		topLeft = position[2];
		topRight = position[3];
	}
	
	/**
	 * Returns an array of Position objects of the next Road section
	 *
	 * @param previous The RoadPos object of the road section directly prior to the one being calculated
	 * @param endPoint The center point along the line that is the end of the road.
	 * @return An array of the four corner positions of the next road section
	 */
	public static Position[] calculatePosition(RoadPos previous, Position endPoint) {
		Position[] newPos = new Position[4];
		
		float xDiff = newPos[1].getX() - endPoint.getX();
		double rad = Math.toRadians(2.5);
		
		newPos[0] = previous.getTopLeft();
		newPos[1] = previous.getTopRight();
		newPos[2] = new Position((float) (xDiff * Math.cos(rad)), endPoint.getY());
		newPos[3] = new Position((float) (xDiff * Math.cos(-rad)), endPoint.getY());
		
		return newPos;
	}
	
	/**
	 * Compares two RoadPos objects
	 *
	 * @param obj The object to be compared
	 * @return True if all four corner positions match up.
	 */
	public boolean equals(Object obj) {
		return obj instanceof RoadPos
			&& (bottomLeft.equals(((RoadPos) obj).getBottomLeft())
			&& bottomRight.equals(((RoadPos) obj).getBottomRight())
			&& topLeft.equals(((RoadPos) obj).getTopLeft())
			&& topRight.equals(((RoadPos) obj).getTopRight()));
	}
	
	/**
	 * Lets 3D print some roads yo
	 *
	 * @return A string containing the coordinates of all four corner positions
	 */
	public String toString() {
		return "Bottom Left " + bottomLeft.toString() + " Bottom Right " + bottomRight.toString()
			+ " Top Left " + topLeft.toString() + " Top Right " + topRight.toString() + ".";
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
