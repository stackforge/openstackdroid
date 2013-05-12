package com.rcarrillocruz.android.openstackdroid;

import com.rcarrillocruz.android.openstackdroid.model.ImageModel;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class ImageDetailsFragment extends Fragment {
	private ScrollView scroller;
	private TextView tv;
	
	public static ImageDetailsFragment newInstance(int position) {
		// TODO Auto-generated method stub
		ImageDetailsFragment f = new ImageDetailsFragment();
		
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
        ImageModel im = (((CloudBrowserActivity)getActivity()).getImages()).get(getShownIndex());
        StringBuffer sb = new StringBuffer();
        sb.append("ID: " + im.getId() + "\n\n");
        sb.append("Name: " + im.getName() + "\n\n");
        sb.append("Status: " + im.getStatus() + "\n\n");
        sb.append("Visibility: " + im.getVisibility() + "\n\n");
        sb.append("Checksum: " + im.getChecksum() + "\n\n");
        sb.append("Created: " + im.getCreated_at() + "\n\n");
        sb.append("Updated: " + im.getUpdated_at() + "\n\n");
        sb.append("Size: " + im.getSize() + "\n\n");
        sb.append("Container format: " + im.getContainer_format() + "\n\n");
        sb.append("Disk format: " + im.getDisk_format() + "\n\n");
        
        tv.setText(sb.toString());
        return scroller; 
    }
}
