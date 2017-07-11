package com.game.alpha.reduc.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.game.alpha.reduc.R;

public class MainMenu extends Activity {
	
	//TODO Get rid of all the bs AppCompat shit I accidentally left on by default
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		//INTENTS I GUESS
		final Intent playGameIntent = new Intent(this, PlayGameActivity.class); //TODO well it would probably be a good idea to come up with a game......
		final Intent settingsIntent = new Intent(this, SettingsActivity.class); //TODO LET THE PEOPLE CHOOSE
		
		final Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startService(playGameIntent);
			}
		});
		final Button button2 = (Button) findViewById(R.id.button2);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(settingsIntent);
			}
		});
		
	}
}
