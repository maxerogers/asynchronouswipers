package com.example.ntropytest;

import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ntropytest.util.SystemUiHider;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.PushService;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class LobbyActivity extends Activity {
	
	private static final String TAG = "MyCustomReceiver";

	String id;
	
	String gameId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		id = getIntent().getExtras().getString("id");
		getIntent().putExtra("activity", "lobby");
		Parse.initialize(this, "cEdvJbKLIg9bqIjFls3pcjaA0qDqRcJ6l5DSz4vg", "LNOpRZfkZkKsIqgoKwMkji41QPPWg1vKtXPzSSf5");

		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		
		setContentView(R.layout.lobby_activity);

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);
		
		final Button matchmaking = (Button) findViewById(R.id.matchmaking);
		matchmaking.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				PushService.unsubscribe(getBaseContext(), "lobby");
				
				ParseQuery<ParseUser> query = ParseUser.getQuery();
				query.whereEqualTo("objectId", id);
				query.findInBackground(new FindCallback<ParseUser>() {
					public void done(List<ParseUser> objects, ParseException e) {
						if (e == null) {
							Iterator<ParseUser> it = objects.iterator();
							final ParseUser player = it.next();
							player.put("status", "searching");
							player.saveInBackground();
							
							ParseQuery<ParseUser> query = ParseUser.getQuery();
							query.whereEqualTo("status", "waiting");
							query.findInBackground(new FindCallback<ParseUser>() {
								public void done(List<ParseUser> objects, ParseException e) {
									if (e == null) {
										Iterator<ParseUser> it = objects.iterator();
										final ParseUser opponent = it.next();
										opponent.put("status", "inGame");
										opponent.saveInBackground();

										ParseQuery<ParseUser> query = ParseUser.getQuery();
										query.whereEqualTo("objectId", id);
										query.findInBackground(new FindCallback<ParseUser>() {
											public void done(List<ParseUser> objects, ParseException e) {
												if (e == null) {
													Iterator<ParseUser> it = objects.iterator();
													ParseUser player = it.next();
													player.put("status", "inGame");
													player.saveInBackground();

													ParseObject game = new ParseObject("game");
													game.put("playerA", id);
													game.put("PlayerB", opponent.get("objectId"));
													String gameId = game.getString("objectId");
													getIntent().putExtra("opponentType", "b");
													game.saveInBackground();
													gameId = game.getString("objectId");
													
													ParsePush push = new ParsePush();
													JSONObject json = new JSONObject();
													try {
														json.put("action", "com.example.UPDATE_STATUS");
													} catch(JSONException ex) {
														System.out.println(e);
													}
													push.setData(json);
													push.setChannel("lobby");
													String sub = game.get("objectId") + " a";
													PushService.subscribe(getBaseContext(), sub, GameActivity.class);
													push.setMessage((String) opponent.get("objectId") + " " +  gameId);
													push.sendInBackground();
												
													

													//TODO
													//Push P2 to GameActivity
												}
											}
										});
									}
								}
							});
						}
					}
				});
				
				
				Intent i = new Intent(LobbyActivity.this, GameActivity.class);
				i.putExtra("id", id);
				i.putExtra("gameId", gameId);
				startActivity(i);
			}
		});
		
		final Button logOut = (Button) findViewById(R.id.log_out);
		logOut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ParseQuery<ParseUser> query = ParseUser.getQuery();
				query.whereEqualTo("username", id);
				query.findInBackground(new FindCallback<ParseUser>() {
				  public void done(List<ParseUser> objects, ParseException e) {
				    if (e == null) {
				        Iterator<ParseUser> it = objects.iterator();
				        ParseUser object = it.next();
				        object.put("status", false);
				    } else {
				        // Something went wrong.
				    }
				  }
				});
			}
		});
		PushService.subscribe(getBaseContext(), "lobby", LobbyActivity.class);
	
	}
	
}
