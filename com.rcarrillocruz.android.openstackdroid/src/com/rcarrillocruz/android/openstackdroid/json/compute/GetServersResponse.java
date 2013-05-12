package com.rcarrillocruz.android.openstackdroid.json.compute;

import java.util.List;

public class GetServersResponse {
	List<ServerDetailsObject> servers;

	public GetServersResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ServerDetailsObject> getServers() {
		return servers;
	}

	public void setServers(List<ServerDetailsObject> servers) {
		this.servers = servers;
	}
}
