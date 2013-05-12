package com.rcarrillocruz.android.openstackdroid;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import android.net.Uri;
import android.os.Bundle;

public class GetUsersOperation implements ApiOperation {
	private static final String urlTail = "/users";
	
	public GetUsersOperation() {
		super();	
	}
	
	@Override
	public HttpRequestBase invoke(Uri endpoint, String token, String tenantId,
			Bundle params) {
		// TODO Auto-generated method stub
		HttpGet httpGet = new HttpGet(endpoint.toString()+urlTail);
		httpGet.setHeader("X-Auth-Token", token);
		
		return httpGet;
	}
}
