package com.reduc.alpha;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.reduc.alpha.screens.MainMenuScreen;
import com.reduc.alpha.screens.TestScreen;

public class ReducReloaded extends Game {
	public SpriteBatch batcher;
	Texture img;
	
	@Override
	public void create () {
		batcher = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		//Settings.load();
		//Assets.load();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batcher.dispose();
		img.dispose();
	}
}
