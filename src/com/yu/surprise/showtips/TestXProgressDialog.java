package com.yu.surprise.showtips;

import com.yu.surprise.R;
import com.yu.surprise.showtips.view.XProgressDialog;

import android.app.Activity;
import android.os.Bundle;

public class TestXProgressDialog extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testxprogressbar);

        XProgressDialog dialog = new XProgressDialog(this, "加载中..", XProgressDialog.THEME_CIRCLE_PROGRESS);
        dialog.show();
    }
}
