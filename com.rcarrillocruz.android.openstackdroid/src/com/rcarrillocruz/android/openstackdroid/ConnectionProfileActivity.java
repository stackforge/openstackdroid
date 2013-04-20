package com.rcarrillocruz.android.openstackdroid;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ConnectionProfileActivity extends Activity {
	private Uri connectionProfileUri;
	
	private EditText mProfileName;
	private EditText mEndpoint;
	private EditText mUsername;
	private EditText mPassword;
	private EditText mTenantId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connection_profile_activity);
		
		mProfileName = (EditText) findViewById(R.id.profile_name_edittext);
		mEndpoint = (EditText) findViewById(R.id.endpoint_edittext);
		mUsername = (EditText) findViewById(R.id.username_edittext);
		mPassword = (EditText) findViewById(R.id.password_edittext);
		mTenantId = (EditText) findViewById(R.id.tenant_id_edittext);
		
		connectionProfileUri = null;
	}

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.connection_profile_menu, menu);
        return true;
    }
    
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.save_profile:
			saveConnectionProfile();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	private void saveConnectionProfile() {
		// TODO Auto-generated method stub
		String profileName = (String) mProfileName.getText().toString();
		String endpoint = mEndpoint.getText().toString();
		String username = mUsername.getText().toString();
		String password = mPassword.getText().toString();
		String tenantId = mTenantId.getText().toString();
		
		ContentValues values = new ContentValues();
		values.put(ConnectionProfileTable.COLUMN_PROFILE_NAME, profileName);
		values.put(ConnectionProfileTable.COLUMN_ENDPOINT, endpoint);
		values.put(ConnectionProfileTable.COLUMN_USERNAME, username);
		values.put(ConnectionProfileTable.COLUMN_PASSWORD, password);
		values.put(ConnectionProfileTable.COLUMN_TENANT_ID, tenantId);
		
		connectionProfileUri = getContentResolver().insert(ConnectionProfileContentProvider.CONTENT_URI, values);
		
		finish();
	}
}
