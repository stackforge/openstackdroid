package com.rcarrillocruz.android.openstackdroid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.rcarrillocruz.android.openstackdroid.json.volume.GetVolumesResponse;
import com.rcarrillocruz.android.openstackdroid.json.volume.VolumeDetailsObject;
import com.rcarrillocruz.android.openstackdroid.model.VolumeModel;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

public class VolumeListFragment extends CloudBrowserListFragment {
	List<VolumeModel> volumes;
	private ArrayAdapter<VolumeModel> adapter;
	
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		volumes = ((CloudBrowserActivity) getActivity()).getVolumes();
        endpoint = ((OpenstackdroidApplication) (getActivity().getApplication())).getVolumeEndpoint();
		
        Intent serviceIntent = new Intent(getActivity(), CloudControllerService.class);
        serviceIntent.setData(Uri.parse(endpoint));
        serviceIntent.putExtra(CloudControllerService.OPERATION, CloudControllerService.GET_VOLUMES_OPERATION);
        serviceIntent.putExtra(CloudControllerService.TOKEN, ((OpenstackdroidApplication) getActivity().getApplication()).getToken());
        serviceIntent.putExtra(CloudControllerService.TENANT, ((OpenstackdroidApplication) getActivity().getApplication()).getTenantId());
        serviceIntent.putExtra(CloudControllerService.RECEIVER, mReceiver); 
        Bundle params = new Bundle();         
        serviceIntent.putExtra(CloudControllerService.PARAMS, params);
                
        getActivity().startService(serviceIntent);
		
        adapter = new ArrayAdapter<VolumeModel>(getActivity(), android.R.layout.simple_list_item_activated_1, volumes);
        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
		mCurCheckPosition = position;
		getListView().setItemChecked(position, true);
		
		showDetails(position);    
    }

	protected void showDetails(int position) {
		// TODO Auto-generated method stub
		VolumeDetailsFragment sdf = (VolumeDetailsFragment) ((CloudBrowserActivity) getActivity()).getmVolumeDetailsFragment();
		
		if (sdf == null || sdf.getShownIndex() != position) 
			sdf = VolumeDetailsFragment.newInstance(position);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.item_details, sdf);
        ft.commit();
	        
	    ((CloudBrowserActivity) getActivity()).showDetailsLayout();
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		// TODO Auto-generated method stub
		if (resultCode == 200) {
			String operation = resultData.getString(CloudControllerService.OPERATION);
			
			if (operation.equals(CloudControllerService.GET_VOLUMES_OPERATION)) {
				Gson gson = new Gson();
				GetVolumesResponse gvr = gson.fromJson(resultData.getString(CloudControllerService.OPERATION_RESULTS), GetVolumesResponse.class);
				
				populateItems(gvr);
			} 
		}
	}
    
	private void populateItems(GetVolumesResponse gsr) {
		// TODO Auto-generated method stub
		volumes.clear();
		Iterator<VolumeDetailsObject> it = gsr.getVolumes().iterator();
		VolumeDetailsObject item = null;
		
		while(it.hasNext()) {
			item = it.next();		
			VolumeModel newItem = new VolumeModel(item.getId(), item.getDisplay_name(), item.getDisplay_description(), item.getStatus(), item.getSize(), item.getCreated_at(), null);			
			
			if (!item.getAttachments().isEmpty())
				newItem.setAttached_to(item.getAttachments().get(0).getId());
			volumes.add(newItem);
		}
		
		adapter.notifyDataSetChanged();
	}

}
