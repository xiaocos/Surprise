package com.yu.surprise.service;

import com.yu.surprise.MainActivity;
import com.yu.surprise.util.Utils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BackgroundService extends Service{
	private BackBinder bBinder = new BackBinder();
	public class BackBinder extends Binder{
		public BackgroundService getService(){
			return BackgroundService.this;
		}
	}
	
	@Override
	public void onCreate() {
		Log.e("Test", "BackgroundService onCreate()..");
		super.onCreate();
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return bBinder;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
	
	public void moveOn(){
		Log.d(MainActivity.TAG, "walk on the way,sir!");
	};
	
	public void stay(){
		Log.d(MainActivity.TAG, "stay for the way,sir!");
	}
}
