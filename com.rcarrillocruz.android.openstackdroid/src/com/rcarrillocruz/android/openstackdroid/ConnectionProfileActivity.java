package com.rcarrillocruz.android.openstackdroid;

import com.rcarrillocruz.android.openstackdroid.R;
import com.rcarrillocruz.android.openstackdroid.R.id;
import com.rcarrillocruz.android.openstackdroid.R.layout;
import com.rcarrillocruz.android.openstackdroid.R.menu;
import com.rcarrillocruz.android.openstackdroid.db.connectionprofile.ConnectionProfileContentProvider;
import com.rcarrillocruz.android.openstackdroid.db.connectionprofile.ConnectionProfileTable;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connection_profile_activity);
		
		mProfileName = (EditText) findViewById(R.id.profile_name_edittext);
		mEndpoint = (EditText) findViewById(R.id.endpoint_edittext);
		mUsername = (EditText) findViewById(R.id.username_edittext);
		mPassword = (EditText) findViewById(R.id.password_edittext);
		mTenantId = (EditText) findViewById(R.id.tenant_id_edittext);
		
		Bundle extras = getIntent().getExtras();
		
		if (extras == null) {
			connectionProfileUri = null;			
		} else {
			connectionProfileUri = extras.getParcelable(ConnectionProfileContentProvider.CONTENT_ITEM_TYPE);
			populateForm(connectionProfileUri);
		}
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
		
		if (connectionProfileUri == null) {
			connectionProfileUri = getContentResolver().insert(ConnectionProfileContentProvider.CONTENT_URI, values);			
		} else {
			getContentResolver().update(connectionProfileUri, values, null, null);
		}
		
		finish();
	}

    private void populateForm(Uri uri) {
    	String[] projection = {ConnectionProfileTable.COLUMN_PROFILE_NAME, ConnectionProfileTable.COLUMN_ENDPOINT, 
    						   ConnectionProfileTable.COLUMN_USERNAME, ConnectionProfileTable.COLUMN_PASSWORD,
    						   ConnectionProfileTable.COLUMN_TENANT_ID};
    	
    	Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
    	cursor.moveToFirst();
    	
    	String profileName = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_PROFILE_NAME));
    	String endpoint = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_ENDPOINT));
    	String username = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_USERNAME));
    	String password = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_PASSWORD));
    	String tenantId = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_TENANT_ID));
    	
    	mProfileName.setText(profileName);
    	mEndpoint.setText(endpoint);
    	mUsername.setText(username);
    	mPassword.setText(password);
    	mTenantId.setText(tenantId);
    	
    	cursor.close();
	}
}
