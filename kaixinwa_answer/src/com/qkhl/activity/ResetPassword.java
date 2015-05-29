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

import android.app.Activity;
import android.content.Intent;
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

public class ResetPassword extends Activity implements OnClickListener {

	private TextView resetregist;
	private Button resetc;
	private EditText newpassword, againnewpassword;
	private Handler resetmima;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chongzhimima);
		initialization();

	}

	private void initialization() {
		resetregist = (TextView) findViewById(R.id.chongzhimimazhucebutton);
		resetc = (Button) findViewById(R.id.chongzhicc);
		newpassword = (EditText) findViewById(R.id.newpassw);
		againnewpassword = (EditText) findViewById(R.id.againpassw);

		newpassword.setTypeface(Typeface.SANS_SERIF);
		againnewpassword.setTypeface(Typeface.SANS_SERIF);

		resetregist.setOnClickListener(this);
		resetc.setOnClickListener(this);
	}

	// 获取时间
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

	private void forgetmima() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				String Url = "http://123.57.209.98/hlwh_android/get_password.php";

				String logint = gaintime();
				CreateInfo cinfo = new CreateInfo();

				String input = newpassword.getText().toString().trim();
//				ForgetPassword fp = new ForgetPassword();
//				String pnum = fp.get_phonenum();
				String pnum =ForgetPassword.forgetphone.getText().toString().trim();
				String s = cinfo.upkey(input, pnum, logint);

				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(Url);

				// client.getParams().setContentCharset("GBK");
				client.getParams().setContentCharset("UTF-8");
				method.setRequestHeader("ContentType",
						"application/x-www-form-urlencoded;charset=UTF-8");

				NameValuePair[] data = { new NameValuePair("upkey", s),
						new NameValuePair("value", input), };
				Log.e("tag", "upkey：" + s + "密码：" + input);
				method.setRequestBody(data);

				try {
					client.executeMethod(method);
					String SubmitResult = method.getResponseBodyAsString();
					JSONObject jObject = new JSONObject(SubmitResult);
					String code = jObject.getString("code");
					// SubmitResult=jObject.getString("message");
					// SubmitResult=jObject.getString("data");
					Log.e("tag", code + "code");
					Log.e("tag", SubmitResult + "code");
					Message msg = new Message();
					msg.obj = code;
					resetmima.sendMessage(msg);

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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.chongzhimimazhucebutton:
			Intent resetr = new Intent(ResetPassword.this,
					RegisterPageActivity.class);
			startActivity(resetr);
			ResetPassword.this.finish();
			break;

		case R.id.chongzhicc:
			String newmima = newpassword.getText().toString().trim();
			String querenmima = againnewpassword.getText().toString().trim();
			if (!"".equals(newmima) && newpassword.getText().length() >= 6) {

				if (!"".equals(querenmima)) {

					if (querenmima.equals(newmima)) {
						forgetmima();
						resetmima = new Handler() {

							public void handleMessage(android.os.Message msg) {

								if ("102".equals(msg.obj)) {
									Toast.makeText(ResetPassword.this, "修改失败",
											Toast.LENGTH_SHORT).show();
									if ("101".equals(msg.obj)) {
										
										Toast.makeText(ResetPassword.this, "修改成功",
												Toast.LENGTH_SHORT).show();
										Intent resetco = new Intent(ResetPassword.this,
												LoginPageAcitivity.class);
										startActivity(resetco);
									}
								}
							}

						};

						
					} else {
						Toast.makeText(ResetPassword.this, "两次输入不一致",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(ResetPassword.this, "请重新输入",
							Toast.LENGTH_SHORT).show();
				}
			} else {

				Toast.makeText(ResetPassword.this, "密码不能为空", Toast.LENGTH_SHORT)
						.show();
			}

		default:
			break;
		}
	}
}
