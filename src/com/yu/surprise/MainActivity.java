package com.yu.surprise;

import com.yu.surprise.service.BackgroundService;
import com.yu.surprise.service.BackgroundService.BackBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	public static final String TAG = "MainActivity";
	Button btn_sure,btn_cancel,btn_test;
	TextView tv_show;
	BackgroundService bService;
	boolean ifBind = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("Test", "MainActivity onCreate..");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	private void initView() {
		btn_sure = (Button)findViewById(R.id.btn_sure);
		btn_cancel = (Button)findViewById(R.id.btn_cancel);
		btn_test = (Button)findViewById(R.id.btn_test);
		
		tv_show = (TextView)findViewById(R.id.tv_show);
		btn_sure.setText("绑定");
		btn_cancel.setText("解除绑定");
		
		btn_cancel.setOnClickListener(this);
		btn_sure.setOnClickListener(this);
		btn_test.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d(TAG, "连接断开中..");
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d(TAG, "连接中..");
			BackBinder bb =(BackBinder)service;
			bService = bb.getService();
			bService.moveOn();
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sure:
			Log.d(TAG, "btn_sure..");
			if(ifBind){
				Log.d(TAG, "解除绑定..");
				unbindService(conn);
			}
			Intent service = new Intent(this, BackgroundService.class);
			bindService(service, conn, BIND_AUTO_CREATE);
			ifBind = true;
			Log.d(TAG, "绑定..");
			break;
			
		case R.id.btn_cancel:
			Log.d(TAG, "btn_cancel..");
			if(ifBind){
			Log.d(TAG, "解除绑定..");
			unbindService(conn);
			ifBind = false;
			}
			break;
		case R.id.btn_test:
			Log.d(TAG, "测试..");
			service = new Intent(this, BackgroundService.class);
			bindService(service, conn, BIND_AUTO_CREATE);
			ifBind = true;
//			unbindService(conn);
//			ifBind = false;
			break;
		default:
			break;
		}
	}
}
