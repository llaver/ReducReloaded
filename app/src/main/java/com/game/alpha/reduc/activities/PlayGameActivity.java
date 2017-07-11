package com.game.alpha.reduc.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.game.alpha.reduc.R;
import com.game.alpha.reduc.views.GameCanvasView;

public class PlayGameActivity extends Activity {
	
	private GameCanvasView gameCanvas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		
		System.out.println("In Game Activity");
		
		gameCanvas = (GameCanvasView) findViewById(R.id.main_game_canvas_id);
	}
	
	public void clearCanvas(View v) {
		gameCanvas.clearCanvas();
	}
}
