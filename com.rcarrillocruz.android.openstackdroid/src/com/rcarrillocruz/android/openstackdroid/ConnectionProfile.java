package com.rcarrillocruz.android.openstackdroid;

public class ConnectionProfile {
	
	private String profileName;
	private String username;
	private String password;
	private String tenantId;
	private String endpoint;
	
	public ConnectionProfile(String profileName, String endpoint, String username, String password, String tenantId) 
	{
		super();
		this.profileName = profileName;
		this.endpoint = endpoint;
		this.username = username;
		this.password = password;
		this.tenantId = tenantId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	
	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public String toString() {
		return getProfileName();
	}
}
