package com.rcarrillocruz.android.openstackdroid.operations.image;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import com.rcarrillocruz.android.openstackdroid.operations.ApiOperation;

import android.net.Uri;
import android.os.Bundle;

public class GetImagesOperation implements ApiOperation {
	private static final String urlTail = "/v2/images";
	public HttpRequestBase invoke(Uri endpoint, String token, String tenantId,
			Bundle params) {
		HttpGet httpGet = new HttpGet(endpoint.toString()+urlTail);
		httpGet.setHeader("X-Auth-Token", token);
		
		return httpGet;
	}

}
