package com.rcarrillocruz.android.openstackdroid;

import java.util.List;

public class GetVolumesResponse {
	private List<VolumeDetailsObject> volumes;
	
	public GetVolumesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<VolumeDetailsObject> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<VolumeDetailsObject> volumes) {
		this.volumes = volumes;
	}
}
