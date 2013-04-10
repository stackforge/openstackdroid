package com.rcarrillocruz.android.openstackdroid;

public class ConnectionProfile {
	
	private String profileName;
	private String username;
	private String password;
	private String tenantId;
	private String connectionURL;
	
	public ConnectionProfile(String profileName, String username, String password, String tenantId,
			String connectionURL) {
		super();
		this.profileName = profileName;
		this.username = username;
		this.password = password;
		this.tenantId = tenantId;
		this.connectionURL = connectionURL;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
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

	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}
	
	
}
