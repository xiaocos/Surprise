package com.yu.surprise.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class BackTask extends AsyncTask<Object, Object, Object>{
	ProgressDialog proDialog ;
	Object lock = "";
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Utils.printTime("onPreExecute");
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		Utils.printTime("doInBackground");
		proDialog = new ProgressDialog((Context)params[0]);
		proDialog.setCancelable(true);
		proDialog.setCanceledOnTouchOutside(false);
		proDialog.setTitle("");
		proDialog.setMessage("导入中,请稍等..");
		proDialog.show();
		return null;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		Utils.printTime("onPostExecute");
		super.onPostExecute(result);
		proDialog.dismiss();
	}
	
}
