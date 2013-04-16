package com.rcarrillocruz.android.openstackdroid;


public class AuthObject {
	private PasswordCredentialsObject passwordCredentials;
	private String tenantId;

	public AuthObject(PasswordCredentialsObject passwordCredentials,
			String tenantId) {
		super();
		this.passwordCredentials = passwordCredentials;
		this.tenantId = tenantId;
	}

	public PasswordCredentialsObject getPasswordCredentials() {
		return passwordCredentials;
	}

	public void setPasswordCredentials(PasswordCredentialsObject passwordCredentials) {
		this.passwordCredentials = passwordCredentials;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}
