package com.reduc.alpha.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by rbell on 8/1/2017.
 */
public class RoadComponent implements Component {
	
	public Vector2[] path;
	public Vector2[] leftCurb;
	public Vector2[] rightCurb;
	public Vector2[][] road = new Vector2[3][];
	
	//TODO decide whether or not to keep rotation here. Probably would be better to use TransformComponent.
	public float rotation = 0.0f;
	
	/**
	 * Get a group of all three required road items.
	 * .get()[0] = center of road
	 * .get()[1] = left side of road
	 * .get()[2] = right side of road
	 *
	 * @return A double array of Vector2 objects
	 * Example: .get()[0][10] = center of road, 10 points in.
	 */
	public Vector2[][] get() {
		road[0] = path;
		road[1] = leftCurb;
		road[2] = rightCurb;
		return road;
	}
	public void set(Vector2[] path, Vector2[] leftCurb, Vector2[] rightCurb) {
		this.path = path;
		this.leftCurb = leftCurb;
		this.rightCurb = rightCurb;
	}
}
