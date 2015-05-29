package com.qkhl.activity;


import com.qkhl.fragment.AnswerFragment;
import com.qkhl.fragment.FileLocal;
import com.qkhl.fragment.FoundFragment;
import com.qkhl.fragment.myFragment;
import com.qkhl.kaixinwa_android.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	public static TextView tv;
	public static FoundFragment foundFragment;
	public static AnswerFragment ansFragment;
	public static  FileLocal filel;
	private static FragmentManager fragmentManager;
	private static AnswerPage answer;
	public static myFragment my_Fragment;

	private ImageButton myset;
	
	private int w=0;
	private View AnswerView;
	private View FoundView;
	private View MyView;
	private TextView xiangdao,saobao;
	private boolean CamerisClosed=false;
	
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Initialization();
		fragmentManager = getSupportFragmentManager();
		
		setTabSelection(1);

	}

	// 初始化控件
	public void Initialization() {
		foundFragment = null;
		AnswerView = findViewById(R.id.answerlayout);
		FoundView = findViewById(R.id.foundlayout);
		MyView = findViewById(R.id.mylayout);
		tv = (TextView) findViewById(R.id.daohang);
//		xiangdao=(TextView) findViewById(R.id.daohang);
		saobao=(TextView) findViewById(R.id.maoyisao);
		myset=(ImageButton) findViewById(R.id.set_button);
		
		myset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,system_setupActivity.class);
				startActivity(intent);
				
				
			}
		});

		AnswerView.setOnClickListener(new ViewClickListener());
		FoundView.setOnClickListener(new ViewClickListener());
		MyView.setOnClickListener(new ViewClickListener());

		saobao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "不会了！回不去", Toast.LENGTH_LONG).show();
				
				w=1;
				setTabSelection(1);
			}
		});
	}
    
	// 更换的页面，Fragment的切换
	public void setTabSelection(int index) {
		 
		transaction = fragmentManager.beginTransaction();
		// 隐藏Fragment
		hideFragments(transaction);
		switch (index) {
		case 1:

			if (w==0) {
				
				if(CamerisClosed){
					
					filel.onStart();
					CamerisClosed=false;
				}

				if (filel == null) {
					// 若不存在则新建一个
					
					filel = new FileLocal();
					transaction.add(R.id.content, filel);
				} else {
					// 若存在则直接显示
					transaction.show(filel);
				}
				
				
			}
			if (w==1) {
				
			
			if(CamerisClosed){
				
				ansFragment.onStart();
				CamerisClosed=false;
			}

			if (ansFragment == null) {
				// 若不存在则新建一个
				
				ansFragment = new AnswerFragment();
				transaction.add(R.id.content, ansFragment);
			} else {
				// 若存在则直接显示
				transaction.show(ansFragment);
			}

			}
			break;

		case 2:
			filel.onPause();
//			ansFragment.onPause();
			CamerisClosed=true;
			//ansFragment.onDestroy();
			if (foundFragment == null) {
				foundFragment = new FoundFragment();
				transaction.add(R.id.content, foundFragment);
			} else {
				transaction.show(foundFragment);
			}

			break;
		case 3:
//			ansFragment.onPause();
			CamerisClosed=true;
			if(my_Fragment == null) {
				my_Fragment=new myFragment();
				transaction.add(R.id.content, my_Fragment);
			}else{
				transaction.show(my_Fragment);
			}
			break;

		}
		transaction.commit();
	}

	// 隐藏所有的Fragment
	public static void hideFragments(FragmentTransaction transaction) {
		if (foundFragment != null) {
			transaction.hide(foundFragment);
		}

		if (ansFragment != null) {
			transaction.hide(ansFragment);
		}
		if (my_Fragment!= null) {
			transaction.hide(my_Fragment);
		}
		if(filel != null){
			transaction.hide(filel);
		}
		

	}

	// 监听事件
	public class ViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.answerlayout:
				setTabSelection(1);
				tv.setText("答案");
				myset.setVisibility(View.GONE);
				saobao.setVisibility(View.VISIBLE);
				break;

			case R.id.foundlayout:
				setTabSelection(2);
				tv.setText("发现");
				saobao.setVisibility(View.GONE);
				myset.setVisibility(View.GONE);
				
				break;
			case R.id.mylayout:
				setTabSelection(3);
				tv.setText("我的");
				saobao.setVisibility(View.GONE);
				myset.setVisibility(View.VISIBLE);
			default:
				break;
			}

		}

	}
}
