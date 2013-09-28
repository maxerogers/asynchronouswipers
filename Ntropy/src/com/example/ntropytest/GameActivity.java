package com.example.ntropytest;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import gameComponents.Move;
import gameComponents.src.BoardModel;

import com.example.ntropytest.util.SystemUiHider;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class GameActivity extends Activity  {
	
	private final static int STANDARD_SIZE = 6;
	
	String gameId, id;
	
	BoardModel board;
	
	String oppType;
	DrawView drawView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		intent.putExtra("activity", "game");
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		drawView = new DrawView(this);
		setContentView(drawView);
		drawView.requestFocus();
		
		gameId = getIntent().getExtras().getString("gameId");
		id = getIntent().getExtras().getString("id");
		
		Parse.initialize(this, "cEdvJbKLIg9bqIjFls3pcjaA0qDqRcJ6l5DSz4vg", "LNOpRZfkZkKsIqgoKwMkji41QPPWg1vKtXPzSSf5");
		
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);

		setContentView(R.layout.game_activity);
		
		boolean gameContinue = true;
		
		oppType = intent.getStringExtra("opponentType");
		
		boolean notMyTurn = oppType.equals("b");
		
		board = new BoardModel(STANDARD_SIZE);
		
		while(gameContinue) {
			
			
			
			while(notMyTurn) {
				if(intent.getBooleanExtra("messageReceieved", false)) {
					updateOpponentMove(intent);
				}
			}
		}

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);
	}
	
	/**
	 * Sends the move to Parse DB
	 */
	public void makeMove(int token, int position) {
		ParseObject move = new ParseObject("Move");
		move.put("gameId", gameId);
		move.put("gridPosition", position);
		move.put("playerId", id);
		move.put("value", token);
		move.saveInBackground();
		
		board.changeSquare(position/STANDARD_SIZE, position%STANDARD_SIZE, token);
		
		ParsePush push = new ParsePush();
		JSONObject json = new JSONObject();
		try {
			json.put("action", "com.example.UPDATE_STATUS");
		} catch(JSONException ex) {
			System.out.println(ex);
		}
		push.setData(json);
		push.setChannel(gameId + oppType);
		push.setMessage(gameId + " " + position + " " + token);
		push.sendInBackground();
	}
	
	private void updateOpponentMove(Intent intent) {
		String message = intent.getStringExtra("message");
		String[] messages = message.split(" ");
		String value = messages[1];
		String positionString = messages[2];
		
		intent.putExtra("messageReceived", false);
		
		int position = Integer.parseInt(positionString);
		int col = position%STANDARD_SIZE;
		int row = position/STANDARD_SIZE;
		
		board.changeSquare(position/STANDARD_SIZE, position%STANDARD_SIZE, Integer.parseInt(value));
	}
}
