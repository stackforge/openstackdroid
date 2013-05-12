package com.rcarrillocruz.android.openstackdroid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.rcarrillocruz.android.openstackdroid.CloudControllerResultReceiver.Receiver;
import com.rcarrillocruz.android.openstackdroid.json.compute.GetServersResponse;
import com.rcarrillocruz.android.openstackdroid.json.compute.IPAddressObject;
import com.rcarrillocruz.android.openstackdroid.json.compute.ServerDetailsObject;
import com.rcarrillocruz.android.openstackdroid.model.IPAddressModel;
import com.rcarrillocruz.android.openstackdroid.model.ServerModel;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ServerListFragment extends CloudBrowserListFragment {
	List<ServerModel> servers;
	private ArrayAdapter<ServerModel> adapter;
    
    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		servers = ((CloudBrowserActivity) getActivity()).getServers();
        endpoint = ((OpenstackdroidApplication) (getActivity().getApplication())).getComputeEndpoint();
		
        Intent serviceIntent = new Intent(getActivity(), CloudControllerService.class);
        serviceIntent.setData(Uri.parse(endpoint));
        serviceIntent.putExtra(CloudControllerService.OPERATION, CloudControllerService.GET_SERVERS_OPERATION);
        serviceIntent.putExtra(CloudControllerService.TOKEN, ((OpenstackdroidApplication) getActivity().getApplication()).getToken());
        serviceIntent.putExtra(CloudControllerService.TENANT, ((OpenstackdroidApplication) getActivity().getApplication()).getTenantId());
        serviceIntent.putExtra(CloudControllerService.RECEIVER, mReceiver); 
        Bundle params = new Bundle();         
        serviceIntent.putExtra(CloudControllerService.PARAMS, params);
                
        getActivity().startService(serviceIntent);
		
        adapter = new ArrayAdapter<ServerModel>(getActivity(), android.R.layout.simple_list_item_activated_1, servers);
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
		ServerDetailsFragment sdf = (ServerDetailsFragment) ((CloudBrowserActivity) getActivity()).getmServerDetailsFragment();
		
		if (sdf == null || sdf.getShownIndex() != position) 
			sdf = ServerDetailsFragment.newInstance(position);
		
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
			
			if (operation.equals(CloudControllerService.GET_SERVERS_OPERATION)) {
				Gson gson = new Gson();
				GetServersResponse gsr = gson.fromJson(resultData.getString(CloudControllerService.OPERATION_RESULTS), GetServersResponse.class);
				
				populateItems(gsr);
			} 
		}
	}
    
	private void populateItems(GetServersResponse gsr) {
		// TODO Auto-generated method stub
		servers.clear();
		Iterator<ServerDetailsObject> it = gsr.getServers().iterator();
		ServerDetailsObject item = null;
		
		while(it.hasNext()) {
			
			item = it.next();
			
			ServerModel newSm = new ServerModel(item.getId(), item.getName(), item.getStatus(), item.getCreated(), item.getUpdated(), new ArrayList<IPAddressModel>(), new ArrayList<IPAddressModel>(), item.getImage().getId(), item.getFlavor().getId());			
			
			if (item.getAddresses().getPrivateAddresses() != null) {
				Iterator<IPAddressObject> it_addr = item.getAddresses().getPrivateAddresses().iterator();
				IPAddressObject item2 = null;
				
				while (it_addr.hasNext()) {
					item2 = it_addr.next();
					newSm.getPrivateAddresses().add(new IPAddressModel(item2.getAddr(), item2.getVersion()));
				}
			}
			
			if (item.getAddresses().getPublicAddresses() != null) {
				Iterator<IPAddressObject> it_addr = item.getAddresses().getPublicAddresses().iterator();
				
				while(it_addr.hasNext()) {
					IPAddressObject item2 = it_addr.next();
					newSm.getPublicAddresses().add(new IPAddressModel(item2.getAddr(), item2.getVersion()));
				}
			}
			
			servers.add(newSm);
		}
		
		adapter.notifyDataSetChanged();
	}
}
