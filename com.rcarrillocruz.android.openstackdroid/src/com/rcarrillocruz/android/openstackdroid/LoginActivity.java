package com.rcarrillocruz.android.openstackdroid;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.view.Menu;

public class LoginActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ConnectionProfile[] profiles = { 
        		new ConnectionProfile("admin on Testing cloud", "admin", "opsware", "1","http://192.168.1.20:5000"+"/v2.0/tokens"),
        		new ConnectionProfile("devops on Production", "devops", "lol", "2", "http://192.168.1.20:5000"+"/v2.0/tokens")
        };		
        
        
        ArrayAdapter<ConnectionProfile> adapter = new ArrayAdapter<ConnectionProfile>(this, android.R.layout.simple_list_item_1, profiles);
        
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
