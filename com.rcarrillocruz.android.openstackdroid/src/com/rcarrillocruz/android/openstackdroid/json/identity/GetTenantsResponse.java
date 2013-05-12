package com.rcarrillocruz.android.openstackdroid;

import java.util.List;

public class GetTenantsResponse {
	private List<TenantDetailsObject> tenants;

	public GetTenantsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<TenantDetailsObject> getTenants() {
		return tenants;
	}

	public void setTenants(List<TenantDetailsObject> tenants) {
		this.tenants = tenants;
	}
}
