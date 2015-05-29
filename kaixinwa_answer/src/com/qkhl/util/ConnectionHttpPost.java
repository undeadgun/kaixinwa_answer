package com.qkhl.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import com.qkhl.activity.update_userinfoActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ConnectionHttpPost {
	
	public  Handler rename;
	

	public  void login(final String url,final String upkey,final String value) {
		new Thread(new Runnable() {

			@Override
			public void run() {

				
				// http://123.57.209.98/hlwh_android/login.php?format=xml

				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(url);

				// client.getParams().setContentCharset("GBK");
				client.getParams().setContentCharset("UTF-8");
				method.setRequestHeader("ContentType",
						"application/x-www-form-urlencoded;charset=UTF-8");

				NameValuePair[] data = { new NameValuePair("upkey", upkey),
						new NameValuePair("value", value),
						 };
				
				method.setRequestBody(data);

				try {
					client.executeMethod(method);
					String SubmitResult = method.getResponseBodyAsString();
					JSONObject jObject = new JSONObject(SubmitResult);
					String code = jObject.getString("code");
					String mess = jObject.getString("message");
					// SubmitResult=jObject.getString("message");
					// SubmitResult=jObject.getString("data");
					Log.e("TAG", code + "code");
					Log.e("TAG", mess);
					Message msg = new Message();
//					msg.what=1;
					msg.obj = mess;
					update_userinfoActivity.rename.sendMessage(msg);

				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

}
