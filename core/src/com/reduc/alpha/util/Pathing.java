package com.reduc.alpha.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbell on 8/3/2017.
 */
public class Pathing {
	
	private float stepSize; //Distance between points
	private Position position; //Position of path
	private float angle; //Angle of turn
	private float deltaRange; //Change in range
	private float deltaAngle; //Change in angle
	
	public Pathing() {
		position = new Position(0,0);
		angle  = (float) (Math.random() * (Math.PI * 2));
		deltaRange = 0.01f;
		deltaAngle = 0;
		stepSize = 1f;
	}
	
	public Pathing(float posStep, float angle) {
		this.stepSize = posStep;
		this.angle = angle;
	}
	
	public List<Position> generatePath(boolean firstRun) {
		List<Position> line = new ArrayList<>();
		
		if(firstRun) {
			//TODO Add Straight Section
		}
		int count = 0;
		while(count < 5000) {
			float cor = (float) (deltaRange *Math.atan(15* deltaAngle)/Math.PI);
			float randNum = (float) (((Math.random() * 2) - 1) * deltaRange - cor);  //Random number from (-deltaRange, deltaRange)
			deltaAngle +=randNum;                       //We don't change the angle directly
			//but its differential - source of the smoothness!
			angle+= deltaAngle;                         //new angle is angle+deltaAngle
			
			position.setX((float) (position.getX() + (stepSize * Math.cos(angle))));
			position.setY((float) (position.getY() + (stepSize * Math.sin(angle))));
			
			line.add(position);
			count++;
		}
		return line;
	}
	
//	void update() {
//		float cor = dRange*atan(15*dAngle)/PI;
//		float randNum = (random(2)-1)*dRange-cor;  //Random number from (-dRange, dRange)
//		dAngle+=randNum;                       //We don't change the angle directly
//		//but its differential - source of the smoothness!
//
//		angle+=dAngle;                         //new angle is angle+dAngle
//
//		pos.x+=posStep*cos(angle);            //just move on step
//		pos.y+=posStep*sin(angle);
//	}
	
}
