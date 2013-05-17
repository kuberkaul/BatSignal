package com.example.s2aas;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public EditText c1;
	public EditText c2;
	public EditText c3;
	public TextView finalMsg;
	public TextView setServices;
	public CheckBox bat;
	public CheckBox wreck;
	public Button button;
	public Context ctx;
	public TelephonyManager tm;
	public String number;
	Sender sender = new Sender();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		ctx = this;
		tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
		number = tm.getLine1Number();
		number = number.substring(1);
		try
		{
			ArrayList<String> list = new ArrayList<String>();
			list.add("u");
			list.add(number);
			list.add(1+"");
			list.add(1+"");
			JSONArray jsArray = new JSONArray(list);
			JSONObject json = new JSONObject();
			json.accumulate("jsArray", jsArray);
			sender.postData(json);
			Toast.makeText(ctx, "Registered with S2AAS successfully", Toast.LENGTH_LONG).show();
		}
		catch(Exception e)
		{
			Log.i("MainActivity",e.toString() + " " + e.getMessage() + Arrays.toString(e.getStackTrace()));
		}
		Log.i("MainActivity", "onCreate called...");
		c1 = (EditText)findViewById(R.id.editText1);
		c2 = (EditText)findViewById(R.id.editText2);
		c3 = (EditText)findViewById(R.id.editText3);
		setServices = (TextView)findViewById(R.id.textView6);
		bat = (CheckBox)findViewById(R.id.checkBox1);
		wreck = (CheckBox)findViewById(R.id.checkBox2);
		finalMsg = (TextView)findViewById(R.id.textView7);
		finalMsg.setVisibility(TextView.INVISIBLE);
		button = (Button)findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				boolean goAheadc1 = true;
				boolean goAheadc2 = true;
				boolean goAheadc3 = true;
				if(!wreck.isChecked() && !bat.isChecked())
				{
					Toast.makeText(ctx, "Select atleast one of the services", Toast.LENGTH_SHORT).show();
				}
				else if(c1.getText().toString().length() == 0 && c2.getText().toString().length() == 0 && c3.getText().toString().length() == 0)
				{
					Toast.makeText(ctx, "Enter atleast one emergency contact", Toast.LENGTH_SHORT).show();
				}
				else if(c1.getText().toString().length() != 0 && c1.getText().toString().length() != 10)
				{
					Toast.makeText(ctx, "Emergency Contact 1 invalid", Toast.LENGTH_SHORT).show();
					goAheadc1 = false;
				}
				else if(c2.getText().toString().length() != 0 && c2.getText().toString().length() != 10)
				{
					Toast.makeText(ctx, "Emergency Contact 2 invalid", Toast.LENGTH_SHORT).show();
					goAheadc2 = false;
				}
				else if(c3.getText().toString().length() != 0 && c3.getText().toString().length() != 10)
				{
					Toast.makeText(ctx, "Emergency Contact 3 invalid", Toast.LENGTH_SHORT).show();
					goAheadc3 = false;
				}
				else
				{
					try
					{
//						Toast.makeText(ctx, "Updated settings", Toast.LENGTH_SHORT).show();
						if(goAheadc1 && c1.getText().toString().length() == 10)
						{
							ArrayList<String> list = new ArrayList<String>();
							list.add("c");
							list.add(number);
							list.add(c1.getText().toString());
							JSONArray jsArray = new JSONArray(list);
							JSONObject json = new JSONObject();
							json.accumulate("jsArray", jsArray);
							sender.postData(json);
//							Toast.makeText(ctx, "Contact 1 added", Toast.LENGTH_SHORT).show();
						}
						if(goAheadc2 && c2.getText().toString().length() == 10)
						{
							ArrayList<String> list = new ArrayList<String>();
							list.add("c");
							list.add(number);
							list.add(c2.getText().toString());
							JSONArray jsArray = new JSONArray(list);
							JSONObject json = new JSONObject();
							json.accumulate("jsArray", jsArray);
							sender.postData(json);
//							Toast.makeText(ctx, "Contact 2 added", Toast.LENGTH_SHORT).show();
						}
						if(goAheadc3 && c3.getText().toString().length() == 10)
						{
							ArrayList<String> list = new ArrayList<String>();
							list.add("c");
							list.add(number);
							list.add(c3.getText().toString());
							JSONArray jsArray = new JSONArray(list);
							JSONObject json = new JSONObject();
							json.accumulate("jsArray", jsArray);
							sender.postData(json);
//							Toast.makeText(ctx, "Contact 3 added", Toast.LENGTH_SHORT).show();
						}
						finalMsg.setVisibility(TextView.VISIBLE);
						bat.setVisibility(CheckBox.INVISIBLE);
						wreck.setVisibility(CheckBox.INVISIBLE);
						setServices.setVisibility(CheckBox.INVISIBLE);
					}
					catch(Exception e)
					{
						Log.i("MainActivity",e.toString() + " " + e.getMessage());
					}
				}
				startSensing();
			}
		});
	}

	public void startSensing()
	{
		Intent intent = new Intent(ctx, S2A2SService.class);
		startService(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		return super.bindService(service, conn, flags);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
