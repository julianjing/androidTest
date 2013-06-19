package com.android.test.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.test.R;
import com.android.test.adapters.DemoCollectionPagerAdapter.DemoObjectFragment;

public class AppLauncherFragment extends Fragment {
	private static final String TAG = AppLauncherFragment.class.getName();
	public static final String ARG_OBJECT = "object";

	public static AppLauncherFragment newInstance(int i) {
		AppLauncherFragment f = new AppLauncherFragment();

		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_collection_object,
				container, false);
		Bundle args = getArguments();
		((TextView) rootView.findViewById(R.id.textView1)).setText("Page:"
				+ Integer.toString(args.getInt(ARG_OBJECT)));
		return rootView;
	}
}
