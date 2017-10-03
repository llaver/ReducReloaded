package com.reduc.alpha.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.reduc.alpha.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.reduc.alpha.ReducReloaded;
import com.reduc.alpha.entities.Road;
import com.reduc.alpha.util.Pathing;
import com.reduc.alpha.util.threads.CalculationsThread;

/**
 * Created by rbell on 7/28/2017.
 */
public class MainMenuScreen extends ScreenAdapter {

	private ReducReloaded game;
	private OrthographicCamera guiCam;
	private Rectangle settingsBounds;
	private Rectangle playBounds;
	private Rectangle highscoresBounds;
	private Rectangle helpBounds;
	private Vector3 touchPoint;
	
	public final Road road = new Road();
	
	int count = 0;
	
	long time = System.currentTimeMillis();
	
	ShapeRenderer shapeRenderer;
	
	private CalculationsThread calcThread;
	
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
		
		calcThread = new CalculationsThread(this);
		calcThread.start();
	}
	
	public void update (float delta) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			/* PLAY GAME */
			if (playBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				//game.setScreen(new GameScreen(game));
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
	
	public Color getColor(int size, int index) {
		int divisionSize = 4000 / size;
		int value = divisionSize * index;
		int red = 0;
		int green = 0;
		int blue = 0;
		switch(value / 255) {
			case 0:
				red = 255;
				blue = value;
				break;
			case 1:
				red = 255 - value % 255;
				blue = 255;
				break;
			case 2:
				green = value % 255;
				blue = 255;
				break;
			case 3:
				green = 255;
				blue = 255 - value % 255;
				break;
			case 4:
				red = value % 255;
				green = 255;
				break;
			default:
				red = 255;
				green = 255;
		}
		return new Color((float) red / 255, (float) green / 255, (float) blue / 255, 1);
	}
	
	public void draw () {
		Vector2 current = new Vector2(Gdx.graphics.getWidth() / 2.0f, 20);
		Vector2 previous = new Vector2(Gdx.graphics.getWidth() / 2.0f, 20);
		Vector2 old = new Vector2(Gdx.graphics.getWidth() / 2.0f, 20);
		GL20 gl = Gdx.gl20;
		//gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);
		
		game.batcher.disableBlending();
		game.batcher.begin();
		//game.batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
		game.batcher.end();
		
		//Gdx.gl.glLineWidth(32); // Or whatever thickness you need
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(1, 1, 0, 1);
		shapeRenderer.setColor(new Color());
		
//		Vector2[] gridSpace = pathing.convertToGridspace(roadPath);
//		Vector2[] normals = new Vector2[roadPath.length];
//
//		for(int i = 1; i < gridSpace.length; i++) {
//			if(gridSpace[i] != null && gridSpace[i - 1] != null) {
//				float dx = gridSpace[i].x - gridSpace[i - 1].x;
//				float dy = gridSpace[i].y - gridSpace[i - 1].y;
//				System.out.println(dx + "  " + dy);
//
//				normals[i] = new Vector2(dx, dy);
//			} else {
//				normals[i] = new Vector2(0, 0);
//			}
//		}
		
		Vector2[][] roadPath = road.getAll();
		Vector2[] center = roadPath[0];
		Vector2[] leftCurb = roadPath[1];
		Vector2[] rightCurb = roadPath[2];
		
		for(int i = 0; i < center.length - 1; i++) {
			shapeRenderer.setColor(getColor(roadPath.length, i));
			old = previous;
			previous = current;
			if(center[i] != null && center[i + 1] != null) {
				current = new Vector2((current.x) + center[i].x * 50, (current.y) + center[i].y * 50);
				
				shapeRenderer.line(previous.x, previous.y, current.x, current.y);
				shapeRenderer.setColor(1, 1, 0, 1);
				
				shapeRenderer.line(leftCurb[i].x, leftCurb[i].y, leftCurb[i + 1].x, leftCurb[i + 1].y);
				shapeRenderer.line(rightCurb[i].x, rightCurb[i].y, rightCurb[i + 1].x, rightCurb[i + 1].y);
				
			}
		}
//		for(int i = 0; i < normals.length; i++) {
//			if(normals[i] != null && normals[i - 1] != null) {
//				shapeRenderer.setColor(1, 1, 0, 1);
//				shapeRenderer.line(normals[i].x, normals[i].y, normals[i - 1].x, normals[i - 1].y);
//				System.out.println("THIS SHOULD BE DOING SOMETHING");
//			}
//		}
		shapeRenderer.end();
		
		if(count == 10) {
			center = cyclePath(center);
			leftCurb = cyclePath(leftCurb);
			rightCurb = cyclePath(rightCurb);
			count = 0;
		}
		
		count++;
		
		game.batcher.enableBlending();
		game.batcher.begin();
		//game.batcher.draw(Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
		//game.batcher.draw(Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
		//game.batcher.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
		game.batcher.end();
	}
	
	private Vector2[] cyclePath(Vector2[] current) {
		if(current.length > 0) {
			Vector2[] newPath = new Vector2[current.length - 1];
			for(int i = 1; i < current.length; i++) {
				if(current[i] != null) {
					newPath[i - 1] = current[i];
				} else {
					newPath[i - 1] = new Vector2(0, 0);
				}
			}

//		//System.out.println(angle);
//		float angle = newPath[0].angle(newPath[1]);
//		for(int i = 0; i < newPath.length - 1; i++) {
//			newPath[i] = GraphicUtil.rotatePoint(newPath[i + 1], newPath[0], angle);
//		}
			
			return newPath;
		} else {
			return current;
		}
	}
	
	
	
	@Override
	public void render(float delta) {
		update(delta);
		draw();
	}
	
	@Override
	public void pause() {
		Settings.save();
		calcThread.stopThread();
		
	}
	
	
}
