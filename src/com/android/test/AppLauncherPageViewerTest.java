package com.android.test;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.test.adapters.DemoCollectionPagerAdapter;
import com.android.test.fragments.AppLauncherFragment;

public class AppLauncherPageViewerTest extends FragmentActivity {
    protected static final String TAG = AppLauncherPageViewerTest.class.getName();
    AppLauncherPagerAdapter appLauncherPagerAdapter;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState); 
        setContentView(R.layout.pageviewertest);
        
        final RadioGroup tickmarks = (RadioGroup) findViewById(R.id.tickmarks);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        appLauncherPagerAdapter =
                new AppLauncherPagerAdapter(
                        getSupportFragmentManager(),3);
        
    
        mViewPager.setAdapter(appLauncherPagerAdapter);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        Log.d(TAG, "tickmarks check:"+position);         
                        tickmarks.check(getTickmarksId(position));
                    }

					private int getTickmarksId(int position) {
						switch(position) {
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


        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab arg0,
					android.app.FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabSelected(Tab arg0,
					android.app.FragmentTransaction arg1) {
				 mViewPager.setCurrentItem(arg0.getPosition());
			}

			@Override
			public void onTabUnselected(Tab arg0,
					android.app.FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
        };

    }
}

class AppLauncherPagerAdapter extends FragmentPagerAdapter {
		
	private int pageSize;

	public AppLauncherPagerAdapter(FragmentManager fm, int pageSize) {
		super(fm);
		this.pageSize = pageSize;
	}

	@Override
	public Fragment getItem(int i) {
		return AppLauncherFragment.newInstance(i);
	}

	@Override
	public int getCount() {
		return pageSize;
	}
}


