package com.yu.surprise.test;

import com.yu.surprise.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

public class TestScroll extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("Test", "Test onCreate..");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
	}
}
