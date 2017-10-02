package com.reduc.alpha;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.reduc.alpha.util.OpenSimplexNoise;
import com.reduc.alpha.util.Pathing;
import com.reduc.alpha.util.Position;
import com.reduc.alpha.util.astar.RoadNode;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rbell on 8/3/2017.
 */
public class Test {
	public static void main(String[] args) {
		
		Pathing pathing = new Pathing();
		
		OpenSimplexNoise noise = new OpenSimplexNoise();
		double[][] map = new double[44][44];
		
		Vector2[] path = null;
		
		long time = System.currentTimeMillis();
		for(int l = 0; l < 5; l++) {
			List<Vector2> road = new ArrayList<>();
			if(l == 0) {
				path = pathing.generatePath(true, new Vector2());
			}
//			else {
//				int x = (int) path[path.size() - 1].getX();
//				int y = (int) path.get(path.size() - 1).getY();
//				path = pathing.generatePath(false, new Position(path.get(path.size() - 1).getX(), path.get(path.size() - 1).getX()));
//			}
//
//			for(Position p : path) {
//				road.add(new Vector2(p.getX(), p.getY()));
//			}
//			for(int i = 1; i < road.size(); i++) {
//				List<Vector2> tempList = new ArrayList<>();
//				tempList.add(road.get(i - 1).interpolate(road.get(i), .5f, Interpolation.circleOut));
//				road = tempList;
//				System.out.println()
//			}
//			for(int i = map.length - 1; i >= 0; i--) {
//				for(int k = map[i].length - 1; k >= 0; k--) {
//
//					float current = (float) map[i][k];
//					//System.out.println(current / 100);
//					byte col = (byte) (256 * map[i][k]);
//					//g.setColor(new Color(col, col, col));
//					//g.fillRect(i * 10, k * 10, (i + 1) * 10, (k + 1) * 10);
//
//					if(current >= -.2 && current <= .2 && !road.contains(new Vector2(i, k))) {
//						//g.setColor(Color.darkGray);
//						//g.fillRect(i, k, i, k);
//						//g.fillRect(i * 10, k * 10, (i + 1) * 10, (k + 1) * 10);
//						System.out.print(" ");
//					} else if(road.contains(new Vector2(i, k))) {
//						//g.setColor(Color.green);
//						//g.fillRect(i, k, i,k);
//						//g.fillRect(i * 10, k * 10, (i + 1) * 10, (k + 1) * 10);
//						System.out.print("X");
//						//System.out.println("X: " + i + " Y: " + k);
//					} else {
//						//g.setColor(Color.black);
//						//g.fillRect(i, k, i, k);
//						//g.fillRect(i * 10, k * 10, (i + 1) * 10, (k + 1) * 10);
//						System.out.print("@");
//					}
//				}
//				System.out.println("");
//			}
			
//			for(int i = 0; i < path.size(); i++) {
//				System.out.println("Delta X: " + path.get(i).getX() + " Delta Y: " + path.get(i).getY());
//
//			}
//
		}
		long end = System.currentTimeMillis();
		System.out.println("Took " + (end - time) + " ms");
	}
}
