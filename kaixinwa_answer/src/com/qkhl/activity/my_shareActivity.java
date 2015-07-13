package com.qkhl.activity;

import com.qkhl.common.MyApplication;
import com.qkhl.kaixinwa_android.R;
import com.qkhl.kaixinwa_android.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class my_shareActivity extends Activity implements OnClickListener{
	private ImageButton return_imgbut;
	private TextView my_sharetextview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_share);
		initViews();
	}
	
	private void initViews(){
		return_imgbut=(ImageButton) findViewById(R.id.share_return_imgbutton);
		my_sharetextview=(TextView) findViewById(R.id.my_share_textview);
		return_imgbut.setOnClickListener(this);
		my_sharetextview.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.share_return_imgbutton:
			setTabSelection(0);
			break;
		case R.id.my_share_textview:
				setTabSelection(1);
				break;

		default:
			break;
		}
		
	}
	private void setTabSelection(int index){
		switch (index) {
		case 0:
			finish();
			break;
		case 1:
			finish();
			break;

		default:
			break;
		}
	}
	

}
