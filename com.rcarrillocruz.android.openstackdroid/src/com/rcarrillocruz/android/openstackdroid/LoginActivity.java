package com.rcarrillocruz.android.openstackdroid;

import android.net.Uri;
import android.os.Handler;

import android.app.ListActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.gson.Gson;
import com.rcarrillocruz.android.openstackdroid.CloudControllerResultReceiver.Receiver;

public class LoginActivity extends ListActivity implements Receiver {
	private CloudControllerResultReceiver mReceiver;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ConnectionProfile[] profiles = { 
        	new ConnectionProfile("admin on Testing cloud", "http://192.168.1.20:5000", "admin", "openstack", "793e8386d75f47b1bd078a3c0ddd9a49"),
        	new ConnectionProfile("devops on Production", "http://192.168.1.20:5000", "devops", "lol", "2")
        };		
        
        ArrayAdapter<ConnectionProfile> adapter = new ArrayAdapter<ConnectionProfile>(this, android.R.layout.simple_list_item_1, profiles);
        setListAdapter(adapter);
        
        mReceiver = new CloudControllerResultReceiver(new Handler());
        mReceiver.setReceiver(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
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


	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		// TODO Auto-generated method stub
		if (resultCode == 200) {
			Toast.makeText(this, resultData.getString("results"),Toast.LENGTH_LONG).show();
		}
	}
    
}
