package com.rcarrillocruz.android.openstackdroid.json.compute;

import java.util.List;

public class GetFlavorsResponse {
	private List<FlavorDetailsObject> flavors;

	public GetFlavorsResponse() {
		super();
	}

	public List<FlavorDetailsObject> getFlavors() {
		return flavors;
	}

	public void setFlavors(List<FlavorDetailsObject> flavors) {
		this.flavors = flavors;
	}
}
