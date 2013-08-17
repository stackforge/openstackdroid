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
import com.rcarrillocruz.android.openstackdroid.json.image.GetImagesResponse;
import com.rcarrillocruz.android.openstackdroid.json.image.ImageDetailsObject;
import com.rcarrillocruz.android.openstackdroid.model.ImageModel;

public class ImageListFragment extends CloudBrowserListFragment {
	List<ImageModel> images;
	private ArrayAdapter<ImageModel> adapter;
    
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
    	endpoint = ((OpenstackdroidApplication) (getActivity().getApplication())).getImageEndpoint();
		images = ((CloudBrowserActivity) getActivity()).getImages();
		
        Intent serviceIntent = new Intent(getActivity(), CloudControllerService.class);
        serviceIntent.setData(Uri.parse(endpoint));
        serviceIntent.putExtra(CloudControllerService.OPERATION, CloudControllerService.GET_IMAGES_OPERATION);
        serviceIntent.putExtra(CloudControllerService.TOKEN, ((OpenstackdroidApplication) getActivity().getApplication()).getToken());
        serviceIntent.putExtra(CloudControllerService.TENANT, (String)null);
        serviceIntent.putExtra(CloudControllerService.RECEIVER, mReceiver); 
        Bundle params = new Bundle();         
        serviceIntent.putExtra(CloudControllerService.PARAMS, params);
                
        getActivity().startService(serviceIntent);
		
        adapter = new ArrayAdapter<ImageModel>(getActivity(), android.R.layout.simple_list_item_activated_1, images);
        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}

	public void onListItemClick(ListView l, View v, int position, long id) {
    	mCurCheckPosition = position;
		getListView().setItemChecked(position, true);
		
		showDetails(position);      
    }

	protected void showDetails(int position) {
		ImageDetailsFragment idf = (ImageDetailsFragment) ((CloudBrowserActivity) getActivity()).getmImageDetailsFragment();
		
		if (idf == null || idf.getShownIndex() != position) 
			idf = ImageDetailsFragment.newInstance(position);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.item_details, idf);
        ft.commit();
	        
	    ((CloudBrowserActivity) getActivity()).showDetailsLayout();
	}

	public void onReceiveResult(int resultCode, Bundle resultData) {
		if (resultCode == 200) {
			String operation = resultData.getString(CloudControllerService.OPERATION);
			
			if (operation.equals(CloudControllerService.GET_IMAGES_OPERATION)) {
				Gson gson = new Gson();
				GetImagesResponse gir = gson.fromJson(resultData.getString(CloudControllerService.OPERATION_RESULTS), GetImagesResponse.class);
				
				populateItems(gir);
			} 
		}
	}
    
	private void populateItems(GetImagesResponse gir) {
		images.clear();
		Iterator<ImageDetailsObject> it = gir.getImages().iterator();
		ImageDetailsObject item = null;
		
		while(it.hasNext()) {
			
			item = it.next();
			ImageModel newIm = new ImageModel(item.getId(), item.getName(), item.getStatus(), item.getVisibility(), item.getChecksum(), item.getCreated_at(), item.getUpdated_at(), item.getSize(), item.getContainer_format(), item.getDisk_format()) ;			
			images.add(newIm);
		}
		
		adapter.notifyDataSetChanged();
	}
}
