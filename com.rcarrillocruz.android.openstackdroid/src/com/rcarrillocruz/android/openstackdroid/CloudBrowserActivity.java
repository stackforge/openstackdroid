package com.rcarrillocruz.android.openstackdroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;

public class CloudBrowserActivity extends Activity implements TabListener {
	private Fragment mListFragmentAttached;
	private Fragment mServerListFragment;
	private Fragment mServerDetailsFragment;
	private Fragment mVolumeListFragment;
	private Fragment mVolumeDetailsFragment;
	private boolean isDetailEnabled;
	
	protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    isDetailEnabled = false;
		    mListFragmentAttached = null;
		    mServerListFragment = null;
		    mServerDetailsFragment = null;
		    mVolumeListFragment = null;
		    mVolumeDetailsFragment = null;
		    setContentView(R.layout.cloud_browser);
		    
		    final ActionBar actionBar = getActionBar();
		    actionBar.setDisplayShowTitleEnabled(false);
		    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		    actionBar.setDisplayHomeAsUpEnabled(true);
		    actionBar.setHomeButtonEnabled(true);
		    		    
		    ActionBar.Tab serverTab = actionBar.newTab().setText("Instances");
		    ActionBar.Tab volumeTab = actionBar.newTab().setText("Volumes");
		    
		    serverTab.setTabListener(this);
		    volumeTab.setTabListener(this);
		    
		    actionBar.addTab(serverTab);
		    actionBar.addTab(volumeTab);
		    
		    //actionBar.addTab(flavorTab);
		    
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			if(isDetailEnabled) {
				LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 0);
		        FrameLayout fl = (FrameLayout) findViewById(R.id.item_details);
		        fl.setLayoutParams(params);
		        setDetailEnabled(false);
			} else {
				Intent i = new Intent(this, LoginActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Class<?> tabClass = null;
		
		switch(tab.getPosition()) {
		case 0:
			tabClass = ServerListFragment.class;
			break;
		case 1:
			tabClass = VolumeListFragment.class;
			break;
		}		
		
		hideDetailsLayout();

		mListFragmentAttached = getFragmentManager().findFragmentById(R.id.items_list);
		
        if(mListFragmentAttached==null) {
        	mListFragmentAttached = Fragment.instantiate(this, tabClass.getName());
        	
        	if (tabClass == ServerListFragment.class) {
        		mServerListFragment = mListFragmentAttached;
        	} else if (tabClass == VolumeListFragment.class) {
        		mVolumeListFragment = mListFragmentAttached;
        	}
        	
            ft.add(R.id.items_list, mListFragmentAttached); 
        } else {
        	if (tabClass == ServerListFragment.class) {
        		if (mServerListFragment != null) {
        			mListFragmentAttached = mServerListFragment;
        			ft.attach(mListFragmentAttached);
        		} else {
        			mListFragmentAttached = Fragment.instantiate(this, tabClass.getName());
        			mServerListFragment = mListFragmentAttached;
        			ft.add(R.id.items_list, mListFragmentAttached);
        		}
        	} else if (tabClass == VolumeListFragment.class) {
        		if (mVolumeListFragment != null) {
        			mListFragmentAttached = mVolumeListFragment;
        			ft.attach(mListFragmentAttached);
        		} else {
        			mListFragmentAttached = Fragment.instantiate(this, tabClass.getName());
        			mVolumeListFragment = mListFragmentAttached;
        			ft.add(R.id.items_list, mListFragmentAttached);
        		}
        	}  
        } 
        
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if (mListFragmentAttached != null)
			ft.detach(mListFragmentAttached);
	}
	
	public void showDetailsLayout() {
        LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
        FrameLayout fl = (FrameLayout) findViewById(R.id.item_details);
        fl.setLayoutParams(params); 
        setDetailEnabled(true);
	}
	
	public void hideDetailsLayout() {
		LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 0);
        FrameLayout fl = (FrameLayout) findViewById(R.id.item_details);
        fl.setLayoutParams(params);
        setDetailEnabled(false);
	}

	public boolean isDetailEnabled() {
		return isDetailEnabled;
	}

	public void setDetailEnabled(boolean isDetailEnabled) {
		this.isDetailEnabled = isDetailEnabled;
	}

	public Fragment getmServerDetailsFragment() {
		return mServerDetailsFragment;
	}

	public void setmServerDetailsFragment(Fragment mServerDetailsFragment) {
		this.mServerDetailsFragment = mServerDetailsFragment;
	}

	public Fragment getmVolumeDetailsFragment() {
		return mVolumeDetailsFragment;
	}

	public void setmVolumeDetailsFragment(Fragment mVolumeDetailsFragment) {
		this.mVolumeDetailsFragment = mVolumeDetailsFragment;
	}
	
	 
}
