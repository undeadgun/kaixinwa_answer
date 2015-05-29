package com.qkhl.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.qkhl.activity.ForgetPassword;
import com.qkhl.common.ForgetSendSMS;
import com.qkhl.common.MyApplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ForgetTimeButton extends Button implements OnClickListener {
	private long lenght = 60 * 1000;// 倒计时长度,这里给了默认60秒
	private String textafter = "秒后重新获取~";
	private String textbefore = "点击获取验证码~";
	private final String TIME = "time";
	private final String CTIME = "ctime";
	private OnClickListener mOnclickListener;
	private Timer t;
	private TimerTask tt;
	private long time;
	Map<String, Long> map = new HashMap<String, Long>();
	private View v;

	public ForgetTimeButton(Context context) {
		super(context);
		setOnClickListener(this);

	}

	public ForgetTimeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(this);
	}

	@SuppressLint("HandlerLeak")
	Handler han = new Handler() {
		public void handleMessage(android.os.Message msg) {

			ForgetTimeButton.this.setText(time / 1000 + textafter);
			time -= 1000;
			if (time < 0) {
				ForgetTimeButton.this.setEnabled(true);
				ForgetTimeButton.this.setText(textbefore);
				clearTimer();
			}
		};
	};

	public void zhuag() {
		if (mOnclickListener != null)
			mOnclickListener.onClick(v);
		initTimer();
		this.setText(time / 1000 + textafter);
		this.setEnabled(false);
		t.schedule(tt, 0, 1000);
		// t.scheduleAtFixedRate(task, delay, period);
	}

	private void initTimer() {
		time = lenght;
		t = new Timer();
		tt = new TimerTask() {

			@Override
			public void run() {
				Log.e("yung", time / 1000 + "");
				han.sendEmptyMessage(0x01);

			}
		};
	}

	private void clearTimer() {
		if (tt != null) {
			tt.cancel();
			tt = null;
		}
		if (t != null)
			t.cancel();
		t = null;
	}

	@Override
	public void setOnClickListener(OnClickListener l) {

		if (l instanceof ForgetTimeButton) {
			super.setOnClickListener(l);
		} else
			this.mOnclickListener = l;
	}

	@Override
	public void onClick(View v) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ForgetSendSMS sendsms = new ForgetSendSMS(
						ForgetPassword.forgetphone.getText().toString());
				// 发送短信
				sendsms.send();
			}
		}).start();

		this.v = v;

	}

	/**
	 * 和activity的onDestroy()方法同步
	 */
	public void onDestroy() {
		if (MyApplication.map == null)
			MyApplication.map = new HashMap<String, Long>();
		MyApplication.map.put(TIME, time);
		MyApplication.map.put(CTIME, System.currentTimeMillis());
		clearTimer();
		Log.e("yung", "onDestroy");
	}

	/**
	 * 和activity的onCreate()方法同步
	 */
	public void onCreate(Bundle bundle) {

		Log.e("yung", MyApplication.map + "");
		if (MyApplication.map == null)
			return;
		if (MyApplication.map.size() <= 0)// 这里表示没有上次未完成的计时
			return;
		long time = System.currentTimeMillis() - MyApplication.map.get(CTIME)
				- MyApplication.map.get(TIME);
		MyApplication.map.clear();
		if (time > 0)
			return;
		else {
			initTimer();
			this.time = Math.abs(time);
			t.schedule(tt, 0, 1000);
			this.setText(time + textafter);
			this.setEnabled(false);
		}
	}

	/** * 设置计时时候显示的文本 */
	public ForgetTimeButton setTextAfter(String text1) {
		this.textafter = text1;
		return this;
	}

	/** * 设置点击之前的文本 */
	public ForgetTimeButton setTextBefore(String text0) {
		this.textbefore = text0;
		this.setText(textbefore);
		return this;
	}

	/**
	 * 设置到计时长度
	 * 
	 * @param lenght
	 *            时间 默认毫秒
	 * @return
	 */
	public ForgetTimeButton setLenght(long lenght) {
		this.lenght = lenght;
		return this;
	}
	/*

*
*/
}