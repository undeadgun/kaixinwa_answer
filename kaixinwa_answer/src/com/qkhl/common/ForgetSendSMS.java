package com.qkhl.common;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import com.qkhl.activity.ForgetPassword;

import android.annotation.SuppressLint;
import android.os.Message;
import android.util.Log;

public class ForgetSendSMS {
	
	private static String Url = "http://123.57.209.98/qkhl_api/index.php/SMSServer/sendSMS";
	private String phone_number;
	private String message;

	public ForgetSendSMS(String phone_number) {
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

				NameValuePair[] data = { new NameValuePair("post_code",Constant.MIYAO),
						new NameValuePair("phone_num",phone_number)
						};

				method.setRequestBody(data);

				try {
					client.executeMethod(method);
					String SubmitResult = method.getResponseBodyAsString();

					JSONObject jObject = new JSONObject(SubmitResult);
					String code = jObject.getString("code");
					message = jObject.getString("message");
//					String smsid = jObject.getString("smsid");

					Log.e("tag", "code:"+code+"/br"+"message:"+message+"/br");
					Message msg = Message.obtain();
					

					if ("101".equals(code)) {
//						mobile_code mc=new mobile_code(phone_number);
//						mc.delete_mobile_code();
						msg.obj = message;
						ForgetPassword.forgetphonenumerrhandle.sendMessage(msg);
					}else{
						msg.obj = message;
						ForgetPassword.forgetphonenumerrhandle.sendMessage(msg);
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
