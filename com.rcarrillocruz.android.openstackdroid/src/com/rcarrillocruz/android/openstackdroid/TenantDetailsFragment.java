package com.rcarrillocruz.android.openstackdroid;

import com.rcarrillocruz.android.openstackdroid.model.TenantModel;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class TenantDetailsFragment extends Fragment {
	private ScrollView scroller;
	private TextView tv;
	
	public static TenantDetailsFragment newInstance(int position) {
		// TODO Auto-generated method stub
		TenantDetailsFragment f = new TenantDetailsFragment();
		
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
        scroller = new ScrollView(getActivity());
        tv = new TextView(getActivity());
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
        tv.setPadding(padding, padding, padding, padding);
        scroller.addView(tv); 
        TenantModel tenant = (((CloudBrowserActivity)getActivity()).getTenants()).get(getShownIndex());
        
        StringBuffer sb = new StringBuffer();
        sb.append("ID: " + tenant.getId() + "\n\n");
        sb.append("Name: " + tenant.getName() + "\n\n");
        sb.append("Enabled: " + tenant.isEnabled() + "\n\n");
        
        String description = tenant.getDescription();
        
        if (description == null)
        	description = "";
        
        sb.append("Description: " + description + "\n\n");
           	     
        tv.setText(sb.toString());
        return scroller; 
    }
}
