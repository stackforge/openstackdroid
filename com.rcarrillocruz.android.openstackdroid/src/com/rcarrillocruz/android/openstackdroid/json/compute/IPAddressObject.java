package com.rcarrillocruz.android.openstackdroid.json.compute;

public class IPAddressObject {
	private String addr;
	private int version;
	
	public IPAddressObject() {
		super();
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
