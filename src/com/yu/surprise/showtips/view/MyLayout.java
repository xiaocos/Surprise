package com.yu.surprise.showtips.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MyLayout extends FrameLayout {
	private Context mContext;
	private int mWidth;
	private int mHeight;
	
	public MyLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private void init(Context _c){
		this.mContext = _c;
		this.mWidth = 0;
		this.mHeight = 0;
	}
	
	public void setLocation(int _width,int _height){
		this.mWidth = _width;
		this.mHeight = _height;
	}
	
	public int getMyWidth(){
		return mWidth;
	}
	
	public int getMyHeight(){
		return mHeight;
	}
	
	/**
	 * {@inheritDoc}
	 * **/
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
