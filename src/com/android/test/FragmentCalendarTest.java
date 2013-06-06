package com.android.test;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.test.fragments.CalenderFragment;

public class FragmentCalendarTest extends Activity {
	
	private static final String TAG = FragmentCalendarTest.class.getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentcalendartest);
		Button showCalendar = (Button)findViewById(R.id.showCalendar);
		showCalendar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog();
			}});
		
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
	
	void showDialog() {
	    DialogFragment newFragment = CalenderFragment.newInstance();
	    newFragment.show(getFragmentManager(), "dialog");
	}
}
