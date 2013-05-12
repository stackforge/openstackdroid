package com.rcarrillocruz.android.openstackdroid;

import java.util.Iterator;
import java.util.List;

import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Loader;
import android.database.Cursor;
import android.app.LoaderManager;
import android.content.CursorLoader;
import com.google.gson.Gson;
import com.rcarrillocruz.android.openstackdroid.CloudControllerResultReceiver.Receiver;
import com.rcarrillocruz.android.openstackdroid.json.identity.EndpointObject;
import com.rcarrillocruz.android.openstackdroid.json.identity.GetTokenResponse;
import com.rcarrillocruz.android.openstackdroid.json.identity.RoleObject;
import com.rcarrillocruz.android.openstackdroid.json.identity.ServiceCatalogObject;

public class LoginActivity extends ListActivity implements Receiver, LoaderManager.LoaderCallbacks<Cursor> {
	private static final String COMPUTE_ENDPOINT = "compute";
	private static final String VOLUME_ENDPOINT = "volume";
	private static final String IDENTITY_ENDPOINT = "identity";
	private static final String IMAGE_ENDPOINT = "image";
	private CloudControllerResultReceiver mReceiver;
	private SimpleCursorAdapter adapter;
	protected Object mActionMode;
	private long selectedItemId = -1;
	private ProgressDialog progressDialog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
              
        final ListView lv = getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        lv.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            	if (mActionMode != null) {
            		return false;
            	}
            	selectedItemId = id;
            	
            	mActionMode = LoginActivity.this.startActionMode(mActionModeCallback);
            	view.setSelected(true);
            	lv.setItemChecked(position, true);
            	
            	return true;
            }
        });
        
        mReceiver = new CloudControllerResultReceiver(new Handler());
        mReceiver.setReceiver(this);
        
        populateProfiles();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

    	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    		MenuInflater inflater = mode.getMenuInflater();
    		inflater.inflate(R.menu.login_contextual, menu);
    		return true;
    	}

    	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    		return false; // Return false if nothing is done
    	}

    	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
    		switch (item.getItemId()) {
    		case R.id.edit:
    			editConnectionProfile();
    			mode.finish();
    			return true;
    		case R.id.clone:
    			cloneConnectionProfile();
    			mode.finish();
    			return true;
    		case R.id.delete:
    			deleteConnectionProfile();
    			mode.finish();
    		default:
    			return false;
    		}
    	}

		public void onDestroyActionMode(ActionMode mode) {
    		mActionMode = null;
    		selectedItemId = -1;
    	}
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if(mActionMode == null) {
            Uri connectionProfileUri = Uri.parse(ConnectionProfileContentProvider.CONTENT_URI + "/" + id);
        	
            String[] projection = {ConnectionProfileTable.COLUMN_ENDPOINT, ConnectionProfileTable.COLUMN_USERNAME, 
        						   ConnectionProfileTable.COLUMN_PASSWORD, ConnectionProfileTable.COLUMN_TENANT_ID};
        	
        	Cursor cursor = getContentResolver().query(connectionProfileUri, projection, null, null, null);
        	cursor.moveToFirst();
        	String endpoint = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_ENDPOINT));
        	String username = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_USERNAME));
        	String password = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_PASSWORD));
        	String tenantId = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_TENANT_ID));
        	cursor.close();
        	
            Intent serviceIntent = new Intent(this, CloudControllerService.class);
            serviceIntent.setData(Uri.parse(endpoint));
            serviceIntent.putExtra(CloudControllerService.OPERATION, CloudControllerService.GET_TOKEN_OPERATION);
            serviceIntent.putExtra(CloudControllerService.TOKEN, (String)null);
            serviceIntent.putExtra(CloudControllerService.TENANT, tenantId);
            serviceIntent.putExtra(CloudControllerService.RECEIVER, mReceiver);
            
            Bundle params = new Bundle();
            params.putString("username", username);
            params.putString("password", password);
            
            serviceIntent.putExtra(CloudControllerService.PARAMS, params);
            
            startService(serviceIntent);
            progressDialog = ProgressDialog.show(this, null, "Authenticating...");
            
        } else {
        	selectedItemId = id;
        }
    }
    
    

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.add_profile:
			addConnectionProfile();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		progressDialog.dismiss();
		
		if (resultCode == 200) {
			Gson gson = new Gson();
			GetTokenResponse gtr = gson.fromJson(resultData.getString(CloudControllerService.OPERATION_RESULTS), GetTokenResponse.class);
			OpenstackdroidApplication application = (OpenstackdroidApplication) getApplication();
			List<ServiceCatalogObject> sc = gtr.getAccess().getServiceCatalog();
			
			application.setAdminUser(false);
			Iterator<RoleObject> it = gtr.getAccess().getUser().getRoles().iterator();
			
			while (it.hasNext()) {
				if (it.next().getName().equals("admin"))
					application.setAdminUser(true);
			}
			
			application.setToken(gtr.getAccess().getToken().getId());
			application.setTenantId(gtr.getAccess().getToken().getTenant().getId());
			application.setComputeEndpoint(getEndpointByType(sc, COMPUTE_ENDPOINT).getPublicURL());
			application.setVolumeEndpoint(getEndpointByType(sc, VOLUME_ENDPOINT).getPublicURL());
			
			EndpointObject ieo = getEndpointByType(sc, IDENTITY_ENDPOINT);
			application.setIdentityEndpoint(ieo.getPublicURL());
			application.setIdentityAdminEndpoint(ieo.getAdminURL());
			
			application.setImageEndpoint(getEndpointByType(sc, IMAGE_ENDPOINT).getPublicURL());
			
            Intent browserIntent = new Intent(this, CloudBrowserActivity.class);
            startActivity(browserIntent);
		} else {
			Toast.makeText(this, "Error " + resultCode + ": " + resultData.getString(CloudControllerService.OPERATION_RESULTS) , Toast.LENGTH_LONG).show();
		}
	}

	private EndpointObject getEndpointByType(List<ServiceCatalogObject> sc, String type) {
		// TODO Auto-generated method stub
		EndpointObject eo = null;
		boolean found = false;
		ServiceCatalogObject item = null;
		
		Iterator<ServiceCatalogObject> it = sc.iterator();
		
		while (it.hasNext() && !found) {
			item = it.next();
			
			if (item.getType().equals(type)) {
				eo = item.getEndpoints().get(0);
				found = true;
			}
				
		}
		
		return eo;
	}

	private void addConnectionProfile() {
		Intent i = new Intent(this, ConnectionProfileActivity.class);
		startActivity(i);
	}

	private void deleteConnectionProfile() {
		Uri connectionProfileUri = Uri.parse(ConnectionProfileContentProvider.CONTENT_URI + "/" + selectedItemId);
		getContentResolver().delete(connectionProfileUri, null, null);
	}
	
	private void editConnectionProfile() {
		Intent i = new Intent(this, ConnectionProfileActivity.class);
		Uri connectionProfileUri = Uri.parse(ConnectionProfileContentProvider.CONTENT_URI + "/" + selectedItemId);
		i.putExtra(ConnectionProfileContentProvider.CONTENT_ITEM_TYPE, connectionProfileUri);
		
		startActivity(i);
	}
	
	private void cloneConnectionProfile() {
		Uri connectionProfileUri = Uri.parse(ConnectionProfileContentProvider.CONTENT_URI + "/" + selectedItemId);
		
		String[] projection = {ConnectionProfileTable.COLUMN_PROFILE_NAME, ConnectionProfileTable.COLUMN_ENDPOINT, 
							   ConnectionProfileTable.COLUMN_USERNAME, ConnectionProfileTable.COLUMN_PASSWORD, 
							   ConnectionProfileTable.COLUMN_TENANT_ID};
		
		Cursor cursor = getContentResolver().query(connectionProfileUri, projection, null, null, null);
    	cursor.moveToFirst();
    	
    	String profileName = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_PROFILE_NAME));
    	String endpoint = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_ENDPOINT));
    	String username = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_USERNAME));
    	String password = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_PASSWORD));
    	String tenantId = cursor.getString(cursor.getColumnIndex(ConnectionProfileTable.COLUMN_TENANT_ID));
    	
		ContentValues values = new ContentValues();
		values.put(ConnectionProfileTable.COLUMN_PROFILE_NAME, profileName);
		values.put(ConnectionProfileTable.COLUMN_ENDPOINT, endpoint);
		values.put(ConnectionProfileTable.COLUMN_USERNAME, username);
		values.put(ConnectionProfileTable.COLUMN_PASSWORD, password);
		values.put(ConnectionProfileTable.COLUMN_TENANT_ID, tenantId);
    	
    	getContentResolver().insert(ConnectionProfileContentProvider.CONTENT_URI, values);
    	
    	cursor.close();
	}
	
	private void populateProfiles() {
	    String[] from = new String[] { ConnectionProfileTable.COLUMN_PROFILE_NAME };
	    int[] to = new int[] { R.id.profile_name };

	    getLoaderManager().initLoader(0, null, this);
	    adapter = new SimpleCursorAdapter(this, R.layout.profile_list_item, null, from, to, 0);

	    setListAdapter(adapter);
	}
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		String[] projection = { ConnectionProfileTable.COLUMN_ID, ConnectionProfileTable.COLUMN_PROFILE_NAME };
	    CursorLoader cursorLoader = new CursorLoader(this, ConnectionProfileContentProvider.CONTENT_URI, projection, null, null, null);
	    return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}
}
