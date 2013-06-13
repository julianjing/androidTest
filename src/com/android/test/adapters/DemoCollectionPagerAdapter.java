package com.android.test.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.test.R;

public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
	public DemoCollectionPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new DemoObjectFragment();
		Bundle args = new Bundle();
		// Our object is just an integer :-P
		args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return 100;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "OBJECT " + (position + 1);
	}

	// Instances of this class are fragments representing a single
	// object in our collection.
	public static class DemoObjectFragment extends Fragment {
		public static final String ARG_OBJECT = "object";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// The last two arguments ensure LayoutParams are inflated
			// properly.
			View rootView = inflater.inflate(
					R.layout.fragment_collection_object, container, false);
			Bundle args = getArguments();
			((TextView) rootView.findViewById(R.id.textView1))
					.setText("Page:"+Integer.toString(args.getInt(ARG_OBJECT)));
			return rootView;
		}
	}

}
