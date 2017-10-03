package com.reduc.alpha.util.threads;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.reduc.alpha.entities.Road;
import com.reduc.alpha.screens.GameScreen;
import com.reduc.alpha.screens.MainMenuScreen;
import com.reduc.alpha.util.Pathing;

public class CalculationsThread extends Thread {
	
	private MainMenuScreen m_screen;
	private boolean m_runing;
	private long m_timeBegin;
	private long m_timeDiff;
	private long m_sleepTime;
	private final static float FRAMERATE = 60f;
	Logger logger = new Logger("Calculations Thread");
	
	Pathing pathing = new Pathing();
	
	Vector2[] path = null;
	Vector2[] roadPath = null;
	
	Road road;
	
	public CalculationsThread(MainMenuScreen screen) { //pass the game screen to it.
		m_screen = screen;
		setName("GameLogic");
	}
	
	@Override
	public void run() {
		m_runing = true;
		logger.info("Started Calculations Thread");
		while (m_runing) {
			m_timeBegin = TimeUtils.millis();
			// act of the camera
			synchronized(m_screen.road) { //stage with figures
				//Get the Road instance
				road = m_screen.road;
				// now figures
				if(road.getSize() <= 1) {
					//Generate road
					path = pathing.generatePath();
					roadPath = pathing.extendPath(pathing.extendPath(pathing.convertPath(path)));
					
					Vector2[][] bounds = pathing.calculateBounds(roadPath);
					road.setAll(path, bounds[0], bounds[1]);
					
				} else if (road.getSize() < 2000 && road.getSize() > 1) {
					//Extend road
					roadPath = pathing.extendPath(road.path);
					
					Vector2[][] bounds = pathing.calculateBounds(roadPath);
					road.setAll(path, bounds[0], bounds[1]);
				}
			}
			m_timeDiff = TimeUtils.millis() - m_timeBegin;
			m_sleepTime = (long) (1f / CalculationsThread.FRAMERATE * 1000f - m_timeDiff);
			if (m_sleepTime > 0) {
				try {
					Thread.sleep(m_sleepTime);
				}
				catch (InterruptedException e) {
					logger.error("Couldn't sleep " + e.getStackTrace());
				}
			} else {
				logger.error("we are to slow! " + m_sleepTime); //might create it dynamic so if you are to slow decrease the framerate till you are not to slow anymore
			}
		}
	}
	
	/**
	 * Stops the thread save<br>
	 */
	public void stopThread() {
		m_runing = false;
		boolean retry = true;
		while (retry) {
			try {
				this.join();
				retry = false;
			}
			catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}