package com.rcarrillocruz.android.openstackdroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;

public class CloudBrowserActivity extends FragmentActivity implements ActionBar.TabListener {
	  
	protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);

		    final ActionBar actionBar = getActionBar();
		    actionBar.setDisplayShowTitleEnabled(false);
		    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		    actionBar.setDisplayHomeAsUpEnabled(true);
		    actionBar.setHomeButtonEnabled(true);
		    
		    ActionBar.Tab serverTab = actionBar.newTab().setText("Instances").setTabListener(this);
		    ActionBar.Tab imageTab = actionBar.newTab().setText("Images").setTabListener(this);
		    ActionBar.Tab flavorTab = actionBar.newTab().setText("Flavors").setTabListener(this);
		    
		    
		    actionBar.addTab(serverTab);
		    actionBar.addTab(imageTab);
		    actionBar.addTab(flavorTab);
		  }

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			Intent i = new Intent(this, LoginActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
}
