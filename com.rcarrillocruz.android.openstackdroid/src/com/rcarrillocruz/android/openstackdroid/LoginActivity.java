package com.rcarrillocruz.android.openstackdroid;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.view.Menu;

public class LoginActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] profiles = new String[] {"admin on Development cloud", "devops on Testing"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profiles);
        
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
