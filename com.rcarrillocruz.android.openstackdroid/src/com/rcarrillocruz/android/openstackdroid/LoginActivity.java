package com.rcarrillocruz.android.openstackdroid;

import android.net.Uri;
import android.os.Handler;
import android.app.ListActivity;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.rcarrillocruz.android.openstackdroid.CloudControllerResultReceiver.Receiver;

public class LoginActivity extends ListActivity implements Receiver {
	private CloudControllerResultReceiver mReceiver;
	protected Object mActionMode;
	public int selectedItem = -1;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ConnectionProfile[] profiles = { 
        	new ConnectionProfile("admin on Testing cloud", "http://192.168.1.20:5000", "admin", "openstack", "793e8386d75f47b1bd078a3c0ddd9a49"),
        	new ConnectionProfile("devops on Production", "http://192.168.1.20:5000", "devops", "lol", "2")
        };		
        
        ArrayAdapter<ConnectionProfile> adapter = new ArrayAdapter<ConnectionProfile>(this, R.layout.profile_list_item, profiles);
        setListAdapter(adapter);
        
        final ListView lv = getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        lv.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            	if (mActionMode != null) {
            		return false;
            	}
            	selectedItem = position;

            	// Start the CAB using the ActionMode.Callback defined above
            	mActionMode = LoginActivity.this.startActionMode(mActionModeCallback);
            	view.setSelected(true);
            	lv.setItemChecked(position, true);
            	
            	return true;
            }
        });
        
        mReceiver = new CloudControllerResultReceiver(new Handler());
        mReceiver.setReceiver(this);
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

    	// Called when the action mode is created; startActionMode() was called
    	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    		// Inflate a menu resource providing context menu items
    		MenuInflater inflater = mode.getMenuInflater();
    		// Assumes that you have "contexual.xml" menu resources
    		inflater.inflate(R.menu.contextual, menu);
    		return true;
    	}

    	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    		return false; // Return false if nothing is done
    	}

    	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
    		switch (item.getItemId()) {
    		case R.id.edit:
    			mode.finish();
    			return true;
    		case R.id.clone:
    			mode.finish();
    			return true;
    		case R.id.delete:
    			mode.finish();
    		default:
    			return false;
    		}
    	}

    	public void onDestroyActionMode(ActionMode mode) {
    		mActionMode = null;
    		selectedItem = -1;
    	}
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if(mActionMode == null) {
        	ConnectionProfile profileClicked = (ConnectionProfile) getListAdapter().getItem(position);
            
            Intent intent = new Intent(this, CloudControllerService.class);
            intent.setData(Uri.parse("http://192.168.1.20:5000"));
            intent.putExtra(CloudControllerService.OPERATION, CloudControllerService.GET_TOKEN_OPERATION);
            intent.putExtra(CloudControllerService.RECEIVER, mReceiver);
            
            Bundle params = new Bundle();
            params.putString("username", profileClicked.getUsername());
            params.putString("password", profileClicked.getPassword());
            params.putString("tenantId", profileClicked.getTenantId());
            
            intent.putExtra(CloudControllerService.PARAMS, params);
            
            startService(intent);
        }      
    }
    
    

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.add_profile:
			addConnectionProfile();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		// TODO Auto-generated method stub
		if (resultCode == 200) {
			Toast.makeText(this, resultData.getString("results"),Toast.LENGTH_LONG).show();
		}
	}

	private void addConnectionProfile() {
		Intent i = new Intent(this, ConnectionProfileActivity.class);
		startActivity(i);
	}
}
