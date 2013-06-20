package com.android.test.views;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.test.R;

public class AutoPageViewer extends LinearLayout {

	protected static final String TAG = AutoPageViewer.class.getName();
	FragmentPagerAdapter pagerAdapter;
	ViewPager mViewPager;
	Context mContext;
	private int page_num;

	public AutoPageViewer(Context context) {
		super(context);
		this.mContext = context;
	}

	public AutoPageViewer(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	}

	public AutoPageViewer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
	}

	public void setAdapter(FragmentPagerAdapter pagerAdapter) {
		this.pagerAdapter = pagerAdapter;
		this.page_num = pagerAdapter.getCount();
		reflesh();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}

	private void reflesh() {
		//add dynamic tickmark according to page_num
		final RadioGroup tickmarks = (RadioGroup) findViewById(R.id.tickmarks);
		tickmarks.removeAllViews();
		if (page_num > 1) {
			for(int i = 0; i < page_num; i++) {
				RadioButton radiobutton = new RadioButton(mContext);
				tickmarks.addView(radiobutton);
			}
			((CompoundButton) tickmarks.getChildAt(0)).setChecked(true);
		}
		
		mViewPager = (ViewPager) findViewById(R.id.pager);

		// ViewPager and its adapters use support library
		// fragments, so use getSupportFragmentManager.

		mViewPager.setAdapter(pagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between pages, select the
						// corresponding tab.
						Log.d(TAG, "tickmarks check:" + position);
						if (page_num > 1) {
							RadioButton button = null;
							int count = tickmarks.getChildCount();
							for (int i=0; i < count; i++) {
								button = (RadioButton)tickmarks.getChildAt(position);
								button.setChecked(false);
							}
							
							button = (RadioButton)tickmarks.getChildAt(position);
							button.setChecked(true);
						}
					}

				});

	}
}
