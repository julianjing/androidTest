package com.android.test.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.test.R;
import com.android.test.views.LMCalendarView;
import com.android.test.views.LMCalendarView.Day;


public class CalenderFragment extends DialogFragment {

	private static final String TAG = CalenderFragment.class.getName();

	
	public static CalenderFragment newInstance() {
		CalenderFragment f = new CalenderFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar_fragment, container, false);
        LMCalendarView calendar = (LMCalendarView)v.findViewById(R.id.lmcalendarview);
        
        final GridView days_grid = calendar.getGrid();
        days_grid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	Day day = (Day) days_grid.getAdapter().getItem(position);
                Toast.makeText(CalenderFragment.this.getActivity(), 
                		"day:" + day.getDay() + "-"+day.getMonth(), Toast.LENGTH_SHORT).show();
                CalenderFragment.this.dismiss();
                
            }
        });
        return v;
    }



}