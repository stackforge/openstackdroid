package com.rcarrillocruz.android.openstackdroid;

import java.util.List;

public class ServiceCatalogObject {
	private List<EndpointObject> endpoints;
	private String name;
	private String type;
	private List<String> endpoints_links;
	
	public ServiceCatalogObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<EndpointObject> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<EndpointObject> endpoints) {
		this.endpoints = endpoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getEndpoints_links() {
		return endpoints_links;
	}

	public void setEndpoints_links(List<String> endpoints_links) {
		this.endpoints_links = endpoints_links;
	}



}
