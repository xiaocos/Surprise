package com.yu.surprise.test.fragment;

import com.yu.surprise.R;
import com.yu.surprise.util.Utils;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import com.yu.surprise.util.Constants;

public class Upfragment extends Fragment implements Handler.Callback,OnClickListener,OnTouchListener{
	TextView tv1 ;
	Button btn1,btn2;
	View layoutView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Utils.printTime("Upfragment - onCreate");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Utils.printTime("Upfragment - onCreateView");
		layoutView = inflater.inflate(R.layout.test_fragment, container,false);
//		layoutView.setOnTouchListener(this);
		return layoutView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		initView();
	}
	
	public void initView(){
		tv1 = (TextView)getActivity().findViewById(R.id.textView1);
		btn1 = (Button)getActivity().findViewById(R.id.button1);
		btn2 = (Button)getActivity().findViewById(R.id.button2);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Intent itt = new Intent();
			itt.setAction(Constants.BTN1_BR_ACTION);
			getActivity().sendBroadcast(itt);
			
//			Utils.update(getActivity());
			break;
			
		case R.id.button2:
			Intent itt2 = new Intent();
			itt2.setAction(Constants.BTN2_BR_ACTION);
			getActivity().sendBroadcast(itt2);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		
		return false;
	}

}
