package com.rcarrillocruz.android.openstackdroid;

import com.rcarrillocruz.android.openstackdroid.model.UserModel;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class UserDetailsFragment extends Fragment {
	private ScrollView scroller;
	private TextView tv;
	
	public static UserDetailsFragment newInstance(int position) {
		UserDetailsFragment f = new UserDetailsFragment();
		
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
        UserModel user = (((CloudBrowserActivity)getActivity()).getUsers()).get(getShownIndex());
        
        StringBuffer sb = new StringBuffer();
        sb.append("ID: " + user.getId() + "\n\n");
        sb.append("Name: " + user.getName() + "\n\n");
        sb.append("Enabled: " + user.isEnabled() + "\n\n");
        sb.append("Email: " + user.getEmail() + "\n\n");
        
        String tenantId = user.getTenantId();
        
        if (tenantId == null)
        	tenantId = "";
        	
        sb.append("Tenant id: " + tenantId + "\n\n");
        
        tv.setText(sb.toString());
        return scroller; 
    }
}
