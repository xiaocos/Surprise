package com.yu.surprise.showtips;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yu.surprise.R;
import com.yu.surprise.showtips.view.NumberProgressBar;
import com.yu.surprise.showtips.view.OnProgressBarListener;

public class TestTips extends Activity implements OnProgressBarListener {
	    private Timer timer;

	    private NumberProgressBar bnp;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_numberprogressbar);

	        bnp = (NumberProgressBar)findViewById(R.id.numberbar1);
	        bnp.setOnProgressBarListener(this);
	        timer = new Timer();
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                runOnUiThread(new Runnable() {
	                    @Override
	                    public void run() {
	                        bnp.incrementProgressBy(1);
	                    }
	                });
	            }
	        }, 1000, 100);
	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }

	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	        timer.cancel();
	    }

	    @Override
	    public void onProgressChange(int current, int max) {
	        if(current == max) {
	            Toast.makeText(getApplicationContext(), getString(R.string.finish), Toast.LENGTH_SHORT).show();
	            bnp.setProgress(0);
	        }
	    }
	}

