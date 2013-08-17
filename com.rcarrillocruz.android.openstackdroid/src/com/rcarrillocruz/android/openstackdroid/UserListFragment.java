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
import com.rcarrillocruz.android.openstackdroid.json.identity.GetUsersResponse;
import com.rcarrillocruz.android.openstackdroid.json.identity.UserDetailsObject;
import com.rcarrillocruz.android.openstackdroid.model.UserModel;

public class UserListFragment extends CloudBrowserListFragment {

	List<UserModel> users;
	private ArrayAdapter<UserModel> adapter;
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		users = ((CloudBrowserActivity) getActivity()).getUsers();
        endpoint = ((OpenstackdroidApplication) (getActivity().getApplication())).getIdentityAdminEndpoint();
		
        Intent serviceIntent = new Intent(getActivity(), CloudControllerService.class);
        serviceIntent.setData(Uri.parse(endpoint));
        serviceIntent.putExtra(CloudControllerService.OPERATION, CloudControllerService.GET_USERS_OPERATION);
        serviceIntent.putExtra(CloudControllerService.TOKEN, ((OpenstackdroidApplication) getActivity().getApplication()).getToken());
        serviceIntent.putExtra(CloudControllerService.TENANT, (String)null);
        serviceIntent.putExtra(CloudControllerService.RECEIVER, mReceiver); 
        Bundle params = new Bundle();         
        serviceIntent.putExtra(CloudControllerService.PARAMS, params);
                
        getActivity().startService(serviceIntent);
		
        adapter = new ArrayAdapter<UserModel>(getActivity(), android.R.layout.simple_list_item_activated_1, users);
        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}
	
    public void onListItemClick(ListView l, View v, int position, long id) {
		mCurCheckPosition = position;
		getListView().setItemChecked(position, true);
		
		showDetails(position);    
    }

	protected void showDetails(int position) {
		UserDetailsFragment udf = (UserDetailsFragment) ((CloudBrowserActivity) getActivity()).getmUserDetailsFragment();
		
		if (udf == null || udf.getShownIndex() != position) 
			udf = UserDetailsFragment.newInstance(position);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.item_details, udf);
        ft.commit();
	        
	    ((CloudBrowserActivity) getActivity()).showDetailsLayout();
	}

	public void onReceiveResult(int resultCode, Bundle resultData) {
		if (resultCode == 200) {
			String operation = resultData.getString(CloudControllerService.OPERATION);
			
			if (operation.equals(CloudControllerService.GET_USERS_OPERATION)) {
				Gson gson = new Gson();
				GetUsersResponse gur = gson.fromJson(resultData.getString(CloudControllerService.OPERATION_RESULTS), GetUsersResponse.class);
				
				populateItems(gur);
			} 
		}
	}
    
	private void populateItems(GetUsersResponse gur) {
		users.clear();
		Iterator<UserDetailsObject> it = gur.getUsers().iterator();
		UserDetailsObject item = null;
		
		while(it.hasNext()) {
			item = it.next();		
			UserModel newItem = new UserModel(item.getId(), item.getName(), item.isEnabled(), item.getEmail(), item.getTenantId());			
			users.add(newItem);
		}
		
		adapter.notifyDataSetChanged();
	}
}
