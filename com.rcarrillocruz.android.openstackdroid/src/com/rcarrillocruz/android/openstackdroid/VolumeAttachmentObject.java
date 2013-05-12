package com.rcarrillocruz.android.openstackdroid;

public class VolumeAttachmentObject {
	private String id;
	private String device;
	private String server_id;
	private String volume_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getServer_id() {
		return server_id;
	}
	public void setServer_id(String server_id) {
		this.server_id = server_id;
	}
	public String getVolume_id() {
		return volume_id;
	}
	public void setVolume_id(String volume_id) {
		this.volume_id = volume_id;
	}
}
