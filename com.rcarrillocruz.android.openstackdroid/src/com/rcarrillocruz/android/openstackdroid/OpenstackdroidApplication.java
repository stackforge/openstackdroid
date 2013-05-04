package com.rcarrillocruz.android.openstackdroid;

import android.app.Application;

public class OpenstackdroidApplication extends Application {
	private String token;
	private String computeEndpoint;
	private String volumeEndpoint;
	private String identityEndpoint;
	private String imageEndpoint;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getImageEndpoint() {
		return imageEndpoint;
	}
	public void setImageEndpoint(String imageEndpoint) {
		this.imageEndpoint = imageEndpoint;
	}
	
}
