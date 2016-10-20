package com.yu.surprise.util;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class Utils {
	public static final String SDCARD_PATH = Environment
			.getExternalStorageDirectory().getPath();
	private static final String TAG = "Test";
	
	public static void json(){
		try {
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
//			JsonReader jr = new JsonReader(new InputStreamReader(System.in));
//			JsonWriter jw = new JsonWriter(new OutputStreamWriter(System.out));
			System.out.print("");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	// 打印日志
	private static void print(String msg) {
		Log.w(TAG, msg);
	}
	//调试信息
	private static void debug(String msg){
		Log.d("Test", "####"+msg);
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

	/**
	 * print params of class package name class name class simple name method
	 * name field name constructor name interface name
	 * */
	static public void printAllInform(Class clsShow) {
		try {
			printTime("getPackage.getName:" + clsShow.getPackage().getName());
			printTime("getName:" + clsShow.getName());
			printTime("getSimpleName:" + clsShow.getSimpleName());
			// 取得所有方法
			Method[] hideMethod = clsShow.getMethods();
			int i = 0;
			if (hideMethod.length == 0) {
				printTime("no public Method in class and super class");
			} else {
				for (; i < hideMethod.length; i++) {
					printTime("method name:" + hideMethod[i].getName());
				}
			}
			// 取得所有常量
			Field[] allFields = clsShow.getFields();

			for (i = 0; i < allFields.length; i++) {
				printTime("Field name:" + allFields[i].getName());
			}
			Constructor[] allConstructor = clsShow.getConstructors();
			if (allConstructor.length == 0) {
				printTime("no public Constructor");
			} else {
				for (i = 0; i < allConstructor.length; i++) {
					printTime("Constructor name:" + allConstructor[i].getName());
				}
			}
			Class[] allInterface = clsShow.getInterfaces();
			if (allInterface.length == 0) {
				printTime("no Interface");
			} else {
				for (i = 0; i < allInterface.length; i++) {
					printTime("Interface name:" + allInterface[i].getName());
				}
			}

		} catch (SecurityException e) {
			// throw new RuntimeException(e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// throw new RuntimeException(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * If the given activity is running , return true;
	 * 
	 * @param activityClassName
	 *            full class name including package name
	 * */
	public static boolean isActivityRunning(Context mContext,
			String activityClassName) {
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> info = activityManager.getRunningTasks(1);
		if (info != null && info.size() > 0) {
			ComponentName component = info.get(0).topActivity;
			Log.e("Test",
					"component.getClassName():" + component.getClassName()
							+ " activityClassName:" + activityClassName);
			if (activityClassName.equals(component.getClassName())) {
				return true;
			}
		}
		return false;
	}

	// print bytes
	public static String printBytes(String comment, byte[] b) {
		StringBuilder sb = new StringBuilder();
		String tmp_s = "";
		for (int i = 0; i < b.length; i++) {
			tmp_s = String.valueOf(Integer.toHexString(b[i] & 0xff));
			if (tmp_s.length() < 2) {
				tmp_s = "0" + tmp_s;
			}
			sb.append(tmp_s + " ");
		}
		Log.e("Test", comment + ":" + sb.toString());
		return comment + sb;
	}

	public static void printInfo(String comment, Object info) {
		if (comment == null) {
			comment = "";
		}
		if (info == null) {
			info = "";
		}
		String methodName = Thread.currentThread().getStackTrace()[3]
				.getMethodName();
		String className = Thread.currentThread().getStackTrace()[1]
				.getClassName();
		Log.e("Test", className + " - " + methodName + " - " + comment + ":"
				+ info.toString());
	}

	private static final int O_RDONLY = 0x00;
	private static final int SCAN_COM = 1;
	private static final int B9600 = 9600;
	private static final int DATA_BITS8 = 8;
	private static final int NONE = 'N';
	private static final int STOP_BIT1 = 1;

	/*
	 * 扫描头读取数据类型属性
	 */
	private static final int SCAN_MODE = 0xFF000000;

	public static class Thread_getLog extends Thread {
		@Override
		public void run() {
			Log.e("Test", "Thread_getLog - SDCARD_PATH:" + SDCARD_PATH);
			// String exec_getlog =
			// "logcat > "+SDCARD_PATH+"/LOWVOLLog.txt -v time -s *:e";
			String exec_getlog = "logcat -v time -s *:e";
			Log.e("Test", "Thread_getLog - exec_getlog:" + exec_getlog);
			try {
				Process process = Runtime.getRuntime().exec(exec_getlog);
				InputStream is = process.getInputStream();
				boolean sdCardExist = Environment.getExternalStorageState()
						.equals(android.os.Environment.MEDIA_MOUNTED);
				File dir = null;
				if (sdCardExist) {
					dir = new File(Environment.getExternalStorageDirectory()
							.toString() + File.separator + "lowvol.txt");
					if (!dir.exists()) {
						dir.createNewFile();
					}
				}
				byte[] buffer = new byte[1024];
				int bytesLeft = 5 * 1024 * 1024;
				FileOutputStream fos = new FileOutputStream(dir);
				try {
					while (bytesLeft > 0) {
						int read = is.read(buffer, 0,
								Math.min(bytesLeft, buffer.length));
						if (read == -1)
							throw new EOFException("Unexpected end of data");

						fos.write(buffer, 0, read);
						bytesLeft -= read;
					}
				} finally {
					fos.close();
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
			super.run();
		}
	}

	/**
	 * 计算cs
	 * 
	 * @param data
	 *            需要计算的字节数组
	 * @param offset
	 * @param len
	 * @return
	 */
	public static int checksum(byte data[], int offset, int len) {
		int cs = 0;
		if (len + offset > data.length) {
			len = data.length - offset;
		}
		for (int i = offset; i < offset + len; i++) {
			cs += data[i];
		}
		return cs & 0xff;
	}

	/**
	 * 将二进制数组打印成字符串,带空格分割
	 * 
	 * @param data
	 * @param offset
	 * @param len
	 * @return
	 */
	public static String toHex(byte data[], int offset, int len) {
		StringBuilder sb = new StringBuilder();
		if (len + offset > data.length) {
			len = data.length - offset;
		}
		for (int i = offset; i < len; i++) {
			sb.append(String.format("%02x", data[i] & 0xff)).append(" ");
		}
		return sb.toString();
	}

	/**
	 * 将二进制数组打印成字符串,带空格分割
	 * 
	 * @param data
	 * @return
	 */
	public static String toHex(byte data[]) {
		return toHex(data, 0, data.length);
	}

	/**
	 * 将带空格分割的16进制表示的字符串转换为二进制数组
	 * 
	 * @param hexString
	 * @return
	 * @throws NumberFormatException
	 */
	public static byte[] fromHex(String hexString) throws NumberFormatException {
		String s[] = hexString.split(" ");
		byte ret[] = new byte[s.length];
		for (int i = 0; i < s.length; i++) {
			ret[i] = (byte) Integer.parseInt(s[i], 16);
		}
		return ret;
	}

	/**
	 * 将二进制数组打印成字符串,不带空格分割
	 * 
	 * @param data
	 * @param offset
	 * @param len
	 * @return
	 */
	public static String toHexNoSpace(byte data[], int offset, int len) {
		StringBuilder sb = new StringBuilder();
		if (len + offset > data.length) {
			len = data.length - offset;
		}
		for (int i = offset; i < len; i++) {
			sb.append(String.format("%02x", data[i] & 0xff));
		}
		return sb.toString();
	}

	/**
	 * 将二进制数组打印成字符串,补带空格分割
	 * 
	 * @param data
	 * @return
	 */
	public static String toHexNoSpace(byte data[]) {
		return toHexNoSpace(data, 0, data.length);
	}

	/**
	 * 将不带空格分割的16进制表示的字符串转换为二进制数组
	 * 
	 * @param hexString
	 * @return
	 * @throws NumberFormatException
	 */
	public static byte[] fromHexNoSpace(String hexString)
			throws NumberFormatException {
		byte ret[] = new byte[(hexString.length() + 1) / 2];
		for (int i = hexString.length(); i > 0; i -= 2) {
			ret[(i - 1) / 2] = (byte) Integer.parseInt(
					hexString.substring((i - 2) >= 0 ? (i - 2) : i - 1, i), 16);
		}
		return ret;
	}

	/**
	 * 反转数组
	 * 
	 * @param array
	 * @return
	 */
	public static byte[] reverse(byte[] array) {
		byte tmp[] = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			tmp[array.length - 1 - i] = array[i];
		}
		return tmp;
	}

	/**
	 * 为数组的每个元素自加0x33
	 * 
	 * @param data
	 * @param offset
	 * @param length
	 */
	public static void add33(byte data[], int offset, int length) {
		for (int i = offset; i < offset + length; i++) {
			data[i] += 0x33;
		}
	}

	/**
	 * 为数组的每个元素自加0x33
	 * 
	 * @param data
	 * @param offset
	 * @param length
	 */
	public static byte[] add33Return(byte data[], int offset, int length) {
		byte[] ret = new byte[length];
		for (int i = offset; i < offset + length; i++) {
			ret[i - offset] = (byte) ((byte) data[i] + (byte) 0x33);
		}
		return ret;
	}

	/**
	 * 为数组的每个元素自加0x33
	 * 
	 * @param data
	 * @param offset
	 * @param length
	 */
	public static byte[] add33ReverseReturn(byte data[], int offset, int length) {
		byte[] ret = add33Return(data, offset, length);
		ret = reverse(ret);
		return ret;
	}

	/**
	 * 为数组的每个元素自减0x33
	 * 
	 * @param data
	 * @param offset
	 * @param length
	 */
	public static void dec33(byte data[], int offset, int length) {
		for (int i = offset; i < offset + length; i++) {
			data[i] -= 0x33;
		}
	}

	/**
	 * 为数组的每个元素自减0x33
	 * 
	 * @param data
	 * @param offset
	 * @param length
	 */
	public static byte[] dec33Return(byte data[], int offset, int length) {
		byte[] ret = new byte[length];
		for (int i = offset; i < offset + length; i++) {
			ret[i - offset] = (byte) ((byte) data[i] - (byte) 0x33);
		}
		return ret;
	}

	/**
	 * 为数组的每个元素自加0x33
	 * 
	 * @param data
	 * @param offset
	 * @param length
	 */
	public static byte[] dec33ReverseReturn(byte data[], int offset, int length) {
		byte[] ret = dec33Return(data, offset, length);
		ret = reverse(ret);
		return ret;
	}

	/**
	 * 根据DA1，DA2返回所有的Pn值，以整数方式返回
	 * 
	 * @param DA1
	 * @param DA2
	 * @return
	 */
	public static List<Integer> getPns(int DA1, int DA2) {
		List<Integer> result = new ArrayList<Integer>();
		if (DA1 == 0 && DA2 == 0) {
			result.add(0);
		} else {
			for (int i = 0; i < 8; i++) {
				if (((1 << i) & DA1) == (1 << i)) {// 该位被设置了
					if (DA2 == 0x00) {
						for (int j = 0; j < 255; j++) {
							result.add(j * 8 + i + 1);
						}
					} else {
						result.add((DA2 - 1) * 8 + i + 1);
					}
				}
			}
		}
		return result;
	}

	/**
	 * 把pn转换为DA1，DA2，DA1存放在返回数组的0索引，DA2存放在返回数组的1索引
	 * 
	 * @return
	 */
	public static byte[] setPn(int pn) {
		byte[] result = new byte[2];
		if (pn == 0) {
			result[0] = 0;
			result[1] = 0;
		} else {
			result[0] = (byte) (1 << ((pn - 1) % 8));
			result[1] = (byte) ((pn - 1) / 8 + 1);
		}
		return result;
	}

	/**
	 * 把pn转换为DA1，DA2，DA1存放在返回数组的0索引，DA2存放在返回数组的1索引
	 * 
	 * @return
	 */
	public static void setPn(int pn, byte data[], int offset) {
		if (pn == 0) {
			data[offset] = 0;
			data[offset + 1] = 0;
		} else {
			data[offset] = (byte) (1 << ((pn - 1) % 8));
			data[offset + 1] = (byte) ((pn - 1) / 8 + 1);
		}
	}

	/**
	 * 根据DT1，DT2返回所有的Fn值，以整数方式返回
	 * 
	 * @param DT1
	 * @param DT2
	 * @return
	 */
	public static List<Integer> getFns(int DT1, int DT2) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			if (((1 << i) & DT1) == (1 << i)) {// 该位被设置了
				if (DT2 == 0xff) {
					for (int j = 0; j < 31; j++) {
						result.add(j * 8 + i + 1);
					}
				} else {
					result.add(DT2 * 8 + i + 1);
				}
			}
		}
		return result;
	}

	/**
	 * 把pn转换为DT1，DT2，DT1存放在返回数组的0索引，DT2存放在返回数组的1索引
	 * 
	 * @return
	 */
	public static byte[] setFn(int fn) {
		byte[] result = new byte[2];
		if (fn == 0) {
			result[0] = 0;
			result[1] = 0;
		} else {
			result[0] = (byte) (1 << ((fn - 1) % 8));
			result[1] = (byte) ((fn - 1) / 8);
		}
		return result;
	}

	/**
	 * 把pn转换为DT1，DT2，DT1存放在返回数组的offset索引，DT2存放在返回数组的offset+1索引
	 * 
	 * @return
	 */
	public static void setFn(int fn, byte data[], int offset) {
		if (fn == 0) {
			data[offset] = 0;
			data[offset + 1] = 0;
		} else {
			data[offset] = (byte) (1 << ((fn - 1) % 8));
			data[offset + 1] = (byte) ((fn - 1) / 8);
		}
	}

	public static String A1(byte data[]) {
		return String.format("%02x-%02x-%02x %02x:%02x:%02x 周%d", data[5],
				data[4] & 0x1f, data[3], data[2], data[1], data[0],
				(data[4] & 0xe0) >> 5);
	}

	public static String A11(byte data[]) {
		String string = String.format("%02x年%02x月%02x日  %02x时%02x分%02x秒 星期%d",
				data[5], data[4] & 0x1f, data[3], data[2], data[1], data[0],
				(data[4] & 0xe0) >> 5);
		string = string.replace("期0", "期日");
		string = string.replace("期1", "期一");
		string = string.replace("期2", "期二");
		string = string.replace("期3", "期三");
		string = string.replace("期4", "期四");
		string = string.replace("期5", "期五");
		string = string.replace("期6", "期六");
		return string;
	}

	public static String A14(byte data[]) {
		return String.format("%02x%02x%02x.%02x%02xkWh", data[4], data[3],
				data[2], data[1], data[0]);
	}

	public static String A20(byte data[]) {
		return String.format("%02x年%02x月%02x日", data[2], data[1], data[0]);
	}

	/**
	 * 二进制转换成16进制
	 * 
	 * @param bString
	 * @return
	 */
	public static String binaryString2hexString(String bString) {
		if (bString == null || bString.equals("") || bString.length() % 8 != 0)
			return null;
		StringBuffer tmp = new StringBuffer();
		int iTmp = 0;
		for (int i = 0; i < bString.length(); i += 4) {
			iTmp = 0;
			for (int j = 0; j < 4; j++) {
				iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
			}
			tmp.append(Integer.toHexString(iTmp));
		}
		return tmp.toString();
	}

	/**
	 * 十六进制转换成二进制
	 */
	public static String binaryString(byte data) {
		String s = "";
		if (Integer.toBinaryString(byte2ten(data)).length() < 8) {
			for (int i = 1; i <= 8 - Integer.toBinaryString(byte2ten(data))
					.length(); i++) {
				s += "0";
			}
		}
		String ss = s + Integer.toBinaryString(byte2ten(data));
		return ss;
	}

	/**
	 * 二进制转换成十进制
	 */
	public static String binaryString2ten(String data) {
		BigInteger src = new BigInteger(data, 2);// 转换为BigInteger类型
		return src.toString();
	}

	/**
	 * byte转换成10进制
	 */
	public static int byte2ten(byte data) {
		return (int) data & 0xff;
	}

	/**
	 * 字符串反转
	 */
	public static String strReverse(String str) {
		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * 获取异常的描述字符串，即{@link Throwable#getMessage()}。如果为null，那么就从
	 * {@link Throwable#getCause()}中获取，以此类推，往上5层终止。
	 * 
	 * @param t
	 * @return
	 */
	public static String getExceptionMsg(Throwable t) {
		String msg = t.getClass().getName();
		for (int i = 0; i < 5; i++) {
			if (t.getMessage() == null) {
				if (t.getCause() != null) {
					t = t.getCause();
				} else {
					break;
				}
			} else {
				msg = t.getMessage();
			}
		}
		return msg;
	}

	/**
	 * 
	 * 
	 * @param str
	 * @param length
	 * @return str
	 */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		StringBuffer sb = null;
		while (strLen < strLength) {
			sb = new StringBuffer();
			sb.append("0").append(str);// 左(前)补0
			// sb.append(str).append("0");//右(后)补0
			str = sb.toString();
			strLen = str.length();
		}
		return str;
	}

	/**
	 * 字符串后面加0
	 */
	public static String addZero(String str, int strLength) {
		int strLen = str.length();
		StringBuffer sb = null;
		while (strLen < strLength) {
			sb = new StringBuffer();
			// sb.append("0").append(str);// 左(前)补0
			sb.append(str).append("0");// 右(后)补0
			str = sb.toString();
			strLen = str.length();
		}
		return str;
	}

	public static byte dec33FromByte(byte b) {
		return (byte) (b - 0x33);
	}

	// 字符串按原文10进制转换成16进制字节
	public static byte[] stringToDecBytes(String s) {
		for (int w = 0; w < s.length(); w++) {
			if (s.charAt(0) == '0') {
				s = s.substring(1);
			}
			if (s.charAt(0) != '0') {
				break;
			}
		}
		String ret = s.replaceAll("[^0-9a-fA-F]", "");
		if (ret.length() % 2 != 0) {
			ret = "0".concat(ret);
		}
		// System.out.println("s:" + s + " ret:" + ret);
		byte[] b = new byte[ret.length() / 2];
		for (int k = 0; k < ret.length() / 2; k++) {
			// System.out.println("k:"+k);
			b[k] = (byte) (0xff & Integer.parseInt(
					ret.substring(k * 2, k * 2 + 2), 10));
		}
		return b;
	}

	// 字符串按原文16进制转换成16进制字节 已去除高位字符0
	public static byte[] stringToHexBytes(String s) {
		for (int w = 0; w < s.length(); w++) {
			if (s.charAt(0) == '0') {
				s = s.substring(1);
			}
			if (s.charAt(0) != '0') {
				break;
			}
		}
		String ret = s.replaceAll("[^0-9a-fA-F]", "");
		if (ret.length() % 2 != 0) {
			ret = "0".concat(ret);
		}
		// System.out.println("s:" + s + " ret:" + ret);
		byte[] b = new byte[ret.length() / 2];
		for (int k = 0; k < ret.length() / 2; k++) {
			// System.out.println("k:"+k);
			b[k] = (byte) (0xff & Integer.parseInt(
					ret.substring(k * 2, k * 2 + 2), 16));
		}
		return b;
	}

	// 逆置 减33
	public static byte[] reverseMinus33(byte[] data) {
		byte[] ret = new byte[data.length];
		ret = reverse(data);
		for (int i = 0; i < ret.length; i++) {
			ret[i] -= 0x33;
		}
		return ret;
	};

	// 逆置 加三三
	public static byte[] reverseAdd33(byte[] data) {
		byte[] ret = new byte[data.length];
		ret = reverse(data);
		for (int i = 0; i < ret.length; i++) {
			ret[i] += 0x33;
		}
		return ret;
	};

	// int to one byte bcd lowbit
	public static byte intToByteBCD(int i) {
		byte ret = 0x00;
		int tmp = i % 10;
		ret |= tmp;
		tmp = (i / 10) % 10;
		ret = (byte) (ret | ((tmp & 0x00ff) << 4));
		return ret;
	}

	// int to 2bytes BCD reverse
	public static byte[] intToByte2BCD(int i) {
		byte[] ret = { 0x00, 0x00 };
		int tmp = i % 10;
		ret[0] |= tmp;
		tmp = (i / 10) % 10;
		ret[0] = (byte) (ret[0] | ((tmp & 0x00ff) << 4));
		tmp = (i / 100) % 10;
		ret[1] |= tmp;
		tmp = (i / 1000) % 10;
		ret[1] = (byte) (ret[1] | ((tmp & 0x00ff) << 4));
		return ret;
	}

	// int to 3bytes BCD
	public static byte[] intToByte3BCDReverse(int i) {
		byte[] ret = { 0x00, 0x00, 0x00 };
		int tmp;
		int left = i;
		for (int k = 0; k < 6; k++) {
			tmp = left % 10;
			left = left / 10;
			if (k % 2 == 0)
				ret[k / 2] = (byte) (ret[k / 2] | (tmp & 0x0f));
			else
				ret[k / 2] = (byte) (ret[k / 2] | (tmp & 0x0f) << 4);
		}
		return ret;
	}

	// BCD 2bytes addr reverse to int
	public static int byte2BCDReverseToInt(byte b_left, byte b_right) {
		int ret = 0;
		ret = ((b_right & 0x000f) * 100) + (((b_right & 0x00f0) >> 4) * 1000)
				+ (((b_left & 0x00f0) >> 4) * 10) + (b_left & 0x000f);
		return ret;
	}

	// BCD 3bytes addr reverse to int
	public static int byte3BCDReverseToInt(byte b_highbyte, byte b_midbyte,
			byte b_lowbyte) {
		int ret = 0;
		ret = ((b_lowbyte & 0x000f) * 10000)
				+ (((b_lowbyte & 0x00f0) >> 4) * 100000)
				+ ((b_midbyte & 0x000f) * 100)
				+ (((b_midbyte & 0x00f0) >> 4) * 1000)
				+ (((b_highbyte & 0x00f0) >> 4) * 10) + (b_highbyte & 0x000f);
		return ret;
	}

	// imei intercept last 12 number
	public static byte[] makeImeiToAddr(String str_imei) {
		byte[] ret = new byte[6];
		if (str_imei.length() < 12) {
			return ret;
		}
		str_imei = str_imei.substring(3);
		String tmp_str;
		for (int i = 0; i < 6; i++) {
			tmp_str = str_imei.substring(0 + i * 2, 2 + i * 2);
			// ret[5 - i] = Byte.decode("0x" + tmp_str);//超出126解析出错
			ret[5 - i] = (byte) (Integer.valueOf(tmp_str).byteValue());
		}
		return ret;
	}

	public static int byteToBCD(byte b) {
		int ret = 0;
		ret = (b & 0x0f) + (((b & 0xf0) >> 4) * 10);
		return ret;
	}

	/**
	 * @函数说明:去掉数组前面的零元素，以第一个非零元素开始，截取剩下的部分
	 * @param:byte[]数组
	 * @return:byte[]数组
	 * */
	public static byte[] cleanZero(byte[] data) {
		if (data == null) {
			return null;
		}
		byte[] tmp;
		int count = data.length;
		for (int i = 0; i < data.length; i++) {
			if (data[i] == 0) {
				count--;
				continue;
			}
			if (data[i] != 0) {
				break;
			}
		}
		tmp = new byte[count];
		for (int i = (data.length - count), k = 0; i < data.length; i++, k++) {
			tmp[k] = data[i];
		}
		for (int i = 0; i < tmp.length; i++) {
			System.out.println("cleanZero-tmp[]:" + tmp[i]);
		}
		return tmp;
	}

	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;
		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}

	/** */
	/**
	 * @函数功能: 10进制串转为BCD码
	 * @输入参数: 10进制串
	 * @输出结果: BCD码
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * 将10进制转换为BCD字节数组（BCD字节数组倒序）
	 * 
	 * @param Dec
	 *            要转换的数
	 * @param length
	 *            转换后BCD字节数组的长度
	 * @return
	 */
	public static byte[] DectoBCD(Double Dec, int length) {
		byte[] Bcd = new byte[length];
		int i;
		int temp;
		for (i = 0; i < length; i++) {
			temp = (int) (Dec % 100);
			Bcd[i] = (byte) (((temp / 10) << 4) + ((temp % 10) & 0x0F));
			Dec /= 100;
		}
		return Bcd;
	}

	public static String getCellData(HSSFRow row, int col) {
		String str = "";
		// int
		if (row.getCell(col).getCellType() == 0) {
			double dbl = row.getCell(col).getNumericCellValue();
			str = String.format("%.0f", dbl);
			str = trim_7Fchar(str);
			// Log.e("trim", "type0:"+Integer.toHexString((int)str.charAt(0)));
			return str;
		}
		if (row.getCell(col).getCellType() == 1) {
			str = row.getCell(col).getStringCellValue();
			str = trim_7Fchar(str);
			// Log.e("trim", "type1:"+Integer.toHexString((int)str.charAt(0)));
			return str;
		}
		str = row.getCell(col).getStringCellValue();
		str = str.trim();
		return str;
	}

	// trim ascII 0x0a
	public static String trim_7Fchar(String str) {
		if (str == null) {
			return "";
		}
		byte[] b = str.getBytes();
		int start = 0, end = b.length;
		for (int w = 0; w < b.length; w++) {
			if (((int) (b[w + 1] & 0xff) == (int) 0xa0)
					&& ((int) (b[w] & 0xff) == (int) 0xc2)) {
				System.out.println("+2");
				start += 2;
				w++;
			} else if ((int) (b[w] & 0xff) <= (int) 0x20) {
				System.out.println("++");
				start++;
			} else {
				break;
			}
			;
		}
		for (int r = b.length - 1; r >= 0; r--) {
			if (((int) (b[r] & 0xff) == (int) 0xa0)
					&& ((int) (b[r - 1] & 0xff) == (int) 0xc2)) {
				System.out.println("-2");
				end -= 2;
				r--;
			} else if ((int) (b[r] & 0xff) <= (int) 0x20) {
				System.out.println("--");
				end--;
			} else {
				break;
			}
		}
		return new String(b, start, end - start, Charset.forName("UTF-8"));
	}

	public static final JSONArray fromListToJSONArray(
			List<Map<String, Object>> list) throws JSONException {
		if (list == null || "".equals(list)) {
			return null;
		}
		JSONArray jsonArr = new JSONArray();
		Map<String, Object> map_tmp = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			map_tmp = list.get(i);
			jsonArr.put(fromMapToJSONObj(map_tmp));
		}
		return jsonArr;
	}

	public static final JSONObject fromMapToJSONObj(Map<String, Object> map)
			throws JSONException {
		JSONObject jsonObj = new JSONObject();
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (key != null) {
				// System.out.println("key:" + key);
				if (map.get(key) != null) {
					jsonObj.put(key, map.get(key));
				} else {
					jsonObj.put(key, "");
				}
			} else {
				System.out.println("key:null");
			}

		}
		return jsonObj;
	}

	/**
	 * 打印时间
	 * */
	public static final void printTime(String tag) {
		Log.e("Test", tag + ":" + getStrTime());
	}

	/**
	 * 获取格式化时间字符串
	 * */
	public static final String getStrTime() {
		Date nowTime = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss:ms");
		return time.format(nowTime);
	}

	// byte[0]-byte[5]:YY MM DD hh mm ss
	public static final byte[] getTime() {
		byte[] ret = new byte[6];
		Calendar c = Calendar.getInstance();
		ret[0] = intToByteBCD(c.get(Calendar.YEAR));
		ret[1] = intToByteBCD(c.get(Calendar.MONTH) + 1);
		ret[2] = intToByteBCD(c.get(Calendar.DAY_OF_MONTH));
		ret[3] = intToByteBCD(c.get(Calendar.HOUR_OF_DAY));
		ret[4] = intToByteBCD(c.get(Calendar.MINUTE));
		ret[5] = intToByteBCD(c.get(Calendar.SECOND));
		return ret;
	}

	// 获取当前日往前倒推7天日期
	public static final String get7DaysTerm() {
		StringBuffer sb = new StringBuffer();
		Calendar c = Calendar.getInstance();
		int Y = c.get(Calendar.YEAR);
		int M = c.get(Calendar.MONTH) + 1;
		int D = c.get(Calendar.DAY_OF_MONTH);
		int h = c.get(Calendar.HOUR_OF_DAY);
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		int M1 = M;
		int D1 = D - 7 + 1;
		c.set(Calendar.MONTH, M - 2);
		c.set(Calendar.DATE, 1);
		c.roll(Calendar.DATE, -1);
		int maxDate = c.get(Calendar.DATE);
		if (D1 <= 0) {
			D1 = maxDate + D1;
		}
		sb.append(String.valueOf(D1) + "日" + "-" + String.valueOf(D) + "日");
		return sb.toString();
	}

	// 获取日期星期为4字节
	public static final byte[] getDateWeek() {
		byte[] ret = new byte[4];
		StringBuffer sb = new StringBuffer();
		Calendar c = Calendar.getInstance();
		ret[0] = intToByteBCD(c.get(Calendar.YEAR));
		ret[1] = intToByteBCD(c.get(Calendar.MONTH) + 1);
		ret[2] = intToByteBCD(c.get(Calendar.DAY_OF_MONTH));
		ret[3] = intToByteBCD(c.get(Calendar.DAY_OF_WEEK) - 1);
		return ret;
	}

	// 解析时间数据帧 hhmmss
	public static final String getTime(byte[] b) {
		if (b.length < 3) {
			b = new byte[] { 0x0, 0x0, 0x0 };
		}
		byte[] ret = dec33ReverseReturn(b, 0, 3);
		StringBuilder sb = new StringBuilder();
		for (int w = 0; w < 3; w++) {
			int m = byteToBCD(ret[w]);
			sb.append(String.valueOf(m));
			if (w != 2) {
				sb.append(":");
			}
		}
		return sb.toString();
	}

	// 设置字符串解析
	public static final byte[] getSettingTime(String s) {
		if (s.length() < 5) {
			return new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff };
		}
		String[] str = s.split(":");
		byte[] ret = new byte[3];
		if (str.length < 3) {
			return new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff };
		} else {
			try {
				ret[0] = intToByteBCD(Integer.valueOf(str[0]));
				ret[1] = intToByteBCD(Integer.valueOf(str[1]));
				ret[2] = intToByteBCD(Integer.valueOf(str[2]));
			} catch (NumberFormatException e) {
				return new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff };
			}
		}
		return ret;
	}

	/**
	 * 检查电表地址格式
	 * */
	public static final boolean checkMeterAddr(String str) {
		String format = "\\d{12}";
		if (str.matches(format)) {
			return true;
		}
		return false;
	}

	/**
	 * return trangle area , absolute value
	 * */
	public static float getTrangleArea(float x1, float y1, float x2, float y2,
			float x3, float y3) {
		float area = 0;
		area = (x1 * y2 + y1 * x3 + x2 * y3) - (x1 * y3 + y2 * x3 + y1 * x2);
		area = Math.abs(area);
		return area;
	};

	/**
	 * return trangle area , absolute value
	 * */
	public static float getLineLength(float x1, float y1, float x2, float y2) {
		float lineLength = 0;
		lineLength = (float) Math.sqrt((double) (Math.abs(y2 - y1) + Math
				.abs(x2 - x1)));
		lineLength = Math.abs(lineLength);
		return lineLength;
	};

	/**
	 * 解码base64编码 存到一维数组 b[] 一维数组b[]存成二维数组Q[][]
	 * 
	 * */
	public static void decodeBase64() {
		String s = "U2FsdGVkX1+GwlYSkb6ewlmShlAAR+k1oKV87HFVoZlaCLjKUa3RsXxMlzs88xv2gvX9wXRao4SLaiOyB8l13w==";
		byte[] b = android.util.Base64.decode(s, 0);
		Utils.printBytes("ret:", b);
		byte[][] Q = new byte[8][8];
		for (int w = 0; w < Q.length; w++) {
			for (int t = 0; t < Q[w].length; t++) {
				Q[w][t] = b[(w) * Q[w].length + t];
				String str = Integer.toHexString(Q[w][t] & 0xff);
				if (str.length() == 1) {
					str = "0" + str;
				}
				System.out.print(str + " ");
				if (t == Q[w].length - 1) {
					System.out.println();
				}
			}
		}
	}
	/**
	 * 自动安装
	 * **/
	public static void update(Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), Constants.FILE_NAME)),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	/**
	 * 自动安装
	 * **/
	public static void update(Context context,String
			 file_path,String file_name){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(file_path, file_name)),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	/**
	 * 自动安装
	 * **/
	public static void update(Context context,File file){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	/**
	 * @func 获取网路类型
	 * 需要权限 android.permission.access_network_state
	 * **/
	public static String NetType(Context context){
		ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		String typeName = info.getTypeName().toLowerCase();
		if("wifi".equalsIgnoreCase(typeName)){
		
		}else{
			typeName = info.getExtraInfo().toLowerCase();
		}
		return typeName;
	}
}
