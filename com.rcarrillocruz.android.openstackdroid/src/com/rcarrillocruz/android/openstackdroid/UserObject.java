package com.rcarrillocruz.android.openstackdroid;

import java.util.List;

public class UserObject {
	private String id;
	private String username;
	private String name;
	private List<RoleObject> roles;
	private List<String> roles_links;
	
	public UserObject() {
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

	public List<String> getRoles_links() {
		return roles_links;
	}

	public void setRoles_links(List<String> roles_links) {
		this.roles_links = roles_links;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RoleObject> getRoles() {
		return roles;
	}
	
	public void setRoles(List<RoleObject> roles) {
		this.roles = roles;
	}

}
