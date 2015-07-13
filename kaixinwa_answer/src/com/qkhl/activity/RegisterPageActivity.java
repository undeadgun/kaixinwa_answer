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
import com.qkhl.common.SendSMS;
import com.qkhl.kaixinwa_android.R;
import com.qkhl.util.ConnectionChangeReceiver;
import com.qkhl.util.CreateInfo;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class RegisterPageActivity extends Activity implements OnClickListener {

	private RelativeLayout registback;// 返回按钮
	private Button smsbutton;// 发送短信按钮
	private Button complete;// 完成按钮
	public EditText phoneNumber;// 手机号输入框
	public EditText inputpass;// 密码输入按钮
	public EditText verification;// 验证码输入框
	private ConnectionChangeReceiver netReceiver;

	private Handler faxiaoxi;
	private String input;// 输入的密码
//	private TextView zhuxiao;
	private TelephonyManager telephonyManager;
	private String phnum;

	private String verificcode;// 输入的验证码
	private Timer timer;// 计时器
	private int jishi = 60;// 计时
	public static Handler smshandler;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				smsbutton.setEnabled(true);
				smsbutton.setText("重新发送验证码");
				timer.cancel();
			} else {
				smsbutton.setText(msg.what + "秒");
			}
		};
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.registerpag);
		Initialization();
		smsbutton.setText("发送验证码");
		smsbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				
				String phonenum = phoneNumber.getText().toString().trim();
				if (isMobileNO(phonenum) == true) {

					jishi = 60;
					smsbutton.setEnabled(false);
					timer = new Timer();
					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							handler.sendEmptyMessage(jishi--);
						}
					}, 0, 1000);
					
				}
				SendSMS sms = new SendSMS(phonenum);
				sms.send();
			
				smshandler = new Handler() {
					public void handleMessage(android.os.Message msg) {
						if (msg.obj != null) {
							if ("发送成功".equals(msg.obj)) {
								Toast.makeText(RegisterPageActivity.this,"短信发送成功", Toast.LENGTH_LONG).show();
							
							} else {
								
								Toast.makeText(RegisterPageActivity.this,msg.obj+"", Toast.LENGTH_LONG).show();
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

	// 监听器事件
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.registback:
			RegisterPageActivity.this.finish();
			break;
		case R.id.rwancheng:

			verificcode = verification.getText().toString().trim();
			input = inputpass.getText().toString().trim();
			//
			//
			// 判定验证码输入框为空时提示请输入正确的验证码，验证码长度=6
			if (!"".equals(verificcode) && verificcode.length() == 6) {

				// 判定密码框为空时提示输入密码长度大于等于6
				if (!"".equals(input) && input.length() >= 6) {

					regist();
					faxiaoxi = new Handler() {
						public void handleMessage(android.os.Message msg) {

							if (msg.obj != null) {
								Toast.makeText(RegisterPageActivity.this,
										msg.obj.toString(), Toast.LENGTH_LONG)
										.show();
								if ("注册成功".equals(msg.obj)) {

									// Intent inrgec = new Intent(
									// RegisterPageActivity.this,
									// LoginPageAcitivity.class);
									// RegisterPageActivity.this
									// .startActivity(inrgec);
									RegisterPageActivity.this.finish();
								}
							}
						}
					};

				} else {

					Toast.makeText(RegisterPageActivity.this, "请输入密码",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(RegisterPageActivity.this, "请输入正确的验证码",
						Toast.LENGTH_SHORT).show();
			}

			break;
//		case R.id.resgister:
//			RegisterPageActivity.this.finish();
//			break;

		}
	}

	
	private void Initialization() {

		registback = (RelativeLayout) findViewById(R.id.registback);
		smsbutton = (Button) findViewById(R.id.smsbutton);
		complete = (Button) findViewById(R.id.rwancheng);
		verification = (EditText) findViewById(R.id.verificationcode);
//		zhuxiao = (TextView) findViewById(R.id.resgister);
		phoneNumber = (EditText) findViewById(R.id.telphoneNumber);
		inputpass = (EditText) findViewById(R.id.inputpassw);

		// 强制设置字体
		phoneNumber.setTypeface(Typeface.SANS_SERIF);
		inputpass.setTypeface(Typeface.SANS_SERIF);
		verification.setTypeface(Typeface.SANS_SERIF);

		registerReceiver();

		registback.setOnClickListener(this);
		complete.setOnClickListener(this);
//		zhuxiao.setOnClickListener(this);

		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

	}

	// 注册服务
	private void registerReceiver() {
		IntentFilter filter = new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(netReceiver, filter);
	}

	// 注册接口解析线程
	private void regist() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				// 获取系统时间
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH) + 1;
				int day = c.get(Calendar.DAY_OF_MONTH);
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				String createtime = year + "-" + month + "-" + day + "-" + hour
						+ "-" + minute;

				// 获取uuid
				UUID uuid = UUID.randomUUID();
				String uu = uuid.toString();
				phnum = phoneNumber.getText().toString().trim();

				// 获取设备id
				String shebeiid = telephonyManager.getDeviceId();
				// 获取upkey
				CreateInfo ci = new CreateInfo();
				String upkeymd5 = ci.upkey(phnum, input);



				// 注册接口
				String Url = "http://123.57.209.98/qkhl_api/index.php/UserServer/sign_up";

				
				// http://123.57.209.98/hlwh_android/login.php?format=xml

				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(Url);

				// client.getParams().setContentCharset("GBK");
				client.getParams().setContentCharset("UTF-8");
				method.setRequestHeader("ContentType",
						"application/x-www-form-urlencoded;charset=UTF-8");

				NameValuePair[] data = {new NameValuePair("post_code", Constant.MIYAO), 
						new NameValuePair("phone_num", phnum),
						new NameValuePair("mobile_code", verificcode),
						new NameValuePair("password", input),
						new NameValuePair("uuid", uu),
						new NameValuePair("equipment_id", shebeiid),
						new NameValuePair("create_time", createtime),
						new NameValuePair("upkey", upkeymd5),
						new NameValuePair("user_type:", Constant.USER_TYPE), };
				Log.e("tag", "手机号：" + phnum + "密码：" + input + "uuid：" + uu
						+ "设备id：" + shebeiid + "创建时间:" + createtime + "upkey:"
						+ upkeymd5 + "user_type:" + Constant.USER_TYPE
						+ "mobile_code:" + verificcode);
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
					if ("301".equals(code)) {
						msg.obj = "注册成功";
						// 注册成功
					}
					if ("202".equals(code)) {
						// 手机号已注册
						msg.obj = "手机号已注册";
					}
					if ("302".equals(code)) {
						msg.obj = "注册失败";
						// 注册失败
					}
					if ("101".equals(code)) {
						msg.obj = "手机号不正确";
					}
					if ("102".equals(code)) {
						msg.obj = "密码格式不正确";
					}
					if ("402".equals(code)) {
						msg.obj = "验证码不正确";
					}

					faxiaoxi.sendMessage(msg);
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
