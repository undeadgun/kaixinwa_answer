package com.qkhl.activity;

import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import com.qkhl.common.Constant;
import com.qkhl.kaixinwa_android.R;
import com.qkhl.util.CreateInfo;
import com.qkhl.util.MD5Utils;
import com.qkhl.util.SharePreferUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPageAcitivity extends Activity implements OnClickListener {

	private Button denglu;
	private TextView regist, forgetpassword;
	private EditText phonenum, password;
	private Handler logintishi;
	private String input;
	private String pnum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loginpage);
		initialization();
	}

	private void initialization() {
		denglu = (Button) findViewById(R.id.dengluanniu);
		regist = (TextView) findViewById(R.id.zhucebutton);
		forgetpassword = (TextView) findViewById(R.id.forgetmima);
		phonenum = (EditText) findViewById(R.id.phonenumber);
		password = (EditText) findViewById(R.id.passw);

		phonenum.setTypeface(Typeface.SANS_SERIF);

		pnum = phonenum.getText().toString().trim();

		password.setTypeface(Typeface.SANS_SERIF);
		
		regist.setOnClickListener(this);
		forgetpassword.setOnClickListener(this);
		phonenum.setOnClickListener(this);
		password.setOnClickListener(this);
		
		
		
		if ("1".equals(SharePreferUtil.getString("zhuangtai", ""))) {
			
			
			Intent inlogin = new Intent();
			inlogin.setClass(LoginPageAcitivity.this,
				MainActivity.class);
			LoginPageAcitivity.this.startActivity(inlogin);;
			LoginPageAcitivity.this.finish();
		}else {
			denglu.setOnClickListener(this);
		}

	}

	@Override
	public void onClick(View arg0) {
		Intent in = new Intent();
		switch (arg0.getId()) {
		case R.id.dengluanniu:
			input = password.getText().toString().trim();
			pnum = phonenum.getText().toString().trim();

			if (!"".equals(pnum)) {

				if (!"".equals(input)) {

					login();

					logintishi = new Handler() {

						public void handleMessage(android.os.Message msg) {

							if (msg.obj != null) {

								Toast.makeText(LoginPageAcitivity.this,
										msg.obj.toString(), Toast.LENGTH_SHORT)
										.show();

								if ("登陆成功".equals(msg.obj)) {

//									String logint = gaintime();
//
//
//
//									Editor editor = sp.edit();
//									editor.putString(arg0, arg1)
//									editor.putString("zhuangtai", "1");
//									editor.commit();

									Intent inlogin = new Intent();
									inlogin.setClass(LoginPageAcitivity.this,
											MainActivity.class);
									LoginPageAcitivity.this.startActivity(inlogin);;
									LoginPageAcitivity.this.finish();
//									Log.e("tag", sp.getString("upkey", "")
//											+ ":editor1");
//									Log.e("tag", sp.getString("zhuangtai", "")
//											+ ":editor1");

								}
							}
						}
					};
				} else {
					Toast.makeText(LoginPageAcitivity.this, "请输入密码",
							Toast.LENGTH_SHORT).show();
				}

			} else {
				Toast.makeText(LoginPageAcitivity.this, "请输入用户手机",
						Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.zhucebutton:
			in.setClass(LoginPageAcitivity.this, RegisterPageActivity.class);
			LoginPageAcitivity.this.startActivity(in);
			break;
		case R.id.forgetmima:
			in.setClass(LoginPageAcitivity.this, ForgetPassword.class);
			LoginPageAcitivity.this.startActivity(in);
			break;
		case R.id.phonenumber:

			break;
		case R.id.passw:

			break;

		}

	}

	private String gaintime() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		String logintime = year + "-" + month + "-" + day + "-" + hour + "-"
				+ minute;
		return logintime;
	}

	private void login() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				String time = gaintime();
				String Url = "http://123.57.209.98/qkhl_api/index.php/UserServer/sign_in";

				// http://123.57.209.98/hlwh_android/login.php?format=xml

				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(Url);

				// client.getParams().setContentCharset("GBK");
				client.getParams().setContentCharset("UTF-8");
				method.setRequestHeader("ContentType",
						"application/x-www-form-urlencoded;charset=UTF-8");

				NameValuePair[] data = { new NameValuePair("post_code", Constant.MIYAO),
						new NameValuePair("phone_num", pnum),
						new NameValuePair("password", input),
						new NameValuePair("login_time", time),
						new NameValuePair("client_type", Constant.USER_TYPE), 
						new NameValuePair("status", "1"),};
				Log.e("tag", "手机号：" + pnum + "密码：" + input + "创建时间:" + time
						+ "user_type:" + Constant.USER_TYPE);
				method.setRequestBody(data);

				try {
					client.executeMethod(method);
					String SubmitResult = method.getResponseBodyAsString();
					JSONObject jObject = new JSONObject(SubmitResult);
					String code = jObject.getString("code");
					String mess = jObject.getString("message");
					String upk = jObject.getString("data");
					// SubmitResult=jObject.getString("message");
					// SubmitResult=jObject.getString("data");
					Log.e("tag", code + "code");
					Log.e("tag", SubmitResult + "code");
					Log.e("tag", upk + "data");
					if ("101".equals(code)) {
						SharePreferUtil.putString("upkey", upk);
						SharePreferUtil.putString("zhuangtai", "1");
					}
					//101成功，102登录失败
					
					
				
					Message msg =  Message.obtain();
					msg.obj = mess;
					logintishi.sendMessage(msg);

				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
//					Intent in=new Intent(LoginPageAcitivity.this,MainActivity.class);
//					startActivity(in);
//					finish();
					e.printStackTrace();
				}

			}
		}).start();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e("tag", "LoginPageAcitivityonDestroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e("tag", "LoginPageAcitivityonPause");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("tag", "LoginPageAcitivityonResume");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.e("tag", "LoginPageAcitivityonRestart");
	}

}
