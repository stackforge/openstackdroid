package com.rcarrillocruz.android.openstackdroid;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class ServerListFragment extends ListFragment {
	String[] servers = new String[] {
			"webserver",
			"oracle",
			"proxy"
	};
	int mCurCheckPosition = 0;
    
    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, servers));
        
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}

	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	showDetails(position);    
    }

	private void showDetails(int position) {
		// TODO Auto-generated method stub
		mCurCheckPosition = position;
		getListView().setItemChecked(position, true);
		
		ServerDetailsFragment sdf = (ServerDetailsFragment) ((CloudBrowserActivity) getActivity()).getmServerDetailsFragment();
		
		if (sdf == null || sdf.getShownIndex() != position) 
			sdf = ServerDetailsFragment.newInstance(position);
			
		FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.item_details, sdf);
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
		
		/*		mCurCheckPosition = position;
        getListView().setItemChecked(position, true);

	        ServerDetailsFragment df = (ServerDetailsFragment) getFragmentManager().findFragmentById(R.id.item_details);
	        
	        if (df == null || df.getShownIndex() != position) {
	            df = ServerDetailsFragment.newInstance(position);
	
	            FragmentTransaction ft = getFragmentManager().beginTransaction();
	            ft.replace(R.id.item_details, df);
	            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            ft.commit(); 
	        } */
	        
	    ((CloudBrowserActivity) getActivity()).showDetailsLayout();
	}
    
    
}
