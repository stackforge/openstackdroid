package com.rcarrillocruz.android.openstackdroid;

import com.rcarrillocruz.android.openstackdroid.CloudControllerResultReceiver.Receiver;

import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;

public abstract class CloudBrowserListFragment extends ListFragment implements Receiver {
	protected String endpoint;
	protected int mCurCheckPosition;
	protected CloudControllerResultReceiver mReceiver;
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mReceiver = new CloudControllerResultReceiver(new Handler());
        mReceiver.setReceiver(this);
	}
	
	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		// TODO Auto-generated method stub
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public int getmCurCheckPosition() {
		return mCurCheckPosition;
	}

	public void setmCurCheckPosition(int mCurCheckPosition) {
		this.mCurCheckPosition = mCurCheckPosition;
	}

	public CloudControllerResultReceiver getmReceiver() {
		return mReceiver;
	}

	public void setmReceiver(CloudControllerResultReceiver mReceiver) {
		this.mReceiver = mReceiver;
	}

	protected abstract void showDetails(int position);
	
}
