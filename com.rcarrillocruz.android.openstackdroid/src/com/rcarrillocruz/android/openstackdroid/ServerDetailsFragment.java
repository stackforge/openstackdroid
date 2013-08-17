package com.rcarrillocruz.android.openstackdroid;

import java.util.Iterator;

import com.rcarrillocruz.android.openstackdroid.model.IPAddressModel;
import com.rcarrillocruz.android.openstackdroid.model.ServerModel;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class ServerDetailsFragment extends Fragment {
	private ScrollView scroller;
	private TextView tv;
	
	public static ServerDetailsFragment newInstance(int position) {
		ServerDetailsFragment f = new ServerDetailsFragment();
		
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
        ServerModel server = (((CloudBrowserActivity)getActivity()).getServers()).get(getShownIndex());
        StringBuffer sb = new StringBuffer();
        sb.append("ID: " + server.getId() + "\n\n");
        sb.append("Name: " + server.getName() + "\n\n");
        sb.append("Created: " + server.getCreated() + "\n\n");
        sb.append("Updated: " + server.getUpdated() + "\n\n");
        sb.append("Status: " + server.getStatus() + "\n\n");
        sb.append("Image: " + server.getImage() + "\n\n");
        sb.append("Flavor: " + server.getFlavor() + "\n\n");
        sb.append("Private IP addresses:\n\n");
        
        Iterator<IPAddressModel> it = (Iterator<IPAddressModel>) server.getPrivateAddresses().iterator();
        
        while (it.hasNext()) {
        	sb.append(it.next().getAddr().toString() + "\n");
        }

        sb.append("\nPublic IP addresses: \n\n");
        it = (Iterator<IPAddressModel>) server.getPublicAddresses().iterator();
        
        while (it.hasNext()) {
        	sb.append(it.next().getAddr().toString() + "\n");
        }
        
        tv.setText(sb.toString());
        return scroller; 
    }

}
