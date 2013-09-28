package com.example;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.ntropytest.*;
import com.parse.ParsePush;
import com.parse.PushService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class MyCustomReceiver {
	public void onReceive(Context context, Intent intent) {
		String channel = intent.getExtras().getString("com.parse.Channel");
		String message = intent.getExtras().getString("com.parse.Message");
		String activity = intent.getStringExtra("activity");
		if(activity.equals("lobby")) {
			String id = intent.getStringExtra("id");
			String idFromMessage = message.substring(0, message.indexOf(' '));
			if(idFromMessage.equals(id)) {
				intent.putExtra("opponentType", "a");
				PushService.unsubscribe(context, "lobby");
				PushService.subscribe(context, message.substring(message.indexOf(' ') + 1), GameActivity.class);
				ParsePush push = new ParsePush();
				JSONObject json = new JSONObject();
				try {
					json.put("action", "com.example.UPDATE_STATUS");
				} catch(JSONException e) {
					System.out.println(e);
				}
				push.setData(json);
				push.setMessage("Begin game");
				push.sendInBackground();
			}
			intent = new Intent(context, LobbyActivity.class);
		} else if(activity.equals("login")) {
			intent = new Intent(context, LoginActivity.class);
		} else if(activity.equals("game")) {
			intent.putExtra("messageReceived", true);
			intent = new Intent(context, GameActivity.class);
		}
		
		intent.putExtra("channel", channel);
		intent.putExtra("message", message);
		context.startActivity(intent);
	}
}
