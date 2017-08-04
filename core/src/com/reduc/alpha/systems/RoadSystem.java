package com.reduc.alpha.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.reduc.alpha.ReducReloaded;
import com.reduc.alpha.components.RoadComponent;

/**
 * Created by rbell on 8/1/2017.
 */
public class RoadSystem extends IteratingSystem {
	
	//PooledEngine engine = GameScreen.engine;
	
	Family roadFamily;
	ImmutableArray<Entity> roads;
	private ComponentMapper<RoadComponent> rm;
	
	public RoadSystem() {
		super(Family.all(RoadComponent.class).get());
		
		rm = ComponentMapper.getFor(RoadComponent.class);
		roadFamily = Family.all(RoadComponent.class).get();
		//roads = engine.getEntitiesFor(roadFamily);
	}
	
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		int index = roads.indexOf(entity, true);
		
		RoadComponent road = rm.get(entity);
		Entity previousRoad = index > 0 ? roads.get(index - 1) : null;
		
		
		
		
	}
}
