package com.example.ntropytest;

import com.example.ntropytest.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class LobbyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		
		setContentView(R.layout.lobby_activity);

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);
		
		final Button matchmaking = (Button) findViewById(R.id.matchmaking);
		matchmaking.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(LobbyActivity.this, GameActivity.class);
				startActivity(i);
			}
		});
	}


}
