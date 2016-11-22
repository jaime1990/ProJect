package com.commonui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public class FragmentController {

	private int containerId;
	private FragmentManager fm;
	private ArrayList<Fragment> fragments;
	
	private static FragmentController controller;

	public static FragmentController getInstance(FragmentActivity activity, int containerId, ArrayList<Fragment> fragments) {
		if (controller == null) {
			controller = new FragmentController(activity, containerId, fragments);
		}
		return controller;
	}
	
	public static void onDestroy() {
		controller = null;
	}

	private FragmentController(FragmentActivity activity, int containerId, ArrayList<Fragment> fragments) {
		this.containerId = containerId;
		fm = activity.getSupportFragmentManager();
		initFragment(fragments);
	}

	private void initFragment(ArrayList<Fragment> fragments) {
		this.fragments = fragments;
		FragmentTransaction ft = fm.beginTransaction();
		for(Fragment fragment : fragments) {
			ft.add(containerId, fragment);
		}
		ft.commitAllowingStateLoss();
	}

	public void showFragment(int position) {
		hideFragments();
		Fragment fragment = fragments.get(position);
		FragmentTransaction ft = fm.beginTransaction();
		ft.show(fragment);
		ft.commitAllowingStateLoss();
	}
	
	public void hideFragments() {
		FragmentTransaction ft = fm.beginTransaction();
		for(Fragment fragment : fragments) {
			if(fragment != null) {
				ft.hide(fragment);
			}
		}
		ft.commitAllowingStateLoss();
	}
	
	public Fragment getFragment(int position) {
		return fragments.get(position);
	}
}