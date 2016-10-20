package com.yu.surprise;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter{
	private List<View> views;
	public ViewPagerAdapter(List<View> views){
		this.views = views;
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeView(views.get(position));
	}
	
	@Override
	public void finishUpdate(View container) {
		super.finishUpdate(container);
	}
	
	@Override
	public int getCount() {
		if(views!=null){
			return views.size();
		}
		return 0 ;
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager)container).addView(views.get(position),0);
		return views.get(position);
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}
	
	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
		super.restoreState(state, loader);
	}
	
	@Override
	public Parcelable saveState() {
		return super.saveState();
	}
	
	@Override
	public void startUpdate(View container) {
		super.startUpdate(container);
	}
}
