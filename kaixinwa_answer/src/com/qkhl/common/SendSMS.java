package com.qkhl.common;

import java.io.IOException;
import java.net.HttpCookie;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;

import com.qkhl.activity.RegisterPageActivity;

import android.annotation.SuppressLint;
import android.os.Message;
import android.util.Log;

public class SendSMS {
	private static String Url = "http://123.57.209.98/hlwh_android/sms.php?method=Submit";
	private String phone_number;
	private String message;

	public SendSMS(String phone_number) {
		this.phone_number = phone_number;
	}

	// 返回message
	public String get_message() {
		return message;
	}

	// 发送验证码
	public void send() {
		new Thread(new Runnable() {

			@SuppressLint("NewApi") @Override
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
					message = jObject.getString("msg");
					String smsid = jObject.getString("smsid");

					Log.e("tag", "code:"+code+"/br"+"message:"+message+"/br"+"smsid:"+smsid);
					Message msg = new Message();
					

					if ("提交成功".equals(message)) {
						mobile_code mc=new mobile_code(phone_number);
						mc.delete_mobile_code();
						msg.obj = message;
						RegisterPageActivity.smshandler.sendMessage(msg);
					}else{
						msg.obj = message;
						RegisterPageActivity.smshandler.sendMessage(msg);
					}

					

				} catch (HttpException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

}
