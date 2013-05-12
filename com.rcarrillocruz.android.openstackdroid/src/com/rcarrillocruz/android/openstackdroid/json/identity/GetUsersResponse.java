package com.rcarrillocruz.android.openstackdroid.json.identity;

import java.util.List;

public class GetUsersResponse {
	private List<UserDetailsObject> users;

	public List<UserDetailsObject> getUsers() {
		return users;
	}

	public void setUsers(List<UserDetailsObject> users) {
		this.users = users;
	}

	public GetUsersResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
