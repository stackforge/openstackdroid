package com.rcarrillocruz.android.openstackdroid;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class VolumeDetailsFragment extends Fragment {

	public static final String[] VOLUME_DETAILS = {
		"10G",
		"2G",
		"40G"
	};
	
	public static VolumeDetailsFragment newInstance(int position) {
		// TODO Auto-generated method stub
		VolumeDetailsFragment f = new VolumeDetailsFragment();
		
		Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
	}

	public int getShownIndex() {
		// TODO Auto-generated method stub
		return getArguments().getInt("position", 0);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        text.setText(VOLUME_DETAILS[getShownIndex()]);
        
        return scroller; 
    }
}
