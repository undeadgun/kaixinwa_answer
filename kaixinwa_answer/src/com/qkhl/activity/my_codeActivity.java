package com.qkhl.activity;

import com.qkhl.common.MyApplication;
import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class my_codeActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_code);
	}

}
