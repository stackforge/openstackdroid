package com.rcarrillocruz.android.openstackdroid;

public class TokenObject {
	private String id;
	private String issued_at;
	private String expires;
	private TenantObject tenant;
	
	public TokenObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIssued_at() {
		return issued_at;
	}

	public void setIssued_at(String issued_at) {
		this.issued_at = issued_at;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public TenantObject getTenant() {
		return tenant;
	}

	public void setTenant(TenantObject tenant) {
		this.tenant = tenant;
	}
}
