package com.rcarrillocruz.android.openstackdroid.json.volume;

import java.util.List;

public class GetVolumesResponse {
	private List<VolumeDetailsObject> volumes;
	
	public GetVolumesResponse() {
		super();
	}
	
	public List<VolumeDetailsObject> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<VolumeDetailsObject> volumes) {
		this.volumes = volumes;
	}
}
