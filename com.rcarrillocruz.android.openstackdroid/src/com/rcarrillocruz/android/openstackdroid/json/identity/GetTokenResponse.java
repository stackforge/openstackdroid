package com.rcarrillocruz.android.openstackdroid.json.identity;

public class GetTokenResponse {
	private AccessObject access;
	
	public GetTokenResponse() {
		super();
	}

	public AccessObject getAccess() {
		return access;
	}

	public void setAccess(AccessObject access) {
		this.access = access;
	}
}
