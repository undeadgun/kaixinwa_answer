package com.qkhl.activity;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import com.qkhl.common.Constant;
import com.qkhl.common.ForgetSendSMS;
import com.qkhl.common.SendSMS;
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
	private Button forgethuoquyanzheng;
	private ImageButton forgetbacke;
	public  static EditText forgetphone;
	private EditText forgetverification;
	public static Handler forgetphonenumerrhandle;
	public  Handler duanxinxiaoxi;

	
	private Timer timer;// 计时器
	int jishi = 60;// 计时

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				forgethuoquyanzheng.setEnabled(true);
				forgethuoquyanzheng.setText("重新发送验证码");
				timer.cancel();
			} else {
				forgethuoquyanzheng.setText(msg.what + "秒");
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.forgetpassword);
		initialization();

		forgethuoquyanzheng.setText("发送验证码");
		forgethuoquyanzheng.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String phonenum = forgetphone.getText().toString().trim();
				if (isMobileNO(phonenum)==true) {
					jishi = 60;
					forgethuoquyanzheng.setEnabled(false);
					timer = new Timer();
					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							handler.sendEmptyMessage(jishi--);
						}
					}, 0, 1000);
					
				}
				ForgetSendSMS sms = new ForgetSendSMS(phonenum);
				sms.send();
				forgetphonenumerrhandle = new Handler() {
					public void handleMessage(android.os.Message msg) {
						if (msg.obj != null) {
							if ("发送成功".equals(msg.obj)) {
								Toast.makeText(ForgetPassword.this,"短信发送成功", Toast.LENGTH_LONG).show();
	
							} else {
								
								Toast.makeText(ForgetPassword.this,msg.obj+"", Toast.LENGTH_LONG).show();
							}
						}
					};
				};
				
				
			}
		});
		
	}
	public  boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])\\d{8}$|(15[^4,\\D])\\d{8}$|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	private void initialization() {
//		forgetregist = (TextView) findViewById(R.id.forgetzhucebutton);
		forgetnext = (Button) findViewById(R.id.forgetnext);
		forgethuoquyanzheng=(Button) findViewById(R.id.huoquyanzhengmaanniu);
		wangjimiam=(TextView) findViewById(R.id.forgetresgister);
		forgetphone = (EditText) findViewById(R.id.forgetphonenum);
		forgetbacke = (ImageButton) findViewById(R.id.forgetback);
		forgetverification = (EditText) findViewById(R.id.forgetverificationcode);
//		forgetchuli();
//		forgetregist.setOnClickListener(this);
		forgetnext.setOnClickListener(this);
		forgetbacke.setOnClickListener(this);
		wangjimiam.setOnClickListener(this);
	}

//	public void forgetchuli() {
//		// 手机号输入错误和发送成功提示
//		if (forgetphonenumerrhandle == null) {
//
//			forgetphonenumerrhandle = new Handler() {
//				public void handleMessage(android.os.Message msg) {
//
//					if (msg.obj != null) {
//						Toast.makeText(ForgetPassword.this, msg.obj.toString(),
//								Toast.LENGTH_SHORT).show();
//
//						if ("短信发送成功".equals(msg.obj.toString())) {
//							forgethuoquyanzheng.zhuag();
//						}
//
//					}
//				}
//			};
//
//		}
//	}

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
//		case R.id.forgetzhucebutton:
//			Intent forgetin = new Intent(ForgetPassword.this,
//					RegisterPageActivity.class);
//			ForgetPassword.this.startActivity(forgetin);
//			ForgetPassword.this.finish();
//			break;
		case R.id.forgetnext:
			
			String verc = forgetverification.getText().toString().trim();
			int len = forgetverification.getText().length();
			if (!"".equals(verc) && len == 6) {
				vcode();
				duanxinxiaoxi=new Handler(){
					public void handleMessage(android.os.Message msg) {
						if (msg.obj != null) {
							Toast.makeText(ForgetPassword.this, msg.obj.toString(),
									Toast.LENGTH_SHORT).show();
							if ("验证成功".equals(msg.obj)) {
								Intent fornext = new Intent(ForgetPassword.this,
										ResetPassword.class);
								ForgetPassword.this.startActivity(fornext);
								ForgetPassword.this.finish();
								
							}
						}
					}
				};

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
	
	// 短信验证
	private void vcode() {
		new Thread(new Runnable() {
			@Override
			public void run() {

			
				String vecod = forgetverification.getText().toString().trim();

				String phonenum=forgetphone.getText().toString().trim();
			
				// 短信接口
				String Url = "http://123.57.209.98/qkhl_api/index.php/UserServer/update_password_set_A";

				
				// http://123.57.209.98/hlwh_android/login.php?format=xml

				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(Url);

				// client.getParams().setContentCharset("GBK");
				client.getParams().setContentCharset("UTF-8");
				method.setRequestHeader("ContentType",
						"application/x-www-form-urlencoded;charset=UTF-8");

				NameValuePair[] data = {new NameValuePair("post_code", Constant.MIYAO), 
						new NameValuePair("phone_num", phonenum),
						new NameValuePair("mobile_code", vecod),
					 };
		
				method.setRequestBody(data);

				
				try {
					client.executeMethod(method);
					String SubmitResult = method.getResponseBodyAsString();

					JSONObject jObject = new JSONObject(SubmitResult);
					String code = jObject.getString("code");
					String message = jObject.getString("message");

					// Document doc = DocumentHelper.parseText(SubmitResult);
					// Element root = doc.getRootElement();
					// String code = root.elementText("code");
					Log.e("tag", code + "：code值" + message);
					Message msg = Message.obtain();
//					if ("301".equals(code)) {
//						msg.obj = "注册成功";
//						// 注册成功
//					}
//					if ("202".equals(code)) {
//						// 手机号已注册
//						msg.obj = "手机号已注册";
//					}
//					if ("302".equals(code)) {
//						msg.obj = "注册失败";
//						// 注册失败
//					}
//					if ("101".equals(code)) {
//						msg.obj = "手机号不正确";
//					}
//					if ("102".equals(code)) {
//						msg.obj = "密码格式不正确";
//					}
//					if ("402".equals(code)) {
//						msg.obj = "验证码不正确";
//					}

					msg.obj = message;
					duanxinxiaoxi.sendMessage(msg);
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
