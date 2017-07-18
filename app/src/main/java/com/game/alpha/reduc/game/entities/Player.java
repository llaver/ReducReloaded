package com.game.alpha.reduc.game.entities;

import com.game.alpha.reduc.util.Position;

/**
 * Created by rbell on 7/14/2017.
 */
public class Player extends GameObject {
	
	public Player() {
		super();
	}
	
	public Player(Position p, ObjectID id, float speed, float acceleration, float direction, float force) {
		super(p, id, speed, acceleration, direction, force);
	}
	
	public void jump() {
	
	}
	
	
	
	@Override
	public void draw() {
	
	}
}
