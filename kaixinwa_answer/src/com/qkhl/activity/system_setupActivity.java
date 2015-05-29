package com.qkhl.activity;

import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class system_setupActivity extends Activity implements OnClickListener {
	private ImageButton return_button;
	private TextView return_textview;
	private ToggleButton message_togglebutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.system_setup);
		initViews();
	}

	private void initViews() {
		return_button = (ImageButton) findViewById(R.id.systemsetup_return_imgbutton);
		return_textview = (TextView) findViewById(R.id.systemsetup_textview);
		message_togglebutton = (ToggleButton) findViewById(R.id.messagepush_toggleButton);
		

		return_button.setOnClickListener(this);
		return_textview.setOnClickListener(this);
		message_togglebutton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				 if(arg1){  
					 arg1=true;
			        }else{  
			        	arg1=false;
			        	
			        }  
			}
		});

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.systemsetup_return_imgbutton:
			setTabSelection(0);
			break;
		case R.id.systemsetup_textview:
			setTabSelection(1);
			break;
		default:
			break;
		}

	}

	private void setTabSelection(int index) {
		
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
