package com.example.ntropytest;

import com.example.ntropytest.util.SystemUiHider;
import com.parse.ParseInstallation;
import com.parse.PushService;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class PushActivity extends Activity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PushService.setDefaultPushCallback(this, PushActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		
		setContentView(R.layout.push_activity);

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);
		
		String origin = getIntent().getExtras().getString("origin");
		String id = getIntent().getExtras().getString("id");
		String gameId = getIntent().getExtras().getString("gameId");
		
		Intent i = new Intent(PushActivity.this, LoginActivity.class);
		
		if(origin.equals("GameActivity")) {
			i = new Intent(PushActivity.this, GameActivity.class);
		} else if(origin.equals("LobbyActivity")) {
			i = new Intent(PushActivity.this, LobbyActivity.class);
		} else if(origin.equals("LoginActivity")) {
			i = new Intent(PushActivity.this, LoginActivity.class);
		} else System.out.println("Fuck me");
		
		i.putExtra("id", id);
		i.putExtra("gameId", gameId);
		
		startActivity(i);
		

	}
}
