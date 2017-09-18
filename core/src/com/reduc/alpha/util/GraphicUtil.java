package com.reduc.alpha.util;


import com.badlogic.gdx.math.Vector2;

import java.awt.*;

/**
 * Created by rbell on 6/29/2017.
 */
public class GraphicUtil {
	public GraphicUtil() {
		System.out.println("yo");
	}

	public static Vector2 rotatePoint(Vector2 point, Vector2 center, float angle) {
		
		float distance = (float) Math.sqrt((center.x - point.x)*(center.x - point.x)+(center.y - point.y) * (center.y - point.y));
		
		float newX = (float) (point.x + Math.cos(angle) * distance);
		float newY = (float) (point.y + Math.sin(angle) * distance);
		
		float x1 = point.x - center.x;
		float y1 = point.y - center.y;
		
		float x2 = (float) (x1 * Math.cos(angle) - y1 * Math.sin(angle));
		float y2 = (float) (x1 * Math.sin(angle) + y1 * Math.cos(angle));
		
		point.x = x2 + center.x;
		point.y = y2 + center.y;
		
		System.out.println("old: " + point.x + " " + point.y + " new: " + newX + " " + newY);
		
		return new Vector2(newX, newY);
	
	}
	
}
