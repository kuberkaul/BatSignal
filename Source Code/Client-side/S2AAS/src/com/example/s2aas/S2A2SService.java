package com.example.s2aas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.mysql.jdbc.Statement;

public class S2A2SService extends Service implements SensorEventListener{
	private SensorManager sensorManager;
	Notification n;
	NotificationManager nm;
	Statement statement;
	String number;
	Sender sender = new Sender();
	
	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {

			long level = intent.getIntExtra("level", 0);
			Log.i("Battery info", String.valueOf(level) + "%");
			
			TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
			number = tm.getLine1Number();
			number = number.substring(1);
			
			try
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
				Calendar calendar = Calendar.getInstance();
				String date = dateFormat.format(calendar.getTime());
				Log.i("date",date);
				ArrayList<String> list = new ArrayList<String>();
				list.add("b");
				list.add(number);
				list.add(date);
				list.add(level+"");
				JSONArray jsArray = new JSONArray(list);
				JSONObject json = new JSONObject();
				json.accumulate("jsArray", jsArray);
				sender.postData(json);
			}
			catch(Exception e)
			{
				Log.i("S2A2SService",e.toString() + "----->" + e.getMessage() + Arrays.toString(e.getStackTrace()));
			}
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("S2A2SService", "onCreate called...");
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		this.registerReceiver(this.mBatInfoReceiver, 
	    		new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		Log.i("S2A2SService", "onCreate done");
		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
		Log.i("S2A2SService", "onStart called...");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mBatInfoReceiver);
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	  public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			
			float[] values = event.values;
			float x = values[0];
			float y = values[1];
			float z = values[2];
			
			try
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
				Calendar calendar = Calendar.getInstance();
				String date = dateFormat.format(calendar.getTime());
				ArrayList<String> list = new ArrayList<String>();
				list.add("w");
				list.add(number);
				list.add(date);
				list.add(x+"");
				list.add(y+"");
				list.add(z+"");
				JSONArray jsArray = new JSONArray(list);
				JSONObject json = new JSONObject();
				json.accumulate("jsArray", jsArray);
				sender.postData(json);
			}
			catch(Exception e)
			{
				Log.i("S2A2SService",e.toString() + "----->" + e.getMessage() + Arrays.toString(e.getStackTrace()));
			}
			
			Log.i("Linear Acceleration data changed!", values.toString());
	    }
	  }
}