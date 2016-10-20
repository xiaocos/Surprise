package com.yu.surprise.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class SystemInfoUtil {
	/**
	 * get VersionName From AndroidManifest.xml
	 * versionCode should be added every update
	 * versionName will be showed to user
	 * **/
	public static String getVersionName(Context c){
		try {
			PackageManager pm = c.getPackageManager();
			PackageInfo pi;
			pi = pm.getPackageInfo(c.getPackageName(), PackageManager.GET_CONFIGURATIONS);
			int t = pi.versionCode;
			return pi.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	/**
	 * print properties and env
	 * */
	static public void printSystemInfo(){
		Properties properties = System.getProperties();
		Iterator it = properties.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			String value = (String)properties.get(key);
			Utils.printTime("getProperties - key:"+key+" value:"+value);
		}
		Map env = System.getenv();
		Iterator it_env = env.keySet().iterator();
		while(it_env.hasNext()){
			String key = (String)it_env.next();
			String value = (String)env.get(key);
			Utils.printTime("getenv - key:"+key+" value:"+value);
		}
	}
	
	/**
	 * need android.permission.GET_TASKSÈ¨ÏÞ
	 * */
	public static void printActivityService(Context mContext){
		ActivityManager activityManager = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> info = activityManager.getRunningTasks(10);
		if(info!=null && info.size() > 0){
			for(int w = 0; w < info.size(); w++){
				ComponentName component = info.get(w).topActivity;
				Utils.printTime("component.getClassName():"+component.getClassName());
			}
		}
	}
}
