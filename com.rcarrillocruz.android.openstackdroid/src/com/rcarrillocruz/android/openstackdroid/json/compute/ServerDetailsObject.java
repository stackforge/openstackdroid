package com.rcarrillocruz.android.openstackdroid;

public class ServerDetailsObject {
	private String id;
	private String name;
	private IPAddressesObject addresses;
	private String created;
	private String updated;
	private String status;
	private ImageObject image;
	private FlavorObject flavor;
	
	public ServerDetailsObject() {
		super();
		// TODO Auto-generated constructor stub
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

	public IPAddressesObject getAddresses() {
		return addresses;
	}

	public void setAddresses(IPAddressesObject addresses) {
		this.addresses = addresses;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ImageObject getImage() {
		return image;
	}

	public void setImage(ImageObject image) {
		this.image = image;
	}

	public FlavorObject getFlavor() {
		return flavor;
	}

	public void setFlavor(FlavorObject flavor) {
		this.flavor = flavor;
	}
}
