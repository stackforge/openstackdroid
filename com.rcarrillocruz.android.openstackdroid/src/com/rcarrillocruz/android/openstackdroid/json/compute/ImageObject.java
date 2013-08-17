package com.rcarrillocruz.android.openstackdroid.json.compute;

import java.util.List;

public class ImageObject {
	private String id;
	private List<ImageLinkObject> links;
	
	public ImageObject() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ImageLinkObject> getLinks() {
		return links;
	}

	public void setLinks(List<ImageLinkObject> links) {
		this.links = links;
	}
}
