package com.rcarrillocruz.android.openstackdroid;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class VolumeDetailsObject {
	private List<VolumeAttachmentObject> attachments;
	private String availability_zone;
	private String bootable;
	private String created_at;
	private String display_description; 
    private String display_name; 
    private String id; 
    //private String metadata; 
    @SerializedName("os-vol-host-attr")
    private String os_vol_host_attr;
    @SerializedName("os-vol-tenant-attr")
    private String os_vol_tenant_attr;
    private float size; 
    private String snapshot_id; 
    private String source_volid; 
    private String status; 
    private String volume_type;
	
    public List<VolumeAttachmentObject> getAttachments() {
		return attachments;
	}
    
	public void setAttachments(List<VolumeAttachmentObject> attachments) {
		this.attachments = attachments;
	}
	
	public String getAvailability_zone() {
		return availability_zone;
	}
	
	public void setAvailability_zone(String availability_zone) {
		this.availability_zone = availability_zone;
	}
	
	public String getBootable() {
		return bootable;
	}
	
	public void setBootable(String bootable) {
		this.bootable = bootable;
	}
	
	public String getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public String getDisplay_description() {
		return display_description;
	}
	
	public void setDisplay_description(String display_description) {
		this.display_description = display_description;
	}
	
	public String getDisplay_name() {
		return display_name;
	}
	
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
/*	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}*/
	
	public String getOs_vol_host_attr() {
		return os_vol_host_attr;
	}
	
	public void setOs_vol_host_attr(String os_vol_host_attr) {
		this.os_vol_host_attr = os_vol_host_attr;
	}
	
	public String getOs_vol_tenant_attr() {
		return os_vol_tenant_attr;
	}
	
	public void setOs_vol_tenant_attr(String os_vol_tenant_attr) {
		this.os_vol_tenant_attr = os_vol_tenant_attr;
	}
	
	public float getSize() {
		return size;
	}
	
	public void setSize(float size) {
		this.size = size;
	}
	
	public String getSnapshot_id() {
		return snapshot_id;
	}
	
	public void setSnapshot_id(String snapshot_id) {
		this.snapshot_id = snapshot_id;
	}
	
	public String getSource_volid() {
		return source_volid;
	}
	
	public void setSource_volid(String source_volid) {
		this.source_volid = source_volid;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getVolume_type() {
		return volume_type;
	}
	
	public void setVolume_type(String volume_type) {
		this.volume_type = volume_type;
	}
}
