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
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

public class VolumeListFragment extends ListFragment {
	String[] volumes = new String[] {
			"/var",
			"/static",
			"/opt"
	};
	int mCurCheckPosition = 0;
	
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, volumes));
        
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}
	
    public void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
		 ((CloudBrowserActivity) getActivity()).setDetailEnabled(true);
		
    	showDetails(position);    
    }

	private void showDetails(int position) {
		// TODO Auto-generated method stub
		mCurCheckPosition = position;
        getListView().setItemChecked(position, true);

		VolumeDetailsFragment vdf = (VolumeDetailsFragment) ((CloudBrowserActivity) getActivity()).getmVolumeDetailsFragment();
		
		if (vdf == null || vdf.getShownIndex() != position) 
			vdf = VolumeDetailsFragment.newInstance(position);
			
		FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.item_details, vdf);
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        
        /*VolumeDetailsFragment df = (VolumeDetailsFragment) getFragmentManager().findFragmentById(R.id.item_details);
        
        if (df == null || df.getShownIndex() != position) {
            df = VolumeDetailsFragment.newInstance(position);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.item_details, df);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit(); 
        }*/
        
        ((CloudBrowserActivity) getActivity()).showDetailsLayout();
	}
}
