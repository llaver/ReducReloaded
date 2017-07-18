package com.game.alpha.reduc.game.managers;

import com.game.alpha.reduc.game.entities.GameObject;
import com.game.alpha.reduc.util.RenderPriority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rbell on 7/14/2017.
 */
public class EntityManager {
	
	private static List<GameObject> entities = Collections.synchronizedList(new ArrayList());
	
	public static void addEntity(GameObject entity) {
		entities.add(entity);
	}
	
	public static int getEntityCount() {
		return entities.size();
	}
	
	/**
	 * Sorts by priority then draws
	 */
	public static void draw() {
		List<GameObject> sortedEntities = EntityManager.sortEntities();
		for(GameObject e : sortedEntities) {
			e.draw();
		}
	}
	
	public static void draw(RenderPriority priority) {
		for(GameObject entity : entities) {
			if(entity.getPriority() == priority) {
				entity.draw();
			}
		}
	}
	
	/**
	 * Sorts the list of entities by render priority.
	 * Lower priority will be first in the list due to being rendered first
	 *
	 * @return a List of entities sorted by render priority
	 */
	public static List<GameObject> sortEntities() {
		List<GameObject> sortedEntities = entities;
		Collections.sort(sortedEntities, new PriorityComparator());
		
		return sortedEntities;
	}
	
	static class PriorityComparator implements Comparator<GameObject> {
		@Override
		public int compare(GameObject a, GameObject b) {
			return a.getPriority().getValue() < b.getPriority().getValue()? -1 : a.getPriority().getValue() == b.getPriority().getValue() ? 0 : 1;
		}
	}
	
}


