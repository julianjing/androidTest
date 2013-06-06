package com.android.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class CalendarViewTest extends Activity {
	
	private static final String TAG = CalendarViewTest.class.getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comboxboxlayout);
		
		Log.d(TAG,"created");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG,"resumed");
	}

	@Override
	protected void onPause() {
		super.onPause();	
		Log.d(TAG,"paused");

		if (isFinishing()) {
			Log.d(TAG,"finishing");
		}
	}
}
