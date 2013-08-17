package com.rcarrillocruz.android.openstackdroid;

import java.util.Iterator;
import java.util.List;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.rcarrillocruz.android.openstackdroid.json.identity.GetTenantsResponse;
import com.rcarrillocruz.android.openstackdroid.json.identity.TenantDetailsObject;
import com.rcarrillocruz.android.openstackdroid.model.TenantModel;

public class TenantListFragment extends CloudBrowserListFragment {

	List<TenantModel> tenants;
	private ArrayAdapter<TenantModel> adapter;
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tenants = ((CloudBrowserActivity) getActivity()).getTenants();
        endpoint = ((OpenstackdroidApplication) (getActivity().getApplication())).getIdentityAdminEndpoint();
		
        Intent serviceIntent = new Intent(getActivity(), CloudControllerService.class);
        serviceIntent.setData(Uri.parse(endpoint));
        serviceIntent.putExtra(CloudControllerService.OPERATION, CloudControllerService.GET_TENANTS_OPERATION);
        serviceIntent.putExtra(CloudControllerService.TOKEN, ((OpenstackdroidApplication) getActivity().getApplication()).getToken());
        serviceIntent.putExtra(CloudControllerService.TENANT, (String)null);
        serviceIntent.putExtra(CloudControllerService.RECEIVER, mReceiver); 
        Bundle params = new Bundle();         
        serviceIntent.putExtra(CloudControllerService.PARAMS, params);
                
        getActivity().startService(serviceIntent);
		
        adapter = new ArrayAdapter<TenantModel>(getActivity(), android.R.layout.simple_list_item_activated_1, tenants);
        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}
	
    public void onListItemClick(ListView l, View v, int position, long id) {
		mCurCheckPosition = position;
		getListView().setItemChecked(position, true);
		
		showDetails(position);    
    }

	protected void showDetails(int position) {
		TenantDetailsFragment tdf = (TenantDetailsFragment) ((CloudBrowserActivity) getActivity()).getmTenantDetailsFragment();
		
		if (tdf == null || tdf.getShownIndex() != position) 
			tdf = TenantDetailsFragment.newInstance(position);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.item_details, tdf);
        ft.commit();
	        
	    ((CloudBrowserActivity) getActivity()).showDetailsLayout();
	}

	public void onReceiveResult(int resultCode, Bundle resultData) {
		if (resultCode == 200) {
			String operation = resultData.getString(CloudControllerService.OPERATION);
			
			if (operation.equals(CloudControllerService.GET_TENANTS_OPERATION)) {
				Gson gson = new Gson();
				GetTenantsResponse gtr = gson.fromJson(resultData.getString(CloudControllerService.OPERATION_RESULTS), GetTenantsResponse.class);
				
				populateItems(gtr);
			} 
		}
	}
    
	private void populateItems(GetTenantsResponse gtr) {
		tenants.clear();
		Iterator<TenantDetailsObject> it = gtr.getTenants().iterator();
		TenantDetailsObject item = null;
		
		while(it.hasNext()) {
			item = it.next();		
			TenantModel newItem = new TenantModel(item.getId(), item.getName(), item.isEnabled(), item.getDescription());			
			tenants.add(newItem);
		}
		
		adapter.notifyDataSetChanged();
	}

}
