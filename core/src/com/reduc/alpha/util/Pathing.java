package com.reduc.alpha.util;

import com.reduc.alpha.util.astar.Map;
import com.reduc.alpha.util.astar.RoadNode;
import com.reduc.alpha.util.astar.RoadNodeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rbell on 8/3/2017.
 */
public class Pathing {
	
	private float stepSize; //Distance between points
	private Position position; //Position of path
	
	private Random r;
	private OpenSimplexNoise noise;
	public static double[][] map;
	
	private Map<RoadNode> roadMap;
	private List<RoadNode> path;
	
	public Pathing() {
		position = new Position(0,0);
		stepSize = 1f;
		r = new Random();
		map = new double[64][64];
		roadMap = new Map<RoadNode>(64, 64, new RoadNodeFactory());
	}
	
	public Pathing(float posStep, float angle) {
		this.stepSize = posStep;
	}
	
	public List<RoadNode> generatePath(boolean firstRun, Position endOfPath) {
		noise = new OpenSimplexNoise(r.nextLong());
		List<Position> line = new ArrayList<>();
		if(firstRun) {
			//TODO Add Straight Section
			
		}
		
		for(int i = 0; i < map.length; i++) {
			//System.out.print("\n*");
			for(int k = 0; k < map[i].length; k++) {
				map[i][k] = noise.eval(i * .15, k * .15);
				//System.out.println("i10: " + i * 10 + " k10: " + k * 10 + " 1i10: " + (i + 1) * 10 + " 1k10: " + (k + 1) * 10);
				//System.out.println(map[i][k]);
			}
		}
		if(firstRun) {
			path = roadMap.findPath(0, 0, 63, r.nextInt(63));
		} else {
			path = roadMap.findPath(0, (int) endOfPath.getY(), 63, r.nextInt(63));
		}
		//roadMap.drawMap(path);
		
		//TODO Convert to good format for the game
		//Get Screen Width
		//Scale Path Width to Screen Width
		//Scale Y somehow?
		//????????????
		//Profit
		//TODO figure out ??????????
		
		return path;
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
