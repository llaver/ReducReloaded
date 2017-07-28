package com.reduc.alpha.util;

/**
 * Enums for Render Priority
 *
 * The lower the value the later the object gets rendered (AKA the more on top)
 * Ex. 0 will alwauys
 *
 *
 */
public enum RenderPriority {
	
	INTERFACE(6),
	PLAYER(5),
	ENEMY(4),
	DEFAULT(3),
	PLATFORM(3),
	VFX(2),
	BACKGROUND_OBJ(1),
	BACKGROUND(0);
	
	
	private final int value;
	
	RenderPriority(int value) {
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}
}
