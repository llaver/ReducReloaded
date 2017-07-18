package com.game.alpha.reduc.game.entities;

import com.game.alpha.reduc.util.Position;
import com.game.alpha.reduc.util.RenderPriority;

/**
 * Created by rbell on 7/14/2017.
 */
public abstract class GameObject {
	
	private Position p;
	private ObjectID id;
	private float speed;
	private float acceleration;
	private float direction;
	private float force;
	private RenderPriority priority;
	
	public GameObject() {
		p = new Position();
		id = ObjectID.GAME_OBJECT;
		speed = 0;
		acceleration = 0;
		direction = 0;
		force = 0;
		priority = RenderPriority.DEFAULT;
	}
	
	public GameObject(Position p, ObjectID id, float speed, float acceleration, float direction, float force, RenderPriority priority) {
		this.p = p;
		this.id = id;
		this.speed = speed;
		this.acceleration = acceleration;
		this.direction = direction;
		this.force = force;
		this.priority = priority;
	}
	
	public abstract void draw();
	
	public Position getPosition() {
		return p;
	}
	
	public void setPosition(Position p) {
		this.p = p;
	}
	
	public ObjectID getId() {
		return id;
	}
	
	public void setId(ObjectID id) {
		this.id = id;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getAcceleration() {
		return acceleration;
	}
	
	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}
	
	public float getDirection() {
		return direction;
	}
	
	public void setDirection(float direction) {
		this.direction = direction;
	}
	
	public float getForce() {
		return force;
	}
	
	public void setForce(float force) {
		this.force = force;
	}
	
	public RenderPriority getPriority() {
		return priority;
	}
	
	public void setPriority(RenderPriority priority) {
		this.priority = priority;
	}
}
