package com.rcarrillocruz.android.openstackdroid.json.identity;

import java.util.List;

public class GetTenantsResponse {
	private List<TenantDetailsObject> tenants;

	public GetTenantsResponse() {
		super();
	}

	public List<TenantDetailsObject> getTenants() {
		return tenants;
	}

	public void setTenants(List<TenantDetailsObject> tenants) {
		this.tenants = tenants;
	}
}
