package com.rcarrillocruz.android.openstackdroid.model;

public class VolumeModel {
	private String id;
	private String name;
	private String description;
	private String status;
	private double size;
	private String created_at;
	private String attached_to;
	
	public VolumeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VolumeModel(String id, String name, String description,
			String status, double size, String created_at, String attached_to) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.size = size;
		this.created_at = created_at;
		this.attached_to = attached_to;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getAttached_to() {
		return attached_to;
	}

	public void setAttached_to(String attached_to) {
		this.attached_to = attached_to;
	}	
	
	public String toString() {
		String res = null;
		
		res = this.getName();
		
		if (res == null)  
			res = this.id;
		
		return res;
	}
}
