package com.rcarrillocruz.android.openstackdroid.model;

public class IPAddressModel {
	private String addr;
	private int version;
	
	public IPAddressModel() {
		super();
	}

	public IPAddressModel(String addr, int version) {
		super();
		this.addr = addr;
		this.version = version;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
