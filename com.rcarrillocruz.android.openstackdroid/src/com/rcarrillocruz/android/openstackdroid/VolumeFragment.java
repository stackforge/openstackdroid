package com.rcarrillocruz.android.openstackdroid;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class VolumeFragment extends ListFragment {
	String[] volumes = new String[] {
			"/static",
			"/photos",
			"/icons"
	};
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_multiple_choice, volumes);
 
        setListAdapter(adapter);
 
        return super.onCreateView(inflater, container, savedInstanceState);
    }
 
    @Override
    public void onStart() {
        super.onStart();
    }
}
