package com.qkhl.activity;

import java.io.File;

import com.qkhl.fragment.AnswerFragment;
//import com.qkhl.fragment.FileLocal;
import com.qkhl.fragment.FoundFragment;
import com.qkhl.fragment.myFragment;
import com.qkhl.kaixinwa_android.R;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	public static TextView tv;
	public static FoundFragment foundFragment;
	public static AnswerFragment ansFragment;
//	public static FileLocal filel;
	private static FragmentManager fragmentManager;
	private static AnswerPage answer;
	public static myFragment my_Fragment;

	private ImageButton myset;
	private ImageView myanswerimager;
	private TextView myanswertext;
	private ImageView myfoundimager;
	private TextView myfoundtext;
	private ImageView myimager;
	private TextView mytext;
//	private ImageButton saobao;

//	private int w = 0;
	private View AnswerView;
	private View FoundView;
	private View MyView;
	private TextView xiangdao;

	private boolean CamerisClosed = false;
	private boolean Cameris = false;

	private FragmentTransaction transaction;

	// 定义一个变量，来标识是否退出
	private static boolean isExit = false;
	private static boolean ismyopen = false;
	private static boolean isfoundopen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Initialization();
		fragmentManager = getSupportFragmentManager();
		
		//被系统ondestory之后再启动的；
		Cameris=true;	
		ismyopen = true;
		isfoundopen=true;

//		if (initDownPath("/sdcard/answer/")==true) {
			setTabSelection(1);
//		}else {
//			setTabSelection(4);
//		}

			Log.e("tag", "onCreate:+MainActivity");
	}
	

	// 初始化控件
	public void Initialization() {
		foundFragment = null;
		AnswerView = findViewById(R.id.answerlayout);
		FoundView = findViewById(R.id.foundlayout);
		MyView = findViewById(R.id.mylayout);
		tv = (TextView) findViewById(R.id.daohang);
		// xiangdao=(TextView) findViewById(R.id.daohang);
//		saobao = (ImageButton) findViewById(R.id.maoyisao);
		myset = (ImageButton) findViewById(R.id.set_button);
		myanswerimager = (ImageView) findViewById(R.id.answerpic);
		myanswertext = (TextView) findViewById(R.id.found);
		myfoundimager = (ImageView) findViewById(R.id.foundpic);
		myfoundtext = (TextView) findViewById(R.id.myfoundte);
		myimager = (ImageView) findViewById(R.id.mypic);
		mytext = (TextView) findViewById(R.id.da);

		myset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, system_setupActivity.class);
				startActivity(intent);

			}
		});

		AnswerView.setOnClickListener(new ViewClickListener());
		FoundView.setOnClickListener(new ViewClickListener());
		MyView.setOnClickListener(new ViewClickListener());
		myanswerimager.setImageResource(R.drawable.dianxiahouanswer);
		myanswertext.setTextColor(Color.parseColor("#0093FF"));
//		saobao.setOnClickListener(new ViewClickListener());
	}
	
	@Override
	protected void onDestroy() {
		Log.e("tag", "onDestroy:+MainActivity");
		// TODO Auto-generated method stub
		super.onDestroy();
		
		
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.e("tag", "onRestart:+MainActivity");
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e("tag", "onStart:+MainActivity");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("tag", "onResume:+MainActivity");
			
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e("tag", "onPause:+MainActivity");
	}
	
	// 更换的页面，Fragment的切换
	public void setTabSelection(int index) {
			transaction = fragmentManager.beginTransaction();
			// 隐藏Fragment
			hideFragments(transaction);
		
		// fragment之间切换动画
		// transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		// transaction.setCustomAnimations(R.anim.zoom_out_enter,
		// R.anim.zoom_out_exit);
		// R.anim.fragment_slide_left_in,
		// R.anim.fragment_slide_left_out,R.anim.fragment_slide_right_in,
		// R.anim.fragment_slide_right_out

		
		switch (index) {
		case 1:
				
			if (CamerisClosed) {
				Log.e("tag", "4");
				ansFragment.onStart();
				CamerisClosed = false;
				
			}
				

				if (ansFragment == null) {
					// 若不存在则新建一个
					Log.e("tag", "1");
					Cameris=false;
					ansFragment = new AnswerFragment();
					transaction.add(R.id.content, ansFragment);
				} else {
					Log.e("tag", "2");
					if (Cameris) {
						Log.e("tag", "3");
						ansFragment = new AnswerFragment();
						transaction.add(R.id.content, ansFragment);
						Cameris=false;
					}
					
					// 若存在则直接显示
					transaction.show(ansFragment);
				}
				

			break;

		case 2:
			
			 ansFragment.onPause();
			 
			CamerisClosed = true;
			// ansFragment.onDestroy();
			if (foundFragment == null) {
				foundFragment = new FoundFragment();
				isfoundopen=false;
				transaction.add(R.id.content, foundFragment);
			} else {
				if (isfoundopen) {
					Log.e("tag", "3");
					foundFragment = new FoundFragment();
					transaction.add(R.id.content, foundFragment);
					isfoundopen=false;
				}
				transaction.show(foundFragment);
			}

			break;
		case 3:
			 ansFragment.onPause();
			 
		
			CamerisClosed = true;
			if (my_Fragment == null) {
				my_Fragment = new myFragment();
				ismyopen=false;
				transaction.add(R.id.content, my_Fragment);
			} else {
				if (ismyopen) {
					my_Fragment = new myFragment();
					transaction.add(R.id.content, my_Fragment);
					ismyopen=false;
				}
				transaction.show(my_Fragment);
			}
			break;
//		case 4:
////			ansFragment.onStart();
////			 ansFragment.onPause();
////			if (Cameris) {
////				ansFragment.onStart();
////				Cameris=false;
////			}
//				
//				CamerisClosed = false;
//			
//
//			if (filel == null) {
//				filel = new FileLocal();
//				transaction.add(R.id.content, filel);
//			} else {
//				transaction.show(filel);
//			}
//			break;

		}
		transaction.commit();
	}

	// 隐藏所有的Fragment
	public  void hideFragments(FragmentTransaction transaction) {
		if (foundFragment != null) {
			transaction.hide(foundFragment);
		}

		if (ansFragment != null) {
			transaction.hide(ansFragment);
		}
		if (my_Fragment != null) {
			transaction.hide(my_Fragment);
		}
//		if (filel != null) {
//			transaction.hide(filel);
//		}

	}

	// 监听事件
	public class ViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.answerlayout:
//				if (initDownPath("/sdcard/answer/")==true) {
					setTabSelection(1);
//				}else {
//					setTabSelection(4);
//				}
				
				// 答案点中
				myanswerimager.setImageResource(R.drawable.dianxiahouanswer);
				myanswertext.setTextColor(Color.parseColor("#0093FF"));
				// 发现默认
				myfoundimager.setImageResource(R.drawable.faxiantubiao);
				myfoundtext.setTextColor(Color.parseColor("#999999"));
				// 我的默认
				myimager.setImageResource(R.drawable.baisemybg);
				mytext.setTextColor(Color.parseColor("#999999"));
				tv.setText("答案");
				myset.setVisibility(View.GONE);
//				saobao.setVisibility(View.VISIBLE);
				break;

			case R.id.foundlayout:
				setTabSelection(2);
				// 发现点中
				myfoundimager.setImageResource(R.drawable.foundxuanzhongbg);
				myfoundtext.setTextColor(Color.parseColor("#0093FF"));

				myimager.setImageResource(R.drawable.baisemybg);
				mytext.setTextColor(Color.parseColor("#999999"));

				myanswerimager.setImageResource(R.drawable.daantubiao);
				myanswertext.setTextColor(Color.parseColor("#999999"));
				tv.setText("发现");
//				saobao.setVisibility(View.GONE);
				myset.setVisibility(View.GONE);

				break;
//			case R.id.maoyisao:
//				setTabSelection(1);
//				break;
			case R.id.mylayout:
				setTabSelection(3);
				myimager.setImageResource(R.drawable.mytubiao);
				mytext.setTextColor(Color.parseColor("#0093FF"));

				myfoundimager.setImageResource(R.drawable.faxiantubiao);
				myfoundtext.setTextColor(Color.parseColor("#999999"));

				myanswerimager.setImageResource(R.drawable.daantubiao);
				myanswertext.setTextColor(Color.parseColor("#999999"));
				tv.setText("我的");
//				saobao.setVisibility(View.GONE);
				myset.setVisibility(View.VISIBLE);
			default:
				break;
			}

		}

	}
	public  boolean initDownPath(String path){
		if(Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
		   File file = new File(path);
		   if(!file.exists()){
//		      file.mkdirs();
		      return true;
		    }
		}
		   return false;
		}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			// 利用handler延迟发送更改状态信息
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}
}
