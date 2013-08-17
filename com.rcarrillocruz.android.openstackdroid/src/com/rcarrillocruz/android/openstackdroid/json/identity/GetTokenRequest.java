package com.rcarrillocruz.android.openstackdroid.json.identity;

public class GetTokenRequest {
	
	private AuthObject auth;

	public GetTokenRequest() {
		super();
	}

	public GetTokenRequest(AuthObject auth) {
		super();
		this.auth = auth;
	}

	public AuthObject getAuth() {
		return auth;
	}

	public void setAuth(AuthObject auth) {
		this.auth = auth;
	}

}
