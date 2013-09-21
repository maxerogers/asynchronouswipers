package com.example.ntropytest;

import com.parse.*;
import com.parse.entity.mime.content.ContentBody;
import com.example.ntropytest.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class LoginActivity extends Activity {

	ParseUser user = new ParseUser();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "cEdvJbKLIg9bqIjFls3pcjaA0qDqRcJ6l5DSz4vg", "LNOpRZfkZkKsIqgoKwMkji41QPPWg1vKtXPzSSf5");
		ParseAnalytics.trackAppOpened(getIntent());
		setContentView(R.layout.login_activity);

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);

		final ToggleButton signInToggle = (ToggleButton) findViewById(R.id.sign_in_toggle);
		final Button submitButton = (Button) findViewById(R.id.submit_button);
		final EditText username = (EditText) findViewById(R.id.username_text);
		final EditText password = (EditText) findViewById(R.id.password_text);
		final EditText passwordConfirm = (EditText) findViewById(R.id.password_confirm_text);
		final TextView usernameTaken = (TextView) findViewById(R.id.username_taken_text);
		final TextView passwordFailure = (TextView) findViewById(R.id.password_failure_text);
		final TextView noMatch = (TextView) findViewById(R.id.no_match_text);
		final TextView success = (TextView) findViewById(R.id.success_text);

		usernameTaken.setVisibility(View.GONE);
		passwordFailure.setVisibility(View.GONE);
		passwordConfirm.setVisibility(View.GONE);
		noMatch.setVisibility(View.GONE);
		success.setVisibility(View.GONE);

		signInToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked) {
					passwordConfirm.setVisibility(View.VISIBLE);
				} else {
					passwordConfirm.setVisibility(View.GONE);
				}
				usernameTaken.setVisibility(View.GONE);
				passwordFailure.setVisibility(View.GONE);
				noMatch.setVisibility(View.GONE);
				success.setVisibility(View.GONE);
			}
		});

		submitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Sign Up
				if(signInToggle.isChecked()) {
					if(!password.getText().toString().equals(passwordConfirm.getText().toString()) || !username.getText().toString().equals("")) {
						passwordFailure.setVisibility(View.VISIBLE);
						success.setVisibility(View.GONE);
					} else {
						passwordFailure.setVisibility(View.GONE);
						user.setPassword(password.getText().toString());
						user.setUsername(username.getText().toString());
						user.signUpInBackground(new SignUpCallback() {
							public void done(ParseException e) {
								if (e == null) {
									success.setVisibility(View.VISIBLE);
									Intent i = new Intent(LoginActivity.this, LobbyActivity.class);
									startActivity(i);
								} else {
									usernameTaken.setVisibility(View.VISIBLE);
									success.setVisibility(View.GONE);
								}
							}
						});
					}
					//Sign In
				} else {
					success.setVisibility(View.GONE);
					passwordConfirm.setVisibility(View.GONE);
					usernameTaken.setVisibility(View.GONE);
					ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback()
					{
						@Override
						public void done(ParseUser userDone, ParseException e)
						{
							if(userDone != null) {
								Intent i = new Intent(LoginActivity.this, LobbyActivity.class);
								startActivity(i);
							}else {
								noMatch.setVisibility(View.VISIBLE);
							}
						}
					});
				}
			}
		});


	}
}
