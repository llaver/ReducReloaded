package com.reduc.alpha.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
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
	
	private Vector2 endOfPath;
	private boolean firstRun = true;
	
	private Map<RoadNode> roadMap;
	private List<RoadNode> path;
	
	public Pathing() {
		position = new Position(0, 0);
		r = new Random();
		sampleSize = 45;
		map = new double[45][45];
		roadMap = new Map<>(45, 45, new RoadNodeFactory());
		noiseLevel = .15;
	}
	
	/**
	 * Create a new pathing object for generating noise and paths
	 *
	 * @param position   Starting/current position
	 * @param sampleSize Size of the width and height of the noise. 32 - 45 works best
	 * @param noiseLevel A value for manipulating the noise. Since the path is based on the noise,
	 *                   a higher value does not mean a curvier line. Best values are .05 - .25
	 */
	public Pathing(Position position, int sampleSize, double noiseLevel) {
		this.position = position;
		r = new Random();
		this.sampleSize = sampleSize;
		map = new double[sampleSize][sampleSize];
		roadMap = new Map<>(sampleSize, sampleSize, new RoadNodeFactory());
		this.noiseLevel = noiseLevel;
		
	}
	
	public Pathing(Vector2 endOfPath) {
		this.endOfPath = endOfPath;
		firstRun = false;
	}
	
	public Pathing(Vector2[] path) {
		this.endOfPath = endOfPath;
		firstRun = false;
	}
	
	public Vector2[] generatePath() {
		//Generate Noise for "random" pathing generation
		noise = new OpenSimplexNoise(r.nextLong());
		if(firstRun) {
			//TODO Add Straight Section
			
		}
		
		//Create a Map based on noise.
		for(int i = 0; i < map.length; i++) {
			for(int k = 0; k < map[i].length; k++) {
				map[i][k] = noise.eval(i * noiseLevel, k * noiseLevel);
			}
		}
		if(firstRun) {
			path = roadMap.findPath(0, 0, sampleSize - 1, r.nextInt(sampleSize - 1));
		} else {
			path = roadMap.findPath(0, (int) endOfPath.y, sampleSize - 1, r.nextInt(sampleSize - 1));
		}
		
		Vector2[] dataSet = new Vector2[path.size()];
		for(int i = 0; i < path.size(); i++) {
			dataSet[i] = new Vector2(path.get(i).getxPosition(), path.get(i).getyPosition());
		}
		
		Vector2[] points = new Vector2[dataSet.length];
		CatmullRomSpline<Vector2> spline = new CatmullRomSpline<>(dataSet, false);
		for(int i = 0; i < points.length; i++) {
			points[i] = new Vector2();
			spline.valueAt(points[i], ((float) i) / ((float) points.length - 1));
		}
		return points;
	}
	
	public Vector2[] convertPath(Vector2[] path) {
		Vector2 current = null;
		Vector2 previous = null;
		Vector2[] updatedPath = new Vector2[path.length];
		for(int i = 0; i < path.length; i++) {
			previous = current;
			current = path[i];
			if(previous != null) {
				updatedPath[i] = new Vector2(current.x - previous.x, current.y - previous.y);
			}
		}
		return updatedPath;
	}
	
	public Vector2[] extendPath(Vector2[] path) {
		firstRun = false;
		endOfPath = new Vector2(path[path.length - 1].x, path[path.length - 1].y);
		Vector2[] generatedPath = convertPath(generatePath());
		Vector2[] newPath = new Vector2[path.length + generatedPath.length];
		int counter = 0;
		for(Vector2 v2 : path) {
			newPath[counter] = v2;
			counter++;
		}
		for(Vector2 v2 : generatedPath) {
			newPath[counter] = v2;
			counter++;
		}
		return newPath;
	}
	
	public Vector2[] convertToGridspace(Vector2[] path) {
		
		Vector2[] gridPath = new Vector2[path.length];
		gridPath[0] = path[0];
		for(int i = 1; i < path.length; i++) {
			if(path[i] != null && path[i - 1] != null) {
				Vector2 temp = new Vector2(path[i - 1].x + path[i].x, path[i - 1].y + path[i].y);
				gridPath[i] = temp;
			} else {
				gridPath[i] = new Vector2();
			}
		}
		return gridPath;
	}
	
	public Vector2[][] calculateBounds(Vector2[] path) {
		
		Vector2 current = new Vector2(Gdx.graphics.getWidth() / 2.0f, 20);
		Vector2 previous = new Vector2(Gdx.graphics.getWidth() / 2.0f, 20);
		
		Vector2[][] results = new Vector2[2][path.length - 1];
		
		for(int i = 0; i < path.length - 1; i++) {
		
			if(path[i] != null) {
				//Set needed vars
				float x1 = current.x;
				float y1 = current.y;
				float x2 = previous.x;
				float y2 = previous.y;
				float D = 30;
				
				//Calculate center point
				float dx = x1 - x2;
				float dy = y1 - y2;
				
				//Calculate normals (Voodoo)
				float dist = (float) Math.sqrt(dx * dx + dy * dy);
				dx /= dist;
				dy /= dist;
				float x3 = x1 + D * dy;
				float y3 = y1 - D * dx;
				float x4 = x1 - D * dy;
				float y4 = y1 + D * dx;
				
				results[0][i] = new Vector2(x3, y3);
				results[1][i] = new Vector2(x4, y4);
			} else {
				results[0][i] = new Vector2();
				results[1][i] = new Vector2();
			}
		}
		return results;
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
