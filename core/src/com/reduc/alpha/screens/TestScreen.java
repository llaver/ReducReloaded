package com.reduc.alpha.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.reduc.alpha.ReducReloaded;

public class TestScreen extends ScreenAdapter {
	
	ReducReloaded game;
	
	public TestScreen(ReducReloaded game) {
		this.game = game;
	}
	
	public void update(float delta) {
	
	}
	
	public void draw() {
	
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		draw();
	}
	
	@Override
	public void pause() {
		pauseComponents();
	}
	
	private void pauseComponents() {
	
	}
}
