package com.rcarrillocruz.android.openstackdroid.model;

public class UserModel {
	private String id;
	private String name;
	private boolean enabled;
	private String email;
	private String tenantId;
	
	public UserModel(String id, String name, boolean enabled, String email,
			String tenantId) {
		super();
		this.id = id;
		this.name = name;
		this.enabled = enabled;
		this.email = email;
		this.tenantId = tenantId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public String toString() {
		return this.name;
	}
}
