package com.rcarrillocruz.android.openstackdroid;

import com.rcarrillocruz.android.openstackdroid.model.VolumeModel;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class VolumeDetailsFragment extends Fragment {
	private ScrollView scroller;
	private TextView tv;
	
	public static VolumeDetailsFragment newInstance(int position) {
		VolumeDetailsFragment f = new VolumeDetailsFragment();
		
		Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
	}

	public int getShownIndex() {
		return getArguments().getInt("position", 0);
	}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        scroller = new ScrollView(getActivity());
        tv = new TextView(getActivity());
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
        tv.setPadding(padding, padding, padding, padding);
        scroller.addView(tv); 
        VolumeModel volume = (((CloudBrowserActivity)getActivity()).getVolumes()).get(getShownIndex());
        StringBuffer sb = new StringBuffer();
        sb.append("ID: " + volume.getId() + "\n\n");
        
        String name = volume.getName();
        if (name == null)
        	name = "";
        sb.append("Name: " + name + "\n\n");
        
        String description = volume.getDescription();
        if (description == null)
        	description = "";
        sb.append("Description: " + description + "\n\n");
        
        sb.append("Created: " + volume.getCreated_at() + "\n\n");
        sb.append("Status: " + volume.getStatus() + "\n\n");
        sb.append("Size: " + volume.getSize() + "G" + "\n\n");
        
        String attached = volume.getAttached_to();
        
        if (attached == null)
        	attached = "";
        
        sb.append("Attached to: " + attached + "\n\n");
        
        tv.setText(sb.toString());
        return scroller; 
    }
}
