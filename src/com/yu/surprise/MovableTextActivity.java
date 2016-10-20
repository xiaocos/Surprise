package com.yu.surprise;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MovableTextActivity extends Activity{
	public static final String TAG = "MovableTextView";
	TextView tv_move ;
	View v;
	private float x,y,ori_x,ori_y,tv_ori_x,tv_ori_y;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movable_textview);
		initView();
	}
	
	@SuppressLint("NewApi")
	public void initView(){
		tv_move = (TextView)findViewById(R.id.tv_move);
		tv_ori_x =  tv_move.getX();
		tv_ori_y =  tv_move.getY();
		Log.d(TAG, "initView"+"++tv_move.getX()++"+tv_move.getX()+"++tv_move.getY()++"+tv_move.getY());
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		x = event.getX()-ori_x;
		y = event.getY()-ori_y;
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			ori_x = event.getX();
			ori_y = event.getY();
			Log.d(TAG, "ACTION_DOWN"+"++x++"+x+"++y++"+y);
			tv_move.setX(x);
			tv_move.setY(y);
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "ACTION_MOVE"+"++x++"+x+"++y++"+y);
			tv_move.setX(x+tv_ori_x);
			tv_move.setY(y+tv_ori_y);
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "ACTION_UP"+"++x++"+x+"++y++"+y);
			tv_move.setX(x+tv_ori_x);
			tv_move.setY(y+tv_ori_y);
			tv_ori_x =  tv_move.getX();
			tv_ori_y =  tv_move.getY();
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}
