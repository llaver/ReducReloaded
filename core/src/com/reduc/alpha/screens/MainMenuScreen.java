package com.reduc.alpha.screens;

import com.badlogic.gdx.graphics.Color;
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
import com.reduc.alpha.util.GraphicUtil;
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
		
		path = pathing.generatePath();
		road = pathing.extendPath(pathing.extendPath(pathing.convertPath(path)));
	}
	
	public void update (float delta) {
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
		
//		Vector2[] gridSpace = pathing.convertToGridspace(road);
//		Vector2[] normals = new Vector2[road.length];
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
		
		for(int i = 0; i < road.length; i++) {
			shapeRenderer.setColor(getColor(road.length, i));
			old = previous;
			previous = current;
			if(road[i] != null) {
				current = new Vector2((current.x) + road[i].x * 10, (current.y) + road[i].y * 10);
				
				shapeRenderer.line(previous.x, previous.y, current.x, current.y);
				shapeRenderer.setColor(1, 1, 0, 1);
				
				//Calculate left
				float left_x1 = current.x;
				float left_y1 = current.y;
				float left_x2 = previous.x;
				float left_y2 = previous.y;
				float D = 30;
				
				float left_dx = left_x1-left_x2;
				float left_dy = left_y1-left_y2;
				float left_dist = (float) Math.sqrt(left_dx*left_dx + left_dy*left_dy);
				left_dx /= left_dist;
				left_dy /= left_dist;
				float left_x3 = left_x1 + D * left_dy;
				float left_y3 = left_y1 - D * left_dx;
				float left_x4 = left_x1 - D * left_dy;
				float left_y4 = left_y1 + D * left_dx;
				
				//Calculate right
				float right_x1 = previous.x;
				float right_y1 = previous.y;
				float right_x2 = old.x;
				float right_y2 = old.y;
				
				float right_dx = right_x1-right_x2;
				float right_dy = right_y1-right_y2;
				float dist = (float) Math.sqrt(right_dx*right_dx + right_dy*right_dy);
				right_dx /= dist;
				right_dy /= dist;
				float right_x3 = right_x1 + D * right_dy;
				float right_y3 = right_y1 - D * right_dx;
				float right_x4 = right_x1 - D * right_dy;
				float right_y4 = right_y1 + D * right_dx;
				
				
				shapeRenderer.line(right_x3, right_y3, left_x3, left_y3);
				shapeRenderer.line(right_x4, right_y4, left_x4, left_y4);
				
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
		
		
		road = cyclePath(road);
		if(road.length < 2000) {
			road = pathing.extendPath(road);
		}
		
		game.batcher.enableBlending();
		game.batcher.begin();
		//game.batcher.draw(Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
		//game.batcher.draw(Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
		//game.batcher.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
		game.batcher.end();
	}
	
	private Vector2[] cyclePath(Vector2[] current) {
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
	}
	
	
	
	@Override
	public void render(float delta) {
		update(delta);
		draw();
	}
	
	@Override
	public void pause() {
		Settings.save();
	}
	
	
}
