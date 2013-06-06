package com.android.test;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CalendarTest extends Activity {
	StringBuilder builder = new StringBuilder();
	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		Calendar cal = Calendar.getInstance(); // GregorianCalendar
		cal.set(Calendar.MONTH, 5);
		displayCalendar(cal);
		
		textView.setText(builder.toString());
		setContentView(textView);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {
		super.onPause();

		if (isFinishing()) {

		}
	}
	
	private void displayCalendar(Calendar cal) {
		int current_month = cal.get(Calendar.MONTH);
		int current_YEAR = cal.get(Calendar.YEAR);
		
		//first day of current month
		builder.append("Year  : " + cal.get(Calendar.YEAR));
		builder.append('\n');
		builder.append("Month : " + (cal.get(Calendar.MONTH)+1));
		builder.append('\n');
		
		Day[] dates = days_of_month(cal, current_month, current_YEAR);
		int count = 1;
		for(int i=0; i< dates.length; i++) {	
			builder.append("" + dates[i].getDay()+"-"+dates[i].getMonth()+",");
			if (count%7 == 0) {
				builder.append('\n');
			}
			count++;
		}
	}

	private Day[] days_of_month(Calendar cal, int month,
			int year) {
		int first_date_offsit = first_date_offsit(cal, month,
				year); //sunday  is 7		
		Day[] dates = new Day[7*6];
		int first_date_dis = first_date_offsit-1;
		cal.set(Calendar.DAY_OF_MONTH, 1-first_date_dis);
		
		for(int i=0; i< dates.length; i++) {			
			dates[i] = new Day(cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.MONTH)+1);		
			cal.roll(Calendar.DAY_OF_YEAR, true);
		}
		return dates;
	}

	private int first_date_offsit(Calendar cal, int month,
			int year) {
		cal.set(year, month, 1);	
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static class Day {
		int day = -1;
		int month = -1;
		public Day(int day, int month) {
			super();
			this.day = day;
			this.month = month;
		}
		public int getDay() {
			return day;
		}
		public int getMonth() {
			return month;
		}
	}
}
