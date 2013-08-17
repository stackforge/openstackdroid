package com.rcarrillocruz.android.openstackdroid;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.Toast;

import com.rcarrillocruz.android.openstackdroid.operations.ApiOperation;

public class CloudControllerService extends IntentService {
	public static final String TAG = CloudControllerResultReceiver.class.getName();
	public static final String GET_TOKEN_OPERATION = "com.rcarrillocruz.android.openstackdroid.operations.identity.GetTokenOperation";
	public static final String GET_SERVERS_OPERATION = "com.rcarrillocruz.android.openstackdroid.operations.compute.GetServersOperation";
	public static final String GET_VOLUMES_OPERATION = "com.rcarrillocruz.android.openstackdroid.operations.volume.GetVolumesOperation";
	public static final String GET_IMAGES_OPERATION = "com.rcarrillocruz.android.openstackdroid.operations.image.GetImagesOperation";
	public static final String GET_FLAVORS_OPERATION = "com.rcarrillocruz.android.openstackdroid.operations.compute.GetFlavorsOperation";
	public static final String GET_USERS_OPERATION = "com.rcarrillocruz.android.openstackdroid.operations.identity.GetUsersOperation";
	public static final String GET_TENANTS_OPERATION = "com.rcarrillocruz.android.openstackdroid.operations.identity.GetTenantsOperation";
	
	public static final String OPERATION = "com.rcarrillocruz.android.openstackdroid.OPERATION";
	public static final String TOKEN = "com.rcarrillocruz.android.openstackdroid.TOKEN";
	public static final String TENANT = "com.rcarrillocruz.android.openstackdroid.TENANT";
	public static final String PARAMS = "com.rcarrillocruz.android.openstackdroid.PARAMS";
	public static final String RECEIVER = "com.rcarrillocruz.android.openstackdroid.RECEIVER";
	public static final String OPERATION_RESULTS = "com.rcarrillocruz.android.openstackdroid.RESULTS";
	
	public CloudControllerService() {				
		super("CloudControllerService");
	}

	protected void onHandleIntent(Intent intent) {
		Uri data = intent.getData();
		Bundle extras = intent.getExtras();
		
		String operation = extras.getString(OPERATION);
		String token = extras.getString(TOKEN);
		String tenantId = extras.getString(TENANT);
		ResultReceiver receiver = extras.getParcelable(RECEIVER);
		Bundle params = extras.getParcelable(PARAMS);

		ApiOperation apiOperation = getOperationInstance(operation);

		HttpRequestBase request = apiOperation.invoke(data, token, tenantId, params);

		HttpResponse response = null;
		response = executeOperation(request);
		
		returnResultstoReceiver(operation, response, receiver);
	}

	private ApiOperation getOperationInstance(String operation) {
		ApiOperation apiOperation = null;
		
		try {
			apiOperation = (ApiOperation) Class.forName(operation).newInstance();
		} catch (InstantiationException e) {
			Log.e(TAG,Log.getStackTraceString(e)); 
			Toast.makeText(getApplicationContext(), "Instantiation exception, check the Android log!", Toast.LENGTH_LONG).show();
		} catch (IllegalAccessException e) {
			Log.e(TAG,Log.getStackTraceString(e)); 
			Toast.makeText(getApplicationContext(), "Illegal access exception, check the Android log!", Toast.LENGTH_LONG).show();
		} catch (ClassNotFoundException e) {
			Log.e(TAG,Log.getStackTraceString(e)); 
			Toast.makeText(getApplicationContext(), "Class not found exception, check the Android log!", Toast.LENGTH_LONG).show();
		}
		
		return apiOperation;
	}

	private HttpResponse executeOperation(HttpRequestBase request) {
		HttpResponse response = null;
		HttpClient client = new DefaultHttpClient();
		
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			Log.e(TAG,Log.getStackTraceString(e)); 
			Toast.makeText(getApplicationContext(), "Client protocol exception, check the Android log!", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Log.e(TAG,Log.getStackTraceString(e)); 
			Toast.makeText(getApplicationContext(), "Input/output exception, check the Android log!", Toast.LENGTH_LONG).show();
		}
		return response;
	}

	private void returnResultstoReceiver(String operation, HttpResponse response,
			ResultReceiver receiver) {
		Bundle results = new Bundle();
		
		try {
			results.putString(OPERATION, operation);
			if (response != null) {
				results.putString(OPERATION_RESULTS, EntityUtils.toString(response.getEntity()));
				receiver.send(response.getStatusLine().getStatusCode(), results);
			} else {
				results.putString(OPERATION_RESULTS, "Connection to Openstack could not be established!");
				receiver.send(504, results);
			}
		} catch (ParseException e) {
			Log.e(TAG,Log.getStackTraceString(e));
			Toast.makeText(getApplicationContext(), "Parsing exception, check the Android log!", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Log.e(TAG,Log.getStackTraceString(e)); 
			Toast.makeText(getApplicationContext(), "Input/output exception, check the Android log!", Toast.LENGTH_LONG).show();
		}
	}
}
