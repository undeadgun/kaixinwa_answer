package com.qkhl.activity;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import com.qkhl.common.Constant;
import com.qkhl.common.ForgetSendSMS;
import com.qkhl.kaixinwa_android.R;
import com.qkhl.util.CreateInfo;
import com.qkhl.view.ForgetTimeButton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class ForgetPassword extends Activity implements OnClickListener {

	private TextView forgetregist,wangjimiam;
	private Button forgetnext;
	private ForgetTimeButton forgethuoquyanzheng;
	private ImageButton forgetbacke;
	public static EditText forgetphone;
	private EditText forgetverification;
	public static Handler forgetphonenumerrhandle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.forgetpassword);
		initialization();
		forgethuoquyanzheng = (ForgetTimeButton) findViewById(R.id.huoquyanzhengmaanniu);
		forgethuoquyanzheng.onCreate(savedInstanceState);
		forgethuoquyanzheng.setTextAfter("秒后重新发送").setTextBefore("获取验证码")
				.setLenght(60 * 1000);

	}

	private void initialization() {
		forgetregist = (TextView) findViewById(R.id.forgetzhucebutton);
		forgetnext = (Button) findViewById(R.id.forgetnext);
		wangjimiam=(TextView) findViewById(R.id.forgetresgister);
		forgetphone = (EditText) findViewById(R.id.forgetphonenum);
		forgetbacke = (ImageButton) findViewById(R.id.forgetback);
		forgetverification = (EditText) findViewById(R.id.forgetverificationcode);
		forgetchuli();
		forgetregist.setOnClickListener(this);
		forgetnext.setOnClickListener(this);
		forgetbacke.setOnClickListener(this);
		wangjimiam.setOnClickListener(this);
	}

	public void forgetchuli() {
		// 手机号输入错误和发送成功提示
		if (forgetphonenumerrhandle == null) {

			forgetphonenumerrhandle = new Handler() {
				public void handleMessage(android.os.Message msg) {

					if (msg.obj != null) {
						Toast.makeText(ForgetPassword.this, msg.obj.toString(),
								Toast.LENGTH_SHORT).show();

						if ("短信发送成功".equals(msg.obj.toString())) {
							forgethuoquyanzheng.zhuag();
						}

					}
				}
			};

		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	

	public String  get_phonenum(){
		String pnum=forgetphone.getText().toString().trim();
		return pnum;
		
	}
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.forgetzhucebutton:
			Intent forgetin = new Intent(ForgetPassword.this,
					RegisterPageActivity.class);
			ForgetPassword.this.startActivity(forgetin);
			ForgetPassword.this.finish();
			break;
		case R.id.forgetnext:
			String verc = forgetverification.getText().toString().trim();
			int len = forgetverification.getText().length();
			if (!"".equals(verc) && len == 6) {
				if (ForgetSendSMS.get_mobile_code() == Integer.parseInt(verc)) {

					Intent fornext = new Intent(ForgetPassword.this,
							ResetPassword.class);
					ForgetPassword.this.startActivity(fornext);
					ForgetPassword.this.finish();
				} else {

					Toast.makeText(ForgetPassword.this, "验证码错误",
							Toast.LENGTH_SHORT).show();
				}

			} else {

				Toast.makeText(ForgetPassword.this, "请输入正确验证码",
						Toast.LENGTH_SHORT).show();

			}
			break;

		case R.id.forgetback:

			
			ForgetPassword.this.finish();
			break;
		case R.id.phonenumber:

			break;
		case R.id.passw:
			
			break;
		case R.id.forgetresgister:

			ForgetPassword.this.finish();
			break;

		}

	}
}
