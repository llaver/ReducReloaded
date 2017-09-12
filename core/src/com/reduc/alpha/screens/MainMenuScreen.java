package com.reduc.alpha.screens;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.reduc.alpha.Assets;
import com.reduc.alpha.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.reduc.alpha.ReducReloaded;
import com.reduc.alpha.util.OpenSimplexNoise;
import com.reduc.alpha.util.Pathing;
import com.reduc.alpha.util.Position;

import java.util.List;

/**
 * Created by rbell on 7/28/2017.
 */
public class MainMenuScreen extends ScreenAdapter {

	private ReducReloaded game;
	OrthographicCamera guiCam;
	Rectangle settingsBounds;
	Rectangle playBounds;
	Rectangle highscoresBounds;
	Rectangle helpBounds;
	Vector3 touchPoint;
	
	Pathing pathing = new Pathing();
	
	OpenSimplexNoise noise = new OpenSimplexNoise();
	double[][] map = new double[44][44];
	
	Vector2[] path = null;
	Vector2[] road = null;
	CatmullRomSpline<Vector2> curvedPath = new CatmullRomSpline<>();
	
	long time = System.currentTimeMillis();
	
	ShapeRenderer shapeRenderer;
	
	public MainMenuScreen(ReducReloaded game) {
		this.game = game;
		
		
		//TODO Update these values to actually mean something
		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		settingsBounds = new Rectangle(0, 0, 64, 64);
		playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
		highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
		helpBounds = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
		touchPoint = new Vector3();
		
		shapeRenderer = new ShapeRenderer();
		
		path = pathing.generatePath(true, new Position());
		road = pathing.convertPath(path);
		
	}
	
	public void update () {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			/* PLAY GAME */
			if (playBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				game.setScreen(new GameScreen(game));
				return;
			}
			/* HIGH SCORES */
			if (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				//game.setScreen(new HighscoresScreen(game));
				return;
			}
			/* HELP SCREEN */
			if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				//game.setScreen(new HelpScreen(game));
				return;
			}
			/* SETTINGS */
			if (settingsBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				Settings.soundEnabled = !Settings.soundEnabled;
				if (Settings.soundEnabled) {
					//	Assets.music.play();
				}
				else {
					//	Assets.music.pause();
				}
				
			}
		}
	}
	
	public void draw () {
		Vector2 current = new Vector2(Gdx.graphics.getWidth() / 2.0f, 20);
		Vector2 previous;
		GL20 gl = Gdx.gl20;
		//gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);
		
		game.batcher.disableBlending();
		game.batcher.begin();
		//game.batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
		game.batcher.end();
		
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(1, 1, 0, 1);
		
		System.out.println(path.length);
		for(int i = 0; i < road.length; i++) {
			previous = current;
			if(road[i] != null) {
				current = new Vector2((current.x) + road[i].x * 10, (current.y) + road[i].y * 10);
				
				
				shapeRenderer.line(previous.x, previous.y, current.x, current.y);
			}
		}
		shapeRenderer.end();
		
		
		
		
		game.batcher.enableBlending();
		game.batcher.begin();
		//game.batcher.draw(Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
		//game.batcher.draw(Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
		//game.batcher.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
		game.batcher.end();
	}
	
	@Override
	public void render (float delta) {
		update();
		draw();
	}
	
	@Override
	public void pause () {
		Settings.save();
	}
	
	
}
