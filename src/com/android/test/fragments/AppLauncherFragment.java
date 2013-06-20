package com.android.test.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.test.AppLauncherPageViewerTest;
import com.android.test.R;
import com.android.test.adapters.ApplicationsAdapter;
import com.android.test.model.ApplicationInfo;

public class AppLauncherFragment extends Fragment {
	private static final String TAG = AppLauncherFragment.class.getName();
	public static final String PAGE_NO = "page_no";
	public static final String PAGE_NUM = "page_num";

	public static AppLauncherFragment newInstance(int page_no, int page_num) {
		AppLauncherFragment f = new AppLauncherFragment();

		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt(PAGE_NO, page_no);
		args.putInt(PAGE_NUM, page_num);
		f.setArguments(args);

		return f;
	}

	private ApplicationsAdapter applicationsAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_app_launcher_test,
				container, false);
		GridView body_grid = (GridView) rootView.findViewById(R.id.grid_apps);	
		int page_no = getArguments().getInt(PAGE_NO);
		int page_num = getArguments().getInt(PAGE_NUM);
		applicationsAdapter = new ApplicationsAdapter(getActivity(), ((AppLauncherPageViewerTest)getActivity()).getApps(page_no,page_num));
		body_grid.setAdapter(applicationsAdapter);
		body_grid.setSelection(0);
		body_grid.setOnItemClickListener(new ApplicationLauncher());
		return rootView;
	}
	
	private class ApplicationLauncher implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            ApplicationInfo app = (ApplicationInfo) parent.getItemAtPosition(position);
            startActivity(app.intent);
        }
    }
	
	
}
