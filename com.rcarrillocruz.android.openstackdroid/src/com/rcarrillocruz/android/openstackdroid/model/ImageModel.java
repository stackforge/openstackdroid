package com.rcarrillocruz.android.openstackdroid.model;

public class ImageModel {
	private String id;
	private String name;
	private String status;
	private String visibility;
	private String checksum;
	private String created_at;
	private String updated_at;
	private int size;
	private String container_format;
	private String disk_format;
	
	public ImageModel() {
		super();
	}

	public ImageModel(String id, String name, String status, String visibility,
			String checksum, String created_at, String updated_at, int size,
			String container_format, String disk_format) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.visibility = visibility;
		this.checksum = checksum;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.size = size;
		this.container_format = container_format;
		this.disk_format = disk_format;
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

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getContainer_format() {
		return container_format;
	}

	public void setContainer_format(String container_format) {
		this.container_format = container_format;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisk_format() {
		return disk_format;
	}

	public void setDisk_format(String disk_format) {
		this.disk_format = disk_format;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String toString() {
		return this.name;
	}
}
