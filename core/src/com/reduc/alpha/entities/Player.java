package com.reduc.alpha.entities;


import com.reduc.alpha.util.Position;
import com.reduc.alpha.util.RenderPriority;

/**
 * Created by rbell on 7/14/2017.
 */
public class Player extends GameObject {
	
	private boolean sliding = false;
	
	public Player() {
		super();
	}
	
	public Player(Position p, ObjectID id, float speed, float acceleration, float direction, float force, RenderPriority priority) {
		super(p, id, speed, acceleration, direction, force, priority);
	}
	
	public void jump() {
	
	}
	
	
	
	
	
	public boolean isSliding() {
		return sliding;
	}
	
	@Override
	public void update() {
	
	}
	
	@Override
	public void draw() {
	
	}
}
