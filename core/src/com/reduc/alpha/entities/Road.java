package com.reduc.alpha.entities;

import com.badlogic.gdx.math.Vector2;
import com.reduc.alpha.util.Position;
import com.reduc.alpha.util.RenderPriority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rbell on 7/26/2017.
 */
public class Road {
	
	public ArrayList<Vector2> path;
	private ArrayList<Vector2> leftCurb;
	private ArrayList<Vector2> rightCurb;
	private ArrayList<Vector2>[] road = new ArrayList[3];
	
	
	//TODO decide whether or not to keep rotation here. Probably would be better to use TransformComponent.
	public float rotation = 0.0f;
	
	public Road() {
		path = new ArrayList<Vector2>();
		leftCurb = new ArrayList<Vector2>();
		rightCurb = new ArrayList<Vector2>();
	}
	
	public Road(ArrayList<Vector2> path, ArrayList<Vector2> leftCurb, ArrayList<Vector2> rightCurb) {
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
	public ArrayList<Vector2>[] getAll() {
		road[0] = path;
		road[1] = leftCurb;
		road[2] = rightCurb;
		return road;
	}
	
	public void setAll(ArrayList<Vector2> path, ArrayList<Vector2> leftCurb, ArrayList<Vector2> rightCurb) {
		this.path = path;
		this.leftCurb = leftCurb;
		this.rightCurb = rightCurb;
	}
	
	public int getSize() {
		if(road[0] != null) {
			return road[0].size();
		} else {
			return 0;
		}
	}
}
