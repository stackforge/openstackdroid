package com.rcarrillocruz.android.openstackdroid.json.identity;

import java.util.List;

public class AccessObject {
	private TokenObject token;
	private UserObject user;
	private List<ServiceCatalogObject> serviceCatalog;
	private MetadataObject metadata;

	public AccessObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TokenObject getToken() {
		return token;
	}

	public void setToken(TokenObject token) {
		this.token = token;
	}
	
	public UserObject getUser() {
		return user;
	}

	public void setUser(UserObject user) {
		this.user = user;
	}

	public List<ServiceCatalogObject> getServiceCatalog() {
		return serviceCatalog;
	}

	public void setServiceCatalog(List<ServiceCatalogObject> serviceCatalog) {
		this.serviceCatalog = serviceCatalog;
	}

	public MetadataObject getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataObject metadata) {
		this.metadata = metadata;
	}
}
