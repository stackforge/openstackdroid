package com.rcarrillocruz.android.openstackdroid.json.image;

import java.util.List;

public class GetImagesResponse {
	private List<ImageDetailsObject> images;
	
	public GetImagesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ImageDetailsObject> getImages() {
		return images;
	}

	public void setImages(List<ImageDetailsObject> images) {
		this.images = images;
	}
}
