package com.reduc.alpha.entities;


import com.badlogic.gdx.math.Vector2;
import com.reduc.alpha.util.Position;
import com.reduc.alpha.util.RenderPriority;

/**
 * Created by rbell on 7/26/2017.
 */
public class Road {
	
	public Vector2[] path;
	private Vector2[] leftCurb;
	private Vector2[] rightCurb;
	private Vector2[][] road = new Vector2[3][];
	
	//TODO decide whether or not to keep rotation here. Probably would be better to use TransformComponent.
	public float rotation = 0.0f;
	
	public Road() {
		path = new Vector2[0];
		leftCurb = new Vector2[0];
		rightCurb = new Vector2[0];
	}
	
	public Road(Vector2[] path, Vector2[] leftCurb, Vector2[] rightCurb) {
		this.path = path;
		this.leftCurb = leftCurb;
		this.rightCurb = rightCurb;
	}
	
	/**
	 * Get a group of all three required road items.
	 * .get()[0] = center of road
	 * .get()[1] = left side of road
	 * .get()[2] = right side of road
	 *
	 * @return A double array of Vector2 objects
	 * Example: .get()[0][10] = center of road, 10 points in.
	 */
	public Vector2[][] getAll() {
		road[0] = path;
		road[1] = leftCurb;
		road[2] = rightCurb;
		return road;
	}
	
	public void setAll(Vector2[] path, Vector2[] leftCurb, Vector2[] rightCurb) {
		this.path = path;
		this.leftCurb = leftCurb;
		this.rightCurb = rightCurb;
	}
	
	public int getSize() {
		if(road[0] != null) {
			return road[0].length;
		} else {
			return 0;
		}
	}
}
