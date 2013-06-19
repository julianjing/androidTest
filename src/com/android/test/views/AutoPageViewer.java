package com.android.test.views;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.android.test.R;

public class AutoPageViewer extends LinearLayout {

	protected static final String TAG = AutoPageViewer.class.getName();
	FragmentPagerAdapter pagerAdapter;
	ViewPager mViewPager;

	public AutoPageViewer(Context context) {
		super(context);
	}

	public AutoPageViewer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AutoPageViewer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setAdapter(FragmentPagerAdapter pagerAdapter) {
		this.pagerAdapter = pagerAdapter;
		reflesh();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}

	private void reflesh() {
		final RadioGroup tickmarks = (RadioGroup) findViewById(R.id.tickmarks);
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
						tickmarks.check(getTickmarksId(position));
					}

					private int getTickmarksId(int position) {
						switch (position) {
						case 0:
							return R.id.radio0;
						case 1:
							return R.id.radio1;
						case 2:
							return R.id.radio2;
						case 3:
							return R.id.radio3;
						}
						return -1;
					}
				});

	}
}
