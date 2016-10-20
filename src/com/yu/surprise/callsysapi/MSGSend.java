package com.yu.surprise.callsysapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MSGSend extends android.app.Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	/**
	 * 
	 * 跳转到消息发送页面
	 * 
	 * */
	public void jumpToSmsSend(){
		Uri uri = Uri.parse("smsto:17190300244");
		Intent it = new Intent(Intent.ACTION_SENDTO,uri);
		it.putExtra("sms_body", "I am a new Message.");
		startActivity(it);
	}
}
