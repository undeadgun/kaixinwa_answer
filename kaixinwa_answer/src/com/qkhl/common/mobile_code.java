package com.qkhl.common;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Message;

public class mobile_code {
	private static String Url = "http://123.57.209.98/hlwh_android/delete_mobile_code.php";
	private String phone_number;
	public mobile_code(String phone_number) {
		this.phone_number = phone_number;
	}
	public void delete_mobile_code() {
		new Thread(new Runnable() {

			public void run() {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(Url);

				client.getParams().setContentCharset("UTF-8");
				
				
				method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

				NameValuePair[] data = { new NameValuePair("mobile",phone_number) };

				method.setRequestBody(data);

				try {
					client.executeMethod(method);
					String SubmitResult = method.getResponseBodyAsString();

					JSONObject jObject = new JSONObject(SubmitResult);
					String code = jObject.getString("code");

					Message msg = new Message();
					
//
//					if ("提交成功".equals(message)) {
//						msg.what = 1;
//					}else{
//						msg.obj = message;
//					}

					// RegisterPageActivity.phonenumerrhandle.sendMessage(msg);

				} catch (HttpException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}
