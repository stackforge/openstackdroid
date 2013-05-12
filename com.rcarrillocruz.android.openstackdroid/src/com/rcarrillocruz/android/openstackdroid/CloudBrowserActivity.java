package com.rcarrillocruz.android.openstackdroid;

import java.util.ArrayList;
import java.util.List;

import com.rcarrillocruz.android.openstackdroid.model.FlavorModel;
import com.rcarrillocruz.android.openstackdroid.model.ImageModel;
import com.rcarrillocruz.android.openstackdroid.model.ServerModel;
import com.rcarrillocruz.android.openstackdroid.model.TenantModel;
import com.rcarrillocruz.android.openstackdroid.model.UserModel;
import com.rcarrillocruz.android.openstackdroid.model.VolumeModel;

import android.os.Bundle;
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
	private List<ServerModel> servers;
	private List<VolumeModel> volumes;
	private List<FlavorModel> flavors;
	private List<ImageModel> images;
	public List<UserModel> users;
	public List<TenantModel> tenants;
	
	private Fragment mListFragmentAttached;
	private Fragment mServerListFragment;
	private Fragment mServerDetailsFragment;
	private Fragment mVolumeListFragment;
	private Fragment mVolumeDetailsFragment;
	private Fragment mFlavorListFragment;
	private Fragment mFlavorDetailsFragment;
	private Fragment mImageListFragment;
	private Fragment mImageDetailsFragment;
	private Fragment mUserListFragment;
	private Fragment mUserDetailsFragment;
	private Fragment mTenantListFragment;
	private Fragment mTenantDetailsFragment;
	
	private boolean isDetailEnabled;
	
	protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    servers = new ArrayList<ServerModel>();
		    volumes = new ArrayList<VolumeModel>();
		    flavors = new ArrayList<FlavorModel>();
		    images = new ArrayList<ImageModel>();
		    users = new ArrayList<UserModel>();
		    tenants = new ArrayList<TenantModel>();
		    
		    isDetailEnabled = false;
		    mListFragmentAttached = null;
		    mServerListFragment = null;
		    mServerDetailsFragment = null;
		    mVolumeListFragment = null;
		    mVolumeDetailsFragment = null;
		    mFlavorListFragment = null;
		    mFlavorDetailsFragment = null;
		    mImageListFragment = null;
		    mImageDetailsFragment = null;
		    mUserListFragment = null;
		    mUserDetailsFragment = null;
		    mTenantListFragment = null;
		    mTenantDetailsFragment = null;
		    
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

		    if (((OpenstackdroidApplication)getApplication()).isAdminUser) {
			    ActionBar.Tab flavorTab = actionBar.newTab().setText("Flavors");
			    ActionBar.Tab imagesTab = actionBar.newTab().setText("Images");
			    ActionBar.Tab usersTab = actionBar.newTab().setText("Users");
			    ActionBar.Tab tenantsTab = actionBar.newTab().setText("Projects");
			    
			    flavorTab.setTabListener(this);
			    imagesTab.setTabListener(this);
			    usersTab.setTabListener(this);
			    tenantsTab.setTabListener(this);
			    
			    actionBar.addTab(flavorTab);
			    actionBar.addTab(imagesTab);	
			    actionBar.addTab(usersTab);
			    actionBar.addTab(tenantsTab);
		    }    
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
		case 2:
			tabClass = FlavorListFragment.class;
			break;
		case 3:
			tabClass = ImageListFragment.class;
			break;
		case 4:
			tabClass = UserListFragment.class;
			break;
		case 5: 
			tabClass = TenantListFragment.class;
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
        	} else if (tabClass == FlavorListFragment.class) {
        		mFlavorListFragment = mListFragmentAttached;
        	} else if (tabClass == ImageListFragment.class) {
        		mImageListFragment = mListFragmentAttached;
        	} else if (tabClass == UserListFragment.class) {
        		mUserListFragment = mListFragmentAttached;
        	} else if (tabClass == TenantListFragment.class) {
        		mTenantListFragment = mListFragmentAttached;
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
        	} else if (tabClass == FlavorListFragment.class) {
        		if (mFlavorListFragment != null) {
        			mListFragmentAttached = mFlavorListFragment;
        			ft.attach(mListFragmentAttached);
        		} else {
        			mListFragmentAttached = Fragment.instantiate(this, tabClass.getName());
        			mFlavorListFragment = mListFragmentAttached;
        			ft.add(R.id.items_list, mListFragmentAttached);
        		} 
        	} else if (tabClass == ImageListFragment.class) {
        		if (mImageListFragment != null) {
        			mListFragmentAttached = mImageListFragment;
        			ft.attach(mListFragmentAttached);
        		} else {
        			mListFragmentAttached = Fragment.instantiate(this, tabClass.getName());
        			mImageListFragment = mListFragmentAttached;
        			ft.add(R.id.items_list, mListFragmentAttached);
        		}        		
        	} else if (tabClass == UserListFragment.class) {
        		if (mUserListFragment != null) {
        			mListFragmentAttached = mUserListFragment;
        			ft.attach(mListFragmentAttached);
        		} else {
        			mListFragmentAttached = Fragment.instantiate(this, tabClass.getName());
        			mUserListFragment = mListFragmentAttached;
        			ft.add(R.id.items_list, mListFragmentAttached);
        		}
        	} else if (tabClass == TenantListFragment.class) {
        		if (mTenantListFragment != null) {
        			mListFragmentAttached = mTenantListFragment;
        			ft.attach(mListFragmentAttached);
        		} else {
        			mListFragmentAttached = Fragment.instantiate(this, tabClass.getName());
        			mTenantListFragment = mListFragmentAttached;
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

	public List<ServerModel> getServers() {
		return servers;
	}

	public void setServers(List<ServerModel> servers) {
		this.servers = servers;
	}

	public List<VolumeModel> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<VolumeModel> volumes) {
		this.volumes = volumes;
	}

	public List<ImageModel> getImages() {
		return images;
	}

	public void setImages(List<ImageModel> images) {
		this.images = images;
	}

	public Fragment getmImageListFragment() {
		return mImageListFragment;
	}

	public void setmImageListFragment(Fragment mImageListFragment) {
		this.mImageListFragment = mImageListFragment;
	}

	public Fragment getmImageDetailsFragment() {
		return mImageDetailsFragment;
	}

	public void setmImageDetailsFragment(Fragment mImageDetailsFragment) {
		this.mImageDetailsFragment = mImageDetailsFragment;
	}

	public List<FlavorModel> getFlavors() {
		return flavors;
	}

	public void setFlavors(List<FlavorModel> flavors) {
		this.flavors = flavors;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	public List<TenantModel> getTenants() {
		return tenants;
	}

	public void setTenants(List<TenantModel> tenants) {
		this.tenants = tenants;
	}

	public Fragment getmFlavorListFragment() {
		return mFlavorListFragment;
	}

	public void setmFlavorListFragment(Fragment mFlavorListFragment) {
		this.mFlavorListFragment = mFlavorListFragment;
	}

	public Fragment getmFlavorDetailsFragment() {
		return mFlavorDetailsFragment;
	}

	public void setmFlavorDetailsFragment(Fragment mFlavorDetailsFragment) {
		this.mFlavorDetailsFragment = mFlavorDetailsFragment;
	}

	public Fragment getmListFragmentAttached() {
		return mListFragmentAttached;
	}

	public void setmListFragmentAttached(Fragment mListFragmentAttached) {
		this.mListFragmentAttached = mListFragmentAttached;
	}

	public Fragment getmServerListFragment() {
		return mServerListFragment;
	}

	public void setmServerListFragment(Fragment mServerListFragment) {
		this.mServerListFragment = mServerListFragment;
	}

	public Fragment getmVolumeListFragment() {
		return mVolumeListFragment;
	}

	public void setmVolumeListFragment(Fragment mVolumeListFragment) {
		this.mVolumeListFragment = mVolumeListFragment;
	}

	public Fragment getmUserListFragment() {
		return mUserListFragment;
	}

	public void setmUserListFragment(Fragment mUserListFragment) {
		this.mUserListFragment = mUserListFragment;
	}

	public Fragment getmUserDetailsFragment() {
		return mUserDetailsFragment;
	}

	public void setmUserDetailsFragment(Fragment mUserDetailsFragment) {
		this.mUserDetailsFragment = mUserDetailsFragment;
	}

	public Fragment getmTenantListFragment() {
		return mTenantListFragment;
	}

	public void setmTenantListFragment(Fragment mTenantListFragment) {
		this.mTenantListFragment = mTenantListFragment;
	}

	public Fragment getmTenantDetailsFragment() {
		return mTenantDetailsFragment;
	}

	public void setmTenantDetailsFragment(Fragment mTenantDetailsFragment) {
		this.mTenantDetailsFragment = mTenantDetailsFragment;
	}
	 
}
