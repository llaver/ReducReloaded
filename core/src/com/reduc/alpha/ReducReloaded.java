package com.reduc.alpha;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ReducReloaded extends Game {
	public SpriteBatch batcher;
	Texture img;
	
	@Override
	public void create () {
		batcher = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		Settings.load();
		//Assets.load();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		batcher.draw(img, 0, 0);
		batcher.end();
	}
	
	@Override
	public void dispose () {
		batcher.dispose();
		img.dispose();
	}
}
