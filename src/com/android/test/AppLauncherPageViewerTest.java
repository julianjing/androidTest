package com.android.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.android.test.fragments.AppLauncherFragment;
import com.android.test.model.ApplicationInfo;
import com.android.test.views.AutoPageViewer;

public class AppLauncherPageViewerTest extends FragmentActivity {
	protected static final String TAG = AppLauncherPageViewerTest.class
			.getName();
	AppLauncherPagerAdapter appLauncherPagerAdapter;
	private ArrayList<ApplicationInfo> appsCache;
	private ArrayList<ApplicationInfo> apps;
	private AutoPageViewer appspageviewer;
	private static final int PAGENUM = 20;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.applauncherpageviewertest);
		loadApplicationsInCache(true);

		SearchView searchapps = (SearchView) findViewById(R.id.searchapps);
		searchapps.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextChange(String newText) {
				getApps(newText);
				appLauncherPagerAdapter = new AppLauncherPagerAdapter(
						getSupportFragmentManager(), apps, PAGENUM);
				appspageviewer.setAdapter(appLauncherPagerAdapter);
				Log.d(TAG, "onQueryTextChange:"+newText);
				//appLauncherPagerAdapter.notifyDataSetChanged();
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		getApps(null);
		appspageviewer = (AutoPageViewer) findViewById(R.id.autopageviewer);
		appLauncherPagerAdapter = new AppLauncherPagerAdapter(
				getSupportFragmentManager(), apps, PAGENUM);
		appspageviewer.setAdapter(appLauncherPagerAdapter);

	}
	
	private void loadApplicationsInCache(boolean isLaunching) {
        if (isLaunching && appsCache != null) {
            return;
        }

        PackageManager manager = getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        final List<ResolveInfo> apps = manager.queryIntentActivities(mainIntent, 0);
        Collections.sort(apps, new ResolveInfo.DisplayNameComparator(manager));

        if (apps != null) {
            final int count = apps.size();

            if (appsCache == null) {
            	appsCache = new ArrayList<ApplicationInfo>(count);
            }
            appsCache.clear();

            for (int i = 0; i < count; i++) {
                ApplicationInfo application = new ApplicationInfo();
                ResolveInfo info = apps.get(i);

                application.title = info.loadLabel(manager);
                application.setActivity(new ComponentName(
                        info.activityInfo.applicationInfo.packageName,
                        info.activityInfo.name),
                        Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                application.icon = info.activityInfo.loadIcon(manager);

                appsCache.add(application);
            }
        }
    }
	
	public void getApps(String fliter) {
		Log.d(TAG,"fliter:"+ fliter);		
		apps = new ArrayList<ApplicationInfo>();      
		int count =  appsCache.size();
		Log.d(TAG,"countr:"+ count);
		if (fliter == null || fliter.equalsIgnoreCase("")) {
			Log.d(TAG,"fliter == null");
			apps = appsCache;
		} else {
			for (int i = 0; i < count; i++) {
				String title = appsCache.get(i).title.toString();
				Log.d(TAG,"title:"+ title +"fliter:"+fliter);
				if (title.indexOf(fliter) != -1) {
					apps.add(appsCache.get(i));
				}
			}
		}
	}
	
	public ArrayList<ApplicationInfo> getApps(int page_no, int page_num) {
	
		int start = page_no * page_num;
		int end = Math.min(start + page_num, apps.size());
		ArrayList<ApplicationInfo> page_apps= new ArrayList<ApplicationInfo>();
		for (int i = start; i < end; i++) {
			page_apps.add(apps.get(i));
		}
		return page_apps;
	}

}

class AppLauncherPagerAdapter extends FragmentPagerAdapter {

	private static final String TAG = "AppLauncherPagerAdapter";
	private int page_size;
	private int page_num; 

	public AppLauncherPagerAdapter(FragmentManager fm,
			ArrayList<ApplicationInfo> apps, int page_num) {
		super(fm);
		this.page_num = page_num;
		this.page_size = (int)Math.ceil((float)apps.size() / (page_num));
		Log.d(TAG, "page_size:"+page_size+":apps.size()"+apps.size());
	}

	@Override
	public Fragment getItem(int i) {
		AppLauncherFragment fm = AppLauncherFragment.newInstance(i, page_num);
		return fm;
	}

	@Override
	public int getCount() {
		return page_size;
	}

}
