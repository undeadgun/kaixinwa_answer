package com.qkhl.common;

import java.io.File;
import java.util.Map;





import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.TokenGenerator;
import com.alibaba.sdk.android.oss.util.OSSToolKit;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;

public class MyApplication extends Application {

	private static MyApplication instance;

	// 用于存放倒计时时间
	public static Map<String, Long> map;
	public static Map<String, Long> map2;
	
	static final String accessKey = "EHYIkXpXsuBqP2Gq";// oss accessKey
	static final String screctKey = "oDudtCM3kxDgayHSsC8WZfM1I6FZJ5";// oss
																		// screctKey
	public static OSSService ossService = OSSServiceProvider.getService();// 初始化ossserver

	public static MyApplication getInstance() {
		  if (null == instance) {
	            instance = new MyApplication();
	        }
		return instance;
	}

	private boolean isDownload;

	@Override
	public void onCreate() {
		super.onCreate();
		isDownload = false;
		instance = this;
		createFile();
		intoss();
	}

	// 初始化OSS
		@SuppressLint("NewApi")
		public void intoss() {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);

			ossService.setApplicationContext(this);
			ossService.setGlobalDefaultTokenGenerator(new TokenGenerator() {
				@Override
				public String generateToken(String httpMethod, String md5,
						String type, String date, String ossHeaders, String resource) {

					String content = httpMethod + "\n" + md5 + "\n" + type + "\n"
							+ date + "\n" + ossHeaders + resource;

					return OSSToolKit.generateToken(accessKey, screctKey, content);
				}
			});
			ossService.setGlobalDefaultHostId("oss-cn-beijing.aliyuncs.com");
			ossService
					.setCustomStandardTimeWithEpochSec(System.currentTimeMillis() / 1000);
			ossService.setGlobalDefaultACL(AccessControlList.PUBLIC_READ);

			ClientConfiguration conf = new ClientConfiguration();
			conf.setConnectTimeout(15 * 1000);
			conf.setSocketTimeout(15 * 1000);
			conf.setMaxConnections(50);
			ossService.setClientConfiguration(conf);
		}

	public void createFile(){
		File creatfile = new File(Environment.getExternalStorageDirectory()+ "/kaixinwa_answer/avatar");
		if (!creatfile.exists()) {
			creatfile.mkdirs();
			Log.e("tag", "創建" + Environment.getExternalStorageDirectory());

		}
	}

	public boolean isDownload() {
		return isDownload;
	}

	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}

}
