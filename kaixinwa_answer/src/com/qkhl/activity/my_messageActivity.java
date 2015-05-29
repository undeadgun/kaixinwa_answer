package com.qkhl.activity;

import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class my_messageActivity extends Activity implements OnClickListener{
	private ImageButton return_imgbut;
	private TextView my_messagetextview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_messgae);
		initViews();
	}
	private void initViews(){
		return_imgbut=(ImageButton) findViewById(R.id.message_return_imgbutton);
		my_messagetextview=(TextView) findViewById(R.id.my_message_textview);
		return_imgbut.setOnClickListener(this);
		my_messagetextview.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.message_return_imgbutton:
			setTabSelection(0);
			break;
		case R.id.my_message_textview:
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
