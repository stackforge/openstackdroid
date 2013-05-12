package com.rcarrillocruz.android.openstackdroid;

import java.util.List;

public class ServerModel {
	private String id;
	private String name;
	private String status;
	private String created;
	private String updated;
	List<IPAddressModel> privateAddresses;
	List<IPAddressModel> publicAddresses;
	private String image;
	private String flavor;
	
	public ServerModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServerModel(String id, String name) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.name = name;
	}
	
	public ServerModel(String id, String name, String status, String created, String updated, 
			List<IPAddressModel> privateAddresses,
			List<IPAddressModel> publicAddresses,
			String image, String flavor) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.created = created;
		this.updated = updated;
		this.privateAddresses = privateAddresses;
		this.publicAddresses = publicAddresses;
		this.image = image;
		this.flavor = flavor;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public List<IPAddressModel> getPrivateAddresses() {
		return privateAddresses;
	}

	public void setPrivateAddresses(List<IPAddressModel> privateAddresses) {
		this.privateAddresses = privateAddresses;
	}

	public List<IPAddressModel> getPublicAddresses() {
		return publicAddresses;
	}

	public void setPublicAddresses(List<IPAddressModel> publicAddresses) {
		this.publicAddresses = publicAddresses;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String toString() {
		return getName();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return (this.getId().equals(((ServerModel)o).getId()));
	}

}
