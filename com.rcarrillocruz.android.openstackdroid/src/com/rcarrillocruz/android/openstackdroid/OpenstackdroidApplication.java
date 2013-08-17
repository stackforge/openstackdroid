package com.rcarrillocruz.android.openstackdroid;

import android.app.Application;

public class OpenstackdroidApplication extends Application {
	private String token;
	private String tenantId;
	public boolean isAdminUser;
	private String computeEndpoint;
	private String volumeEndpoint;
	private String identityEndpoint;
	private String identityAdminEndpoint;
	private String imageEndpoint;
	
	public void onCreate() {
		super.onCreate();
	}
	
	public void onTerminate() {
		super.onTerminate();
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public boolean isAdminUser() {
		return isAdminUser;
	}
	
	public void setAdminUser(boolean isAdminUser) {
		this.isAdminUser = isAdminUser;
	}
	
	public String getComputeEndpoint() {
		return computeEndpoint;
	}
	
	public void setComputeEndpoint(String computeEndpoint) {
		this.computeEndpoint = computeEndpoint;
	}
	
	public String getVolumeEndpoint() {
		return volumeEndpoint;
	}
	
	public void setVolumeEndpoint(String volumeEndpoint) {
		this.volumeEndpoint = volumeEndpoint;
	}
	
	public String getIdentityEndpoint() {
		return identityEndpoint;
	}
	
	public void setIdentityEndpoint(String identityEndpoint) {
		this.identityEndpoint = identityEndpoint;
	}
	
	public String getIdentityAdminEndpoint() {
		return identityAdminEndpoint;
	}
	
	public void setIdentityAdminEndpoint(String identityAdminEndpoint) {
		this.identityAdminEndpoint = identityAdminEndpoint;
	}
	
	public String getImageEndpoint() {
		return imageEndpoint;
	}
	
	public void setImageEndpoint(String imageEndpoint) {
		this.imageEndpoint = imageEndpoint;
	}
	
}
