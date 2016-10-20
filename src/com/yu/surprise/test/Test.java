package com.yu.surprise.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.apache.http.HttpConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.yu.surprise.MyView;
import com.yu.surprise.R;
import com.yu.surprise.service.AlertService;
import com.yu.surprise.service.BackgroundService;
import com.yu.surprise.test.fragment.Upfragment;
import com.yu.surprise.util.AnamationUtil;
import com.yu.surprise.util.Constants;
import com.yu.surprise.util.Constants.MSGWHAT;
import com.yu.surprise.util.Utils;
import com.yu.surprise.util.WaitingUI;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

public class Test extends Activity implements Handler.Callback,
		View.OnTouchListener {
	private static final String TAG = "Test";
	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	float lineLenth = 0;
	float trangleArea = 0;
	public static final int _SELECT = 0x01;
	BluetoothAdapter btAdapter;
	ListView listView;
	List<Map<String, String>> list;
	int index;
	Handler handler;
	ArrayAdapter<String> arrAdpt;
	Intent m_intent;
	BackInit bi;
	public static int _flag = 0;
	/**接收按钮广播*/
	BroadcastReceiver br = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i("Test", "BroadcastReceiver br:" + intent.getAction());
			if (Constants.BTN1_BR_ACTION.equals(intent.getAction())) {
				Log.e("Test", "BroadcastReceiver BTN1_BR_ACTION");
				if (handler != null) {
//					handler.sendEmptyMessage(Constants.MSGWHAT.MSG_JSON);
					handler.sendEmptyMessage(MSGWHAT.MSG_INTENT);
				}
			}
			if (Constants.BTN2_BR_ACTION.equals(intent.getAction())) {
				Log.e("Test", "BroadcastReceiver BTN2_BR_ACTION");

			}
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e("Test", "onActivityResult requestCode:"+requestCode+"resultCode:"+resultCode);
		
	};

	public class BackInit extends AsyncTask<Object, Integer, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Utils.printTime("onPreExecute");
		}

		@Override
		protected String doInBackground(Object... params) {
			Utils.printTime("doInBackground");
			Utils.printTime("+++1");
			// for(long i = 0; i < 9999999l;i++){
			//
			// }
			// for(long i = 0; i < 9999999l;i++){
			//
			// }
			Utils.printTime("+++2");
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			Utils.printTime("onPostExecute");
			super.onPostExecute(result);
			setContentView(R.layout.test);
			WaitingUI.dissmissProgressDialog();
		}
	}

	public class ThreadBack extends Thread {
		Object lock = "";

		@Override
		public void run() {
			super.run();

			Utils.printTime("+++1");
			for (long i = 0; i < 9999999l; i++) {

			}
			for (long i = 0; i < 9999999l; i++) {

			}

			Utils.printTime("+++2");
		}

		public void doSTH() {
			setContentView(R.layout.test);
		}

		@Override
		public synchronized void start() {
			super.start();
			// 不可顺序执行
			synchronized (this) {
				doSTH();
			}
		}
	}

	public void showText(String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	private FragmentManager fragmentManager;
	private Fragment fragment_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("Test", "Test onCreate..");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity);
		handler = new Handler(this);
		/**include Upfragment BTN1 BTN2 broadcast*/
		IntentFilter int_fil = new IntentFilter(Constants.BTN1_BR_ACTION);
		int_fil.addAction(Constants.BTN2_BR_ACTION);
		registerReceiver(br, int_fil);
		// handler.sendEmptyMessage(0x00);

		// bi = new BackInit();
		// WaitingUI.showProgressDialog(this, "", "通讯中，请等待。。");
		// bi.execute(null,null,null);

	}

	@Override
	protected void onStart() {
		Log.e("Test", "Test onStart..");
		super.onStart();

	}

	@Override
	protected void onResume() {
		Log.e("Test", "Test onResume..");
		super.onResume();
		initView();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.e("Test", "Test onSaveInstanceState..");
		super.onSaveInstanceState(outState);

	}

	public void initView() {
		fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		if (fragment_content == null) {
			fragment_content = new Upfragment();
			transaction.add(R.id.content, fragment_content,"FRAG_CONT");
			transaction.show(fragment_content);
		} else {
			transaction.show(fragment_content);
		}
		transaction.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try {
			PackageManager pm = this.getPackageManager();
			PackageInfo pi;
			pi = pm.getPackageInfo(this.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
			menu.add(pi.versionName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.onCreateOptionsMenu(menu);
	}

	protected void onDestroy() {
		Log.e("Test", "Test onResume..");
		super.onDestroy();
		unregisterReceiver(br);
	}

	/**
	 * 打印Http头字段
	 * 
	 * @param http
	 */
	public static void printResponseHeader(HttpURLConnection http) {
		Map<String, String> header = getHttpResponseHeader(http);
		for (Map.Entry<String, String> entry : header.entrySet()) {
			String key = entry.getKey() != null ? entry.getKey() + ":" : "";
			print(key + entry.getValue());
		}
	}

	/**
	 * 获取Http响应头字段
	 * 
	 * @param http
	 * @return
	 */
	public static Map<String, String> getHttpResponseHeader(
			HttpURLConnection http) {
		Map<String, String> header = new LinkedHashMap<String, String>();
		for (int i = 0;; i++) {
			String mine = http.getHeaderField(i);
			if (null == mine)
				break;
			header.put(http.getHeaderFieldKey(i), mine);
		}
		return header;
	}

	// 打印日志
//	@TargetApi(value = 1)
	private static void print(String msg) {
		Log.w(TAG, msg);
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		default:
			break;
		case MSGWHAT.MSG_INTENT:
			Intent _intent = new Intent();
			/**action_main 搭配 category 启动系统应用**/
//			_intent.setAction(Intent.ACTION_MAIN);
//			_intent.addCategory(Intent.CATEGORY_APP_BROWSER);//浏览器
//			_intent.addCategory(Intent.CATEGORY_APP_CALCULATOR);//计算器
//			_intent.addCategory(Intent.CATEGORY_APP_CALENDAR);//日历
//			_intent.addCategory(Intent.CATEGORY_APP_CONTACTS);//联系人
//			_intent.addCategory(Intent.CATEGORY_APP_EMAIL);//邮件
//			_intent.addCategory(Intent.CATEGORY_APP_GALLERY);//相册
//			_intent.addCategory(Intent.CATEGORY_APP_MAPS);//未找到activity
//			_intent.addCategory(Intent.CATEGORY_APP_MARKET);//未找到activity
//			_intent.addCategory(Intent.CATEGORY_APP_MESSAGING);//未找到activity
//			_intent.addCategory(Intent.CATEGORY_APP_MUSIC);//音乐播放器
			startActivity(_intent);
			break;
		case Constants.MSGWHAT.MSG_NETWORKINFO:
			ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();
			cm.getAllNetworkInfo();
			if(ni!=null)
			Log.d("TEST", "ni.getType():"+ni.getType()+" OS:"+System.getenv("OS"));
			break;
		case Constants.MSGWHAT.MSG_HTTP:
			new Thread(new Runnable() {
				@Override
				public void run() {
					Utils.printTime(Utils.NetType(Test.this));
					HttpURLConnection conn = null;
					try {
						URL url;
						String str_u = "http://www.baidu.com/";
						url = new URL(str_u);
						conn = (HttpURLConnection) url
								.openConnection();

						conn.setConnectTimeout(5 * 1000);
						conn.setRequestMethod("GET");
						conn.setRequestProperty(
								"Accept",
								"image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
						conn.setRequestProperty("Accept-Language", "zh-CN");
						conn.setRequestProperty("Referer", str_u);
						conn.setRequestProperty("Charset", "UTF-8");
						conn.setRequestProperty(
								"User-Agent",
								"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
						conn.setRequestProperty("Connection", "Keep-Alive");
						conn.connect();
						printResponseHeader(conn);
						BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
						int count = 0;
						byte[] buffer = new byte[1024];
						ByteBuffer bb = ByteBuffer.allocate(1024);
						while((count=bis.read(buffer, 0, buffer.length))>0){
							bb.put(buffer, 0, count);
							Utils.printBytes("HttpURLConnection Read "+count+" bytes:", bb.array());
							String s = new String(Arrays.copyOf(buffer, count), "UTF-8");
							Log.e("Test", "s_length:"+s.length());
							Log.e("Test", "s:"+s);
						}
						Utils.printTime("READ OVER..");
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						conn.disconnect();
					}
				}
			}).start();
			break;
		case Constants.MSGWHAT.MSG_FTP:
			new Thread(new Runnable() {
				@Override
				public void run() {
					InputStream in = null;
					try {
						URL url = new URL(
								"ftp://mirror.csclub.uwaterloo.ca/index.html");
						URLConnection urlConnection = url.openConnection();
						in = new BufferedInputStream(urlConnection
								.getInputStream());
						byte[] buffer = new byte[1024];
						int read_num = 0;
						while ((read_num = in.read(buffer, 0, buffer.length)) > 0) {
							Utils.printBytes("ftp:", buffer);
							String s = new String(buffer, "UTF-8");
							Log.e("Test", "s:"+s);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							if (in != null)
								in.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
			break;
			
		case Constants.MSGWHAT.MSG_WEBVIEW:
			String url = "http://www.baidu.com";
			WebView wv = new WebView(this);
			wv.loadUrl(url);
			break;
			
		case Constants.MSGWHAT.MSG_ANAMATIONUTIL:
//			fragmentManager.findFragmentById(R.id.);
			fragmentManager = getFragmentManager();
			Fragment _upF = fragment_content;
			_upF.getView().setTag("_upFTag");
			Log.e("Test", "^^^_upF_tag:"+_upF.getTag());
			Fragment _temp_Frag = getFragmentManager().findFragmentByTag("FRAG_CONT");
//			Fragment _upF = getFragmentManager().findFragmentByTag("UpFragment");
			View v = _temp_Frag.getView().findViewById(R.id.textView1);
			if(_flag == 0){
				AnamationUtil.rotateView(v);
				_flag = 1;
			}else
			if(_flag == 1){
				AnamationUtil.rotateViewBack(v);
				_flag = 0;
			}
			break;
			
		case Constants.MSGWHAT.MSG_JSON:
			try {
//				
				
				JSONObject jo = new JSONObject();
				jo.put("id", 1);
				jo.put("name", "jackie");
				jo.put("num", 1);
				
				JSONArray ja = new JSONArray();
				ja.put(0, jo);
				ja.put(1, jo);
				ja.put(2, jo);
				ja.put(3, jo);
				ja.put(4, jo);
//				JsonReader jr = new JsonReader(new InputStreamReader(System.in));
//				JsonWriter jw = new JsonWriter(new OutputStreamWriter(System.out));
				System.out.print("");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		
		case MSGWHAT.MSG_PHONEMESSAGE:
			Method[] m = null;
			Field [] f = null;
			TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			SmsManager smsM = SmsManager.getDefault();
			Field _f = null;
			try {
				//mSubId 0 - 卡1 1 - 卡2
				_f = android.telephony.SmsManager.class.getDeclaredField("mSubId");
				_f.setAccessible(true);
				Log.e("Test", "mSubId:"+_f.get(smsM).toString());
				_f.set(smsM, 1);
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			PendingIntent sendItt = PendingIntent.getActivity(this, 0, getIntent(), PendingIntent.FLAG_UPDATE_CURRENT); 
			PendingIntent sendItt = PendingIntent.getBroadcast(Test.this, 0, new Intent(), 0); 
			PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0, getIntent(), PendingIntent.FLAG_UPDATE_CURRENT); 
			smsM.sendTextMessage("18672008142", "13010714500", "_f.set(smsM, 1);", sendItt, null);
			smsM.sendTextMessage("17190300244", "13010714500", "_f.set(smsM, 1);", sendItt, null);
//			try {
//				m = Class.forName("android.telephony.SmsManager").getMethods();
//				f = Class.forName("android.telephony.SmsManager").getFields();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			for(Field _f1 : f){
//				Log.e("Test", "_f1.getName():"+_f1.getName());
//			}
//			for(Method _m1 : m){
//				Log.e("Test", "_m1.getName():"+_m1.getName());
//			}
			break;
		case MSGWHAT.MSG_PHONECALL:
			Intent itt = new Intent(Intent.ACTION_CALL);
//			Intent itt = new Intent(Intent.ACTION_DIAL);
//			Intent itt = new Intent(Intent.ACTION_CALL_BUTTON);
			String phNum = "17112345678";
			Uri data = Uri.parse("tel:"+phNum);
			itt.setData(data);
			startActivity(itt);
			break;
			/**need android.permission.Account_Manager permission
			 * need system app id**/
		case MSGWHAT.MSG_ACCOUNT:
			AccountManager accm = (AccountManager)getSystemService(Context.ACCOUNT_SERVICE);
			break;
		case MSGWHAT.MSG_SEARCHABLEINFO:
			SearchManager sm = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
			List<SearchableInfo> list_si = sm.getSearchablesInGlobalSearch();
			for(SearchableInfo _si:list_si){
				Log.e("Test", " "+_si.getSearchActivity());
			}
			break;
		}
		return false;
	};

	@Override
	public void onBackPressed() {
		checkReturn();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.e("Test", "onKeyDown:" + keyCode);
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 检测返回事件
	 */
	private void checkReturn() {
		final Builder builder = new AlertDialog.Builder(Test.this);
		builder.setTitle("确定要退出程序？")
				// .setMessage("这是提示内容")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
						// 按钮事件
						m_intent = new Intent(Test.this,
								BackgroundService.class);
						// m_intent.setFlags(Intent.)
						stopService(m_intent);
						Test.this.finish();
						System.exit(0);
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
						// 按钮事件
						AlertDialog dialog = builder.create();
						dialog.dismiss();
					}
				});
		builder.show();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// Utils.printTime("getAction:"+String.valueOf(event.getAction()));
		// Utils.printTime("getActionIndex:"+String.valueOf(event.getActionIndex()));
		// Utils.printTime("getActionMasked:"+String.valueOf(event.getActionMasked()));
		// Utils.printTime("getPointerCount:"+String.valueOf(event.getPointerCount()));

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Utils.printTime("ACTION_DOWN - index:" + event.getActionIndex()
					+ " getPointerCount:" + event.getPointerCount() + "    ");
			if (event.getPointerCount() == 2) {
				float x1 = event.getX(0);
				float y1 = event.getY(0);
				float x2 = event.getX(1);
				float y2 = event.getY(1);

			}
			if (event.getPointerCount() > 2) {
				float x1 = event.getX(0);
				float y1 = event.getY(0);
				float x2 = event.getX(1);
				float y2 = event.getY(1);
				float x3 = event.getX(2);
				float y3 = event.getY(2);
				Utils.getTrangleArea(x1, y1, x2, y2, x3, y3);
			}
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			// Utils.printTime("ACTION_MOVE");
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Utils.printTime("ACTION_UP - index:" + event.getActionIndex()
					+ " getPointerCount:" + event.getPointerCount() + "    ");
		}
		if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
			Utils.printTime("ACTION_POINTER_DOWN - index:"
					+ event.getActionIndex() + " getPointerCount:"
					+ event.getPointerCount() + "    ");
		}
		if (event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
			Utils.printTime("ACTION_POINTER_UP - index:"
					+ event.getActionIndex() + " getPointerCount:"
					+ event.getPointerCount() + "    ");
		}

		return true;
	}
}
