package com.rcarrillocruz.android.openstackdroid;

import java.util.List;

public class ServerObject {
	private String id;
	private String name;
	private List<ServerLinkObject> links;
	
	public ServerObject() {
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

	public List<ServerLinkObject> getLinks() {
		return links;
	}

	public void setLinks(List<ServerLinkObject> links) {
		this.links = links;
	}

	
}
