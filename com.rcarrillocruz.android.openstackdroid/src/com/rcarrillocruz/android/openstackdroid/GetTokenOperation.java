package com.rcarrillocruz.android.openstackdroid;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import android.net.Uri;
import android.os.Bundle;

import com.google.gson.Gson;

public class GetTokenOperation implements ApiOperation {
	private Gson gson;
	private static final String urlTail = "/v2.0/tokens";
	
	public GetTokenOperation() {
		super();	
		gson = new Gson();
	}
	
	@Override
	public HttpRequestBase invoke(Uri endpoint, String token, String tenantId,
			Bundle params) {
		// TODO Auto-generated method stub
		HttpPost httpPost = new HttpPost(endpoint.toString()+urlTail);
		httpPost.setHeader("Content-type", "application/json");
		
		String username = params.getString("username");
		String password = params.getString("password");
		
		GetTokenRequest request = new GetTokenRequest(new AuthObject(new PasswordCredentialsObject(params.getString("username"), params.getString("password")), tenantId));
		
		StringEntity se = null;
		try {
			se = new StringEntity(gson.toJson(request));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		httpPost.setEntity(se);
		
		return httpPost;
	}
}
