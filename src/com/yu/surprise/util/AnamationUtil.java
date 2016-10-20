package com.yu.surprise.util;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Log;
import android.view.View;

public class AnamationUtil {
	/**
	 * @param view 
	 * @param 
	 * */
	public static void rotateView(View view){
//		ObjectAnimator oa = new ObjectAnimator();
//		oa.setTarget(view);
//		oa.setDuration(500);
//		oa.setInterpolator(new TimeInterpolator() {
//			@Override
//			public float getInterpolation(float input) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		});
		ObjectAnimator oa = ObjectAnimator.ofFloat(view, "rotation", 0F,360.0F);
		oa.setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "rotationX", 0F,360.0F).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "rotationY", 0F,360.0f).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "pivotX", 0F,360.0f).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "translationX", 0F,36.0f).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "translationY", 0F,36.0f).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "scaleX", 0.5F).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "scaleY", 2F).setDuration(500).start();
		
		Log.e("Test", "getAlpha:"+view.getAlpha()+" getBaseline:"+view.getBaseline()+" getBaseline:"+view.getBottom()+" getCameraDistance:"+view.getCameraDistance()
				+"\ngetDrawingCacheBackgroundColor:"+view.getDrawingCacheBackgroundColor()+" getDrawingCacheQuality:"+view.getDrawingCacheQuality()+" getDrawingTime:"+view.getDrawingTime()
				+" getHeight:"+view.getHeight()+" getId:"+view.getId()
				+"\ngetHorizontalFadingEdgeLength:"+view.getHorizontalFadingEdgeLength()
				+" getPivotX:"+view.getPivotX()+" getPivotY:"+view.getPivotY()
				+" getRotation:"+view.getRotation()+" getRotationX:"+view.getRotationX()+" getRotationY:"+view.getRotationY()
				+"\ngetScaleX:"+view.getScaleX()
				+" getScaleY:"+view.getScaleY()
				+" getTranslationX:"+view.getTranslationX()+" getTranslationY:"+view.getTranslationY()
				+" getX:"+view.getX()+" getY:"+view.getY());
	}
	/**
	 * @param view 
	 * @param 
	 * */
	public static void rotateViewBack(View view){
		ObjectAnimator.ofFloat(view, "rotation", 360.0F,0F).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "rotationX", 360.0F,.0F).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "rotationY", 360.0f,.0F).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "pivotX", 360.0f,.0f).setDuration(500).start();
		ObjectAnimator.ofFloat(view, "translationX", 72.0f,.0f).setDuration(500).start();
		ObjectAnimator.ofFloat(view, "translationY", 72.0f,.0f).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "scaleX", 2F).setDuration(500).start();
//		ObjectAnimator.ofFloat(view, "scaleY", 0.5F).setDuration(500).start();
		Log.e("Test", "getAlpha:"+view.getAlpha()+" getBaseline:"+view.getBaseline()+" getBaseline:"+view.getBottom()+" getCameraDistance:"+view.getCameraDistance()
				+"\ngetDrawingCacheBackgroundColor:"+view.getDrawingCacheBackgroundColor()+" getDrawingCacheQuality:"+view.getDrawingCacheQuality()+" getDrawingTime:"+view.getDrawingTime()+" getHeight:"+view.getHeight()+" getId:"+view.getId()
				+"\ngetHorizontalFadingEdgeLength:"+view.getHorizontalFadingEdgeLength()+" getPivotX:"+view.getPivotX()+" getPivotY:"+view.getPivotY()+" getRotation:"+view.getRotation()+" getRotationX:"+view.getRotationX()+" getRotationY:"+view.getRotationY()
				+"\ngetScaleX:"+view.getScaleX()+" getScaleY:"+view.getScaleY()+" getTranslationX:"+view.getTranslationX()+" getTranslationY:"+view.getTranslationY()+" getX:"+view.getX()+" getY:"+view.getY());
	}
}
