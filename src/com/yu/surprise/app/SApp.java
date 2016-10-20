package com.yu.surprise.app;

import com.yu.surprise.service.BackgroundService;
import com.yu.surprise.test.LoadingActivity;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class SApp extends Application {
	
	@Override
	public void onCreate(){
		Log.e("Test", "SApp onCreate()..");
		super.onCreate();
		init();
	}
	
	public void init(){
//		Intent itt_loading = new Intent(this, LoadingActivity.class);
//		itt_loading.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		startActivity(itt_loading);
//		Intent service = new Intent(this, BackgroundService.class);
//		startService(service);
	}
}
