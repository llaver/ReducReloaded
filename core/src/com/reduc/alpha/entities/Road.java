package com.reduc.alpha.entities;


import com.reduc.alpha.util.Position;
import com.reduc.alpha.util.RenderPriority;

/**
 * Created by rbell on 7/26/2017.
 */
public class Road extends GameObject {
	
	//Grade of road, length of section of road, and angle of turn (if any)
	private float grade = 0f;
	private float length = 0f;
	private float angle = 0f;
	
	
	
	public Road() {
		super();
	}
	
	public Road(Position p, ObjectID id, float speed, float acceleration, float direction, float force, RenderPriority priority) {
		super(p, id, speed, acceleration, direction, force, priority);
	}
	
	public void generate() {
	
	
	
	}
	
	
	
	/**
	 * Called by road manager (wherever the fuck thats gonna be) every time more road is needed (so like every 20 frames)
	 */
	public void extend() {
		//TODO Implement this shit
		
		
	}
	
	@Override
	public void update() {
		//TODO Make the road look like its moving.
		
		
	}
	
	@Override
	public void draw() {
	
	}
}
