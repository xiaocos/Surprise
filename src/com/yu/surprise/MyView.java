package com.yu.surprise;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View{
	Paint mpaint;
	public MyView(Context context){
		super(context);
		mpaint = new Paint();
	}
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mpaint = new Paint();
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyView);
		int viewColor = a.getColor(R.styleable.MyView_viewColor, 0xffffffff);
		mpaint.setColor(viewColor);
		a.recycle();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mpaint = new Paint();
		mpaint.setColor(Color.BLUE);
		Path path = new Path();
		path.moveTo(20, 70);
		path.lineTo(120, 20);
		path.lineTo(120, 70);
		path.close();
		canvas.drawPath(path, mpaint);
		
	}
}
