package com.rcarrillocruz.android.openstackdroid.json.compute;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FlavorDetailsObject {
	private String id;
	private String name;
	private int vcpus;
	private int ram;
	private int disk;
	private String swap;
	@SerializedName("OS-FLV-DISABLED:disabled")
	private boolean disabled;
	@SerializedName("OS-FLV-EXT-DATA:ephemeral")
	private int ephemeral;
	@SerializedName("os-flavor-access:is_public")
	private boolean isPublic;
	private double rxtx_factor;
	private List<LinkObject> links;
	
	public FlavorDetailsObject() {
		super();
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

	public int getVcpus() {
		return vcpus;
	}

	public void setVcpus(int vcpus) {
		this.vcpus = vcpus;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getDisk() {
		return disk;
	}

	public void setDisk(int disk) {
		this.disk = disk;
	}

	public String getSwap() {
		return swap;
	}

	public void setSwap(String swap) {
		this.swap = swap;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public int getEphemeral() {
		return ephemeral;
	}

	public void setEphemeral(int ephemeral) {
		this.ephemeral = ephemeral;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public double getRxtx_factor() {
		return rxtx_factor;
	}

	public void setRxtx_factor(double rxtx_factor) {
		this.rxtx_factor = rxtx_factor;
	}

	public List<LinkObject> getLinks() {
		return links;
	}

	public void setLinks(List<LinkObject> links) {
		this.links = links;
	}
}
