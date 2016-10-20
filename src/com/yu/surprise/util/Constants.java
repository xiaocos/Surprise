package com.yu.surprise.util;

import android.os.Environment;

public class Constants {
	public static final String BTN1_BR_ACTION = "com.yu.surprise.test.fragment.Upfragment_btn1";
	public static final String BTN2_BR_ACTION = "com.yu.surprise.test.fragment.Upfragment_btn2";
	public static final String SDCARD_PATH = Environment
			.getExternalStorageDirectory().getPath();
	public static final String FILE_NAME = "LowVolLineAnalyzer1.1.6.apk";
	
	public class MSGWHAT {
		public static final int MSG_HTTP = 0x00;
		public static final int MSG_FTP = 0x01;
		public static final int MSG_WEBVIEW = 0x02;
		public static final int MSG_ANAMATIONUTIL = 0x03;
		public static final int MSG_JSON = 0x04;
		public static final int MSG_PHONEMESSAGE = 0x05;
		public static final int MSG_PHONECALL = 0x06;
		public static final int MSG_ACCOUNT = 0x07;
		public static final int MSG_SEARCHABLEINFO = 0x08;
		public static final int MSG_NETWORKINFO = 0x09;
		public static final int MSG_INTENT = 0x0a;
		
	}
}
