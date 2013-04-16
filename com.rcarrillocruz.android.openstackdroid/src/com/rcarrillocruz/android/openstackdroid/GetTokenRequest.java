package com.rcarrillocruz.android.openstackdroid;

public class GetTokenRequest {
	
	private AuthObject auth;

	public GetTokenRequest() {
		super();
		// TODO Auto-generated constructor stub
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
