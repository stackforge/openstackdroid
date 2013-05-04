package com.rcarrillocruz.android.openstackdroid;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class ServerDetailsFragment extends Fragment {

	public static final String[] SERVER_DETAILS = {
		"LOLSERVER1",
		"SADASD",
		"ERWER"
	};
	
	public static ServerDetailsFragment newInstance(int position) {
		// TODO Auto-generated method stub
		ServerDetailsFragment f = new ServerDetailsFragment();
		
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
        text.setText(SERVER_DETAILS[getShownIndex()]);
        
        return scroller; 
    }
}
