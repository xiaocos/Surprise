package com.yu.surprise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DynamicAddActivity extends Activity implements View.OnClickListener{
	TextView tv_first;
	Button btn_add ;
	LinearLayout ll ;
	int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, View.INVISIBLE);
		setContentView(R.layout.dynamic);
		initView();
	}
	
	private void initView(){
		tv_first = (TextView)findViewById(R.id.tv_show);
		btn_add = (Button)findViewById(R.id.btn_add);
		ll = (LinearLayout)findViewById(R.id.linear_add);
		position = 0;
		btn_add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add:
			TextView tv_tmp = new TextView(this);
			tv_tmp.setText(String.valueOf(position)+"new text message");
			ll.addView(tv_tmp, ++position, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//			setContentView(R.id.linear_add);
			break;
		
		
		default:
			break;
		}
	}
}
