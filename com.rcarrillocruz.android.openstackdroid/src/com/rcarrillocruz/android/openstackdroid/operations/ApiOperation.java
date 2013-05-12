package com.rcarrillocruz.android.openstackdroid;

import org.apache.http.client.methods.HttpRequestBase;

import android.net.Uri;
import android.os.Bundle;

public interface ApiOperation {
	
	public HttpRequestBase invoke(Uri endpoint, String token, String tenantId, Bundle params); 

}
