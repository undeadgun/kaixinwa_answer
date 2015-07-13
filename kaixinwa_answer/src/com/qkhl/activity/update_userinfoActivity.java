package com.qkhl.activity;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import com.qkhl.kaixinwa_android.R;
import com.qkhl.util.ConnectionHttpPost;
import com.qkhl.util.SharePreferUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class update_userinfoActivity extends Activity {
	private EditText name_update;
	private View name_sure;
	private String new_name;
	public static Handler rename;
	public static Handler renametext;
	private String str, url, tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 无title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.update_userinfo);
		str = getIntent().getExtras().getString("name_textview");
		tag = getIntent().getExtras().getString("tag");

		url = getIntent().getExtras().getString("url");
		Log.e("TAG", url);
		name_update = (EditText) findViewById(R.id.name_update_edittext);
		name_update.setHint("设置" + str);
		name_sure = findViewById(R.id.name_sure);

		name_sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				ConnectionHttpPost chp = new ConnectionHttpPost();
				new_name = name_update.getText().toString().trim();
				if ("".equals(new_name)) {
					Toast.makeText(update_userinfoActivity.this, "请设置内容，不能为空！",
							Toast.LENGTH_SHORT).show();
					Log.e("TAG", new_name + "new_name");

				} else {

					chp.login(url, "123455", new_name);

					rename = new Handler() {
						public void handleMessage(android.os.Message msg) {
							if (msg.obj != null) {
								Toast.makeText(update_userinfoActivity.this,
										msg.obj.toString(), Toast.LENGTH_SHORT)
										.show();
								if ("更新成功".equals(msg.obj)) {
									if (tag.equals("1")) {

										SharePreferUtil.putString("setname",
												new_name);
									}
									if (tag.equals("2")) {

										SharePreferUtil.putString("setmood",
												new_name);
									}
									if (tag.equals("3")) {

										SharePreferUtil.putString("setweixin",
												new_name);
									}
									if (tag.equals("4")) {

										SharePreferUtil.putString("setqq",
												new_name);
									}
									if (tag.equals("5")) {

										SharePreferUtil.putString("setaddress",
												new_name);
									}
									if (tag.equals("6")) {

										SharePreferUtil.putString("setschool",
												new_name);
									}

									// new Thread(new Runnable() {
									//
									// @Override
									// public void run() {
									//
									// // TODO Auto-generated method stub
									// Message message = new Message();
									// message.obj = new_name;
									//
									// renametext.sendMessage(message);
									//
									// }
									// }).start();

								}
							}
							finish();
						}

					};
				}
			}
		});

	}

}
