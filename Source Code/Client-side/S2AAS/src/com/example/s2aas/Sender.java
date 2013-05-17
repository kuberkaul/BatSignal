package com.example.s2aas;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

public class Sender {

	public void postData(JSONObject obj) {
	    // Create a new HttpClient and Post Header

		String url = "http://ec2-54-234-200-191.compute-1.amazonaws.com:8080/S2A2S_RDS/InsertServlet";
	    HttpParams myParams = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(myParams, 10000);
	    HttpConnectionParams.setSoTimeout(myParams, 10000);
	    HttpClient httpclient = new DefaultHttpClient(myParams);

	    try {

	        HttpPost httppost = new HttpPost(url.toString());
	        httppost.setHeader("Content-type", "application/json");

	        StringEntity se = new StringEntity(obj.toString()); 
	        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	        httppost.setEntity(se);

	        HttpResponse response = httpclient.execute(httppost);
	        String temp = EntityUtils.toString(response.getEntity());
	        Log.i("tag", temp);
	        
	        Log.i("RESPONSE", Arrays.toString(response.getHeaders("error")));

	    } catch (ClientProtocolException e) {
	    	Log.i("Sender", Arrays.toString(e.getStackTrace()));

	    } catch (IOException e) {
	    	Log.i("Sender", Arrays.toString(e.getStackTrace()));
	    }
	}
	
	public void insert()
	{
		
	}
}