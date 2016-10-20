package com.yu.surprise.test.fragment;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;

public class AbsListViewBaseFragment extends BaseFragment {
	
	protected AbsListView listview;
	
	protected boolean pauseOnScroll = false;
	protected boolean pauseOnFling = true;
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		MenuItem pauseOnScrollItem = menu.findItem(1);
		
		
		
		super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	protected void startImagePagerActivity(int position) {
		
	}
	
	private void applyScrollListener() {
		listview.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling));
	}
}
