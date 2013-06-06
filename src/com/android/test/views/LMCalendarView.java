package com.android.test.views;

import java.util.ArrayList;
import java.util.Calendar;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.test.R;
import com.android.test.views.LMCalendarView.DatesAdapter;

public class LMCalendarView extends LinearLayout {
	
	public Calendar month;
	private int current_month;
	private DatesAdapter adapter;
	private TextView current_year;
	private int current_day;
	
	public LMCalendarView(Context context) {
        super(context);
    }

    public LMCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LMCalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        
      //init view
        current_year = (TextView)findViewById(R.id.current_year);  
        Button next_month = (Button)findViewById(R.id.next_month);
        next_month.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateCalendarMonth(++current_month);
			}});
        
        Button pre_month = (Button)findViewById(R.id.pre_month);
        pre_month.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateCalendarMonth(--current_month);
			}
        });
        
        //init month grid
        GridView body_grid = (GridView) findViewById(R.id.body_grid);
        adapter = new DatesAdapter(LMCalendarView.this.getContext());
        body_grid.setAdapter(adapter);
        body_grid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	Day day = (Day) adapter.getItem(position);
                Toast.makeText(LMCalendarView.this.getContext(), 
                		"day:" + day.getDay() + "-"+day.getMonth(), Toast.LENGTH_SHORT).show();
            }
        });
        
        
        //inin fields
        month = Calendar.getInstance();
        current_month = month.get(Calendar.MONTH); 
        current_day = month.get(Calendar.DAY_OF_MONTH);
  
        //refresh view
        updateCalendarMonth(current_month);
       
    }
    
    public void updateCalendarMonth(int month_num) {
		month = Calendar.getInstance();
		month.set(Calendar.MONTH, month_num);	
		current_year.setText(DateFormat.format("MMMM yyyy", month));
		adapter.setDates(days_of_month(month,month.get(Calendar.MONTH), month.get(Calendar.YEAR)));
		adapter.notifyDataSetChanged();
	}
    
    private static ArrayList<Day> days_of_month(Calendar cal, int month,
			int year) {
		int first_date_offsit = first_date_offsit(cal, month,
				year); //sunday  is 7		
		int size = 7*6;
		ArrayList<Day> dates = new ArrayList<Day>();
		int first_date_dis = first_date_offsit-1;
		cal.set(Calendar.DAY_OF_MONTH, 1-first_date_dis);
		
		for(int i=0; i< size; i++) {			
			dates.add(new Day(cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.MONTH)+1));		
			cal.roll(Calendar.DAY_OF_YEAR, true);
		}
		return dates;
	}

	private static int first_date_offsit(Calendar cal, int month,
			int year) {
		cal.set(year, month, 1);	
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public class DatesAdapter extends BaseAdapter {
	    private Context mContext;
	    private ArrayList<Day> dates = new ArrayList<Day>();
	    
	    public DatesAdapter(Context c) {
	        mContext = c;
	    } 
	    
	    public void setDates(ArrayList<Day> dates) {
	    	this.dates = dates;
	    }
	    
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView dayView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            dayView = new TextView(mContext);
	            dayView.setLayoutParams(new GridView.LayoutParams(48, 48));
	            dayView.setPadding(8, 8, 8, 8);
	        } else {
	            dayView = (TextView) convertView;
	        }
	        Day day = dates.get(position);
	        dayView.setText(""+day.getDay());
	        if (day.equalMonth(current_month)) {
	        	dayView.setBackgroundColor(Color.BLUE);
	        	if (current_day == day.getDay()) {
	        		dayView.setBackgroundColor(Color.RED);
	        	}
	        } else {
	        	dayView.setBackgroundColor(Color.TRANSPARENT);
	        }
	        
			
			return dayView;
		}

		@Override
		public int getCount() {
			return dates.size();
		}

		@Override
		public Object getItem(int position) {
			return dates.get(position);
		}

		@Override
		public long getItemId(int position) {
			return dates.get(position).getDay();
		}
	}
	
	static class DayHolder {
		TextView day;
	}
	
	public static class Day {
		private int day = -1;
		private int month = -1;
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
		
		public boolean equalMonth(int month) {
			if (((month+12*100)%12+1) == getMonth()) {
				return true;
			} else {
				return false;
			}
		}
	}
}
