package com.rcarrillocruz.android.openstackdroid.model;

public class FlavorModel {
	private String id;
	private String name;
	private int vcpus;
	private int ram;
	private int rootDisk;
	private String swapDisk;
	
	public FlavorModel(String id, String name, int vcpus, int ram,
			int rootDisk, String swapDisk) {
		super();
		this.id = id;
		this.name = name;
		this.vcpus = vcpus;
		this.ram = ram;
		this.rootDisk = rootDisk;
		this.swapDisk = swapDisk;
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

	public int getRootDisk() {
		return rootDisk;
	}

	public void setRootDisk(int rootDisk) {
		this.rootDisk = rootDisk;
	}

	public String getSwapDisk() {
		return swapDisk;
	}

	public void setSwapDisk(String swapDisk) {
		this.swapDisk = swapDisk;
	}
	
	public String toString() {
		return this.name;
	}
}
