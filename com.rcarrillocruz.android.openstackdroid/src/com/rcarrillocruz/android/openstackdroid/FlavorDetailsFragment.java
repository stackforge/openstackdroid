package com.rcarrillocruz.android.openstackdroid;

import com.rcarrillocruz.android.openstackdroid.model.FlavorModel;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class FlavorDetailsFragment extends Fragment {
	private ScrollView scroller;
	private TextView tv;
	
	public static FlavorDetailsFragment newInstance(int position) {
		// TODO Auto-generated method stub
		FlavorDetailsFragment f = new FlavorDetailsFragment();
		
		Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;	
    }
	
	public int getShownIndex() {
		// TODO Auto-generated method stub
		return getArguments().getInt("position", 0);
	}
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        scroller = new ScrollView(getActivity());
        tv = new TextView(getActivity());
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
        tv.setPadding(padding, padding, padding, padding);
        scroller.addView(tv); 
        FlavorModel fm = (((CloudBrowserActivity)getActivity()).getFlavors()).get(getShownIndex());
        
        StringBuffer sb = new StringBuffer();
        sb.append("ID: " + fm.getId() + "\n\n");
        sb.append("Name: " + fm.getName() + "\n\n");
        sb.append("VCPUs: " + fm.getVcpus() + "\n\n");
        sb.append("RAM: " + fm.getRam() + "\n\n");
        sb.append("Root disk: " + fm.getRootDisk() + "\n\n");
        sb.append("Swap disk: " + fm.getSwapDisk() + "\n\n");
        
        tv.setText(sb.toString());
        return scroller; 
    }
}
