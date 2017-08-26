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
	
	private Position position; //Position of path
	
	private Random r;
	private OpenSimplexNoise noise;
	public static double[][] map;
	private double noiseLevel;
	private int sampleSize;
	
	private Map<RoadNode> roadMap;
	private List<RoadNode> path;
	
	public Pathing() {
		position = new Position(0,0);
		r = new Random();
		sampleSize = 45;
		map = new double[45][45];
		roadMap = new Map<RoadNode>(45, 45, new RoadNodeFactory());
		noiseLevel = .15;
	}
	
	/**
	 * Create a new pathing object for generating noise and paths
	 *
	 * @param position Starting/current position
	 * @param sampleSize Size of the width and height of the noise. 32 - 45 works best
	 * @param noiseLevel A value for manipulating the noise. Since the path is based on the noise,
	 *                   a higher value does not mean a curvier line. Best values are .05 - .25
	 */
	public Pathing(Position position, int sampleSize, double noiseLevel) {
		this.position = position;
		r = new Random();
		this.sampleSize = sampleSize;
		map = new double[sampleSize][sampleSize];
		roadMap = new Map<RoadNode>(sampleSize, sampleSize, new RoadNodeFactory());
		this.noiseLevel = noiseLevel;
		
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
				map[i][k] = noise.eval(i * noiseLevel, k * noiseLevel);
				//System.out.println("i10: " + i * 10 + " k10: " + k * 10 + " 1i10: " + (i + 1) * 10 + " 1k10: " + (k + 1) * 10);
				//System.out.println(map[i][k]);
			}
		}
		if(firstRun) {
			path = roadMap.findPath(0, 0, sampleSize - 1, r.nextInt(sampleSize - 1));
		} else {
			path = roadMap.findPath(0, (int) endOfPath.getY(), sampleSize - 1, r.nextInt(sampleSize - 1));
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
