package com.reduc.alpha.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.reduc.alpha.R;
import com.reduc.alpha.views.GameCanvasView;

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
