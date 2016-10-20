package com.yu.surprise.service;

import java.text.DecimalFormat;
import java.util.Iterator;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;

public class LocationService extends Service implements android.os.Handler.Callback{
	LocationManager locationManager;
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		openGPSSettings();
		getLocation();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	//打开位置服务设置
	private boolean openGPSSettings(){
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			return true;
		};
		Intent _it = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(_it);
		return false;
	}
	//获取位置信息 增加位置监听
	private void getLocation(){
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
	
		String provider = locationManager.getBestProvider(criteria, true);//
		Location location = locationManager.getLastKnownLocation(provider);
		double la = location.getLatitude();
		double lo = location.getLongitude();
		updateToNewLocation(location);
		locationManager.addGpsStatusListener(listener);
		// 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1,
						locationListener);
	}
	//更新位置信息
	private void updateToNewLocation(Location location) {
//		Log.d(TAG, "updateToNewLocation..");
		if (location != null) {
			//纬度
			double latitude = location.getLatitude();
			//经度
			double longitude = location.getLongitude();
			DecimalFormat df = new DecimalFormat("#.00000000");  
//			et_y.setText(df.format(latitude));
//			et_x.setText(df.format(longitude));
//			Log.d(TAG, "纬度：" + latitude + "\n经度" + longitude);
		} else {
//			Log.d(TAG, "无法获取地理信息");
			
		}
	}
	
	GpsStatus.Listener l = new GpsStatus.Listener() {
		@Override
		public void onGpsStatusChanged(int event) {
			
		}
	};
	
	//GPS状态监听
  	GpsStatus.Listener listener = new GpsStatus.Listener() {
  		public void onGpsStatusChanged(int event) {
  			System.out.println("GPS状态==--------==="+event);
//  			Log.i(TAG, "GPS状态==--------==="+event);
  			switch (event) {
  			//第一次定位
  			case GpsStatus.GPS_EVENT_FIRST_FIX:
//  				Log.i(TAG, "第一次定位");
  				break;
  				//卫星状态改变
  			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
//  				Log.i(TAG, "卫星状态改变");
  				//获取当前状态
  				GpsStatus gpsStatus=locationManager.getGpsStatus(null);
  				//获取卫星颗数的默认最大值
  				int maxSatellites = gpsStatus.getMaxSatellites();
  				//创建一个迭代器保存所有卫星 
  				Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
  				int count = 0;     
  				while (iters.hasNext() && count <= maxSatellites) {     
  					GpsSatellite s = iters.next();     
  					count++;     
  				}   
  				System.out.println("搜索到："+count+"颗卫星");
  				break;
  				//定位启动
  			case GpsStatus.GPS_EVENT_STARTED:
//  				Log.i(TAG, "定位启动");
  				break;
  				//定位结束
  			case GpsStatus.GPS_EVENT_STOPPED:
//  				Log.i(TAG, "定位结束");
  				break;
  			}
  		};
  	};
  	//位置变化监听
  	private LocationListener locationListener=new LocationListener() {
        /**
         * 位置信息变化时触发
         */
        public void onLocationChanged(Location location) {
//        		updateToNewLocation(location);
//            Log.i(TAG, "时间："+location.getTime()); 
//            Log.i(TAG, "经度："+location.getLongitude()); 
//            Log.i(TAG, "纬度："+location.getLatitude()); 
//            Log.i(TAG, "海拔："+location.getAltitude()); 
        }
        
        /**
         * GPS状态变化时触发
         */
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
            //GPS状态为可见时
            case LocationProvider.AVAILABLE:
//                Log.i(TAG, "当前GPS状态为可见状态");
                break;
            //GPS状态为服务区外时
            case LocationProvider.OUT_OF_SERVICE:
//                Log.i(TAG, "当前GPS状态为服务区外状态");
                break;
            //GPS状态为暂停服务时
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
//                Log.i(TAG, "当前GPS状态为暂停服务状态");
                break;
            }
        }
    
        /**
         * GPS开启时触发
         */
        public void onProviderEnabled(String provider) {
            Location location=locationManager.getLastKnownLocation(provider);
//            updateToNewLocation(location);
        }
    
        /**
         * GPS禁用时触发
         */
        public void onProviderDisabled(String provider) {
//        	updateToNewLocation(null);
        }

    
    };
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		
		case 0x9999:
			onDestroy();
			break;

		default:
			break;
		}
		return false;
	}
}
