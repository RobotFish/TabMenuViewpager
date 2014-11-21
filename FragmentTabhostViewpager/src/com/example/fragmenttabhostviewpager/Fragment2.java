package com.example.fragmenttabhostviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
	TextView msg;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("TAG", "2222onCreate");
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("TAG", "2222");
		View rootView = inflater.inflate(R.layout.fragment2, container,
				false);
		msg = (TextView) rootView.findViewById(R.id.msg);

		return rootView;
	}

	public void setMsg(String text) {
		msg.setText(text);
	}
}