package com.yu.surprise.service;

import java.io.IOException;

import com.yu.surprise.R;
import com.yu.surprise.util.Utils;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;

public class AlertService extends Service{
	MediaPlayer media;
	@Override
	public void onCreate() {
		super.onCreate();
		Utils.printInfo(null, null);
	}
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Utils.printInfo("start..", null);
			media = MediaPlayer.create(this, R.raw.alert);
			media.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					Utils.printInfo("onCompletion..", null);
					mp.stop();
					onDestroy();
				}
			});
		try {
//			media.prepare();
			System.out.println("media.isPlaying():"+media.isPlaying());
			if(media!=null&&media.isPlaying()){
				Utils.printInfo("try restart..", null);
				media.stop();
				media.start();
			}
			media.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		Utils.printInfo("end start..", null);
	}
	
	
	@Override
	public IBinder onBind(Intent intent) {
		Utils.printInfo("onBind..", null);
		return null;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Utils.printInfo("onDestroy..", null);
		media.release();
	}

}
