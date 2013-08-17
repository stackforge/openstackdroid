package com.rcarrillocruz.android.openstackdroid;

import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.rcarrillocruz.android.openstackdroid.json.compute.FlavorDetailsObject;
import com.rcarrillocruz.android.openstackdroid.json.compute.GetFlavorsResponse;
import com.rcarrillocruz.android.openstackdroid.model.FlavorModel;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FlavorListFragment extends CloudBrowserListFragment {
	List<FlavorModel> flavors;
	private ArrayAdapter<FlavorModel> adapter;
    
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
        endpoint = ((OpenstackdroidApplication) (getActivity().getApplication())).getComputeEndpoint();
		flavors = ((CloudBrowserActivity) getActivity()).getFlavors();
		
        Intent serviceIntent = new Intent(getActivity(), CloudControllerService.class);
        serviceIntent.setData(Uri.parse(endpoint));
        serviceIntent.putExtra(CloudControllerService.OPERATION, CloudControllerService.GET_FLAVORS_OPERATION);
        serviceIntent.putExtra(CloudControllerService.TOKEN, ((OpenstackdroidApplication) getActivity().getApplication()).getToken());
        serviceIntent.putExtra(CloudControllerService.TENANT, (String)null);
        serviceIntent.putExtra(CloudControllerService.RECEIVER, mReceiver); 
        Bundle params = new Bundle();         
        serviceIntent.putExtra(CloudControllerService.PARAMS, params);
                
        getActivity().startService(serviceIntent);
		
        adapter = new ArrayAdapter<FlavorModel>(getActivity(), android.R.layout.simple_list_item_activated_1, flavors);
        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}
    
	public void onListItemClick(ListView l, View v, int position, long id) {
    	mCurCheckPosition = position;
		getListView().setItemChecked(position, true);
		
		showDetails(position);      
    }
	
	protected void showDetails(int position) {
		FlavorDetailsFragment fdf = (FlavorDetailsFragment) ((CloudBrowserActivity) getActivity()).getmFlavorDetailsFragment();
		
		if (fdf == null || fdf.getShownIndex() != position) 
			fdf = FlavorDetailsFragment.newInstance(position);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.item_details, fdf);
        ft.commit();
	        
	    ((CloudBrowserActivity) getActivity()).showDetailsLayout();
	}
	
	public void onReceiveResult(int resultCode, Bundle resultData) {
		if (resultCode == 200) {
			String operation = resultData.getString(CloudControllerService.OPERATION);
			
			if (operation.equals(CloudControllerService.GET_FLAVORS_OPERATION)) {
				Gson gson = new Gson();
				GetFlavorsResponse gfr = gson.fromJson(resultData.getString(CloudControllerService.OPERATION_RESULTS), GetFlavorsResponse.class);
				
				populateItems(gfr);
			} 
		}
	}
    
	private void populateItems(GetFlavorsResponse gfr) {
		flavors.clear();
		Iterator<FlavorDetailsObject> it = gfr.getFlavors().iterator();
		FlavorDetailsObject item = null;
		
		while(it.hasNext()) {
			item = it.next();
			FlavorModel newModel = new FlavorModel(item.getId(), item.getName(), item.getVcpus(), item.getRam(), item.getDisk(), item.getSwap()) ;			
			flavors.add(newModel);
		}
		
		adapter.notifyDataSetChanged();
	}

}
