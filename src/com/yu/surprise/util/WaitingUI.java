package com.yu.surprise.util;

import android.app.ProgressDialog;
import android.content.Context;

public class WaitingUI {
	static ProgressDialog proDialog;
	public static void showProgressDialog(Context c,String title,String message){
		if(title == null)title = "";
		if(message == null)message = "";
		proDialog = new ProgressDialog(c);
		proDialog.setCancelable(true);
		proDialog.setCanceledOnTouchOutside(false);
		proDialog.setTitle(title);
		proDialog.setMessage(message);
		proDialog.show();
	}
	public static void dissmissProgressDialog(){
		if(proDialog!=null){
			proDialog.dismiss();
		}
	};
}
