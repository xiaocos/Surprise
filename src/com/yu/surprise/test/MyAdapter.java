package com.yu.surprise.test;

import java.util.List;

import com.yu.surprise.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	private Context context;
	private List<String> list_arrs;
	private LayoutInflater mInflater;
	
	public final class MyListView{
		public TextView tv_content;
		public TextView tv_time;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyListView holder = null;
		if(convertView == null){
			holder = new MyListView();
			convertView = mInflater.inflate(R.layout.test, null);
		}
		return null;
	}

}
