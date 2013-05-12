package com.rcarrillocruz.android.openstackdroid.json.compute;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class IPAddressesObject {
	@SerializedName("private")
	private List<IPAddressObject> privateAddresses;
	
	@SerializedName("public")
	private List<IPAddressObject> publicAddresses;

	public IPAddressesObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<IPAddressObject> getPrivateAddresses() {
		return privateAddresses;
	}

	public void setPrivateAddresses(List<IPAddressObject> privateAddresses) {
		this.privateAddresses = privateAddresses;
	}

	public List<IPAddressObject> getPublicAddresses() {
		return publicAddresses;
	}

	public void setPublicAddresses(List<IPAddressObject> publicAddresses) {
		this.publicAddresses = publicAddresses;
	}
}
