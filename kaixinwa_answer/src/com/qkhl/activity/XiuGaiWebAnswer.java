package com.qkhl.activity;

import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class XiuGaiWebAnswer extends Activity {

	private ImageView back, share, save;
	private ImageView zsjiantou, xljiantou, ygjiantou;
	private TextView xiaoshu;
	private WebView xweb, zweb, yweb;
	private RelativeLayout zslayout, xllayout, yglayout;
	private Boolean iszslayout = false, isxllayout = false, isyglayout = false;
	  /** 
     *当前打开的父节点 
     */  
    private int open=-1;  
   
    int[] location =new int[2];
    /** 
     * 是否有打开的父节点 
     */  
    private boolean isExpanding=false; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xiugaiitem);
		back = (ImageView) findViewById(R.id.back);
		share = (ImageView) findViewById(R.id.Share);
		xiaoshu = (TextView) findViewById(R.id.xiaoshuname);
		save = (ImageView) findViewById(R.id.save);
		xweb = (WebView) findViewById(R.id.xllhtweb);
		zweb = (WebView) findViewById(R.id.zsylweb);
		yweb = (WebView) findViewById(R.id.yglyzweb);
		zsjiantou = (ImageView) findViewById(R.id.zhishijiantou);
		xljiantou = (ImageView) findViewById(R.id.xinlujiantou);
		ygjiantou = (ImageView) findViewById(R.id.yangguangjiantou);
		zslayout = (RelativeLayout) findViewById(R.id.zhishiyuliangquan);
		xllayout = (RelativeLayout) findViewById(R.id.xinlulanghuatan);
		yglayout = (RelativeLayout) findViewById(R.id.yangguanglvyezhou);
		
//		sc1 = (ScrollView) findViewById(R.id.sc1);
//		sc2 = (ScrollView) findViewById(R.id.sc2);
//		sc3 = (ScrollView) findViewById(R.id.sc3);

		zweb.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
//				xllayout.getLocationInWindow(location);
				zslayout.getLocationOnScreen(location) ;
	            int y = location[1];
	            Log.e("tag",y+":y");
	            
				return false;
			}
		});
		
	
		Intent intent = getIntent();
		String lianjie = intent.getStringExtra("lianjie");
		zslayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (iszslayout == false) {
					zweb.setVisibility(View.VISIBLE);
					WebSettings websett = zweb.getSettings();
					websett.setJavaScriptEnabled(true);
					websett.setCacheMode(WebSettings.LOAD_NO_CACHE);
					zweb.loadUrl("http://qkhl-api.com/123/answer.html");
					zsjiantou
							.setBackgroundResource(R.drawable.expandablelist_donw);
					
					
					iszslayout = true;
				} else {
					zsjiantou.setBackgroundResource(R.drawable.blue_jiantou_bg);
					zweb.setVisibility(View.GONE);
					iszslayout = false;
				}

			}
		});
		xllayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isxllayout == false) {
					xweb.setVisibility(View.VISIBLE);
					WebSettings webset = xweb.getSettings();
					webset.setJavaScriptEnabled(true);
					webset.setCacheMode(WebSettings.LOAD_NO_CACHE);
					xweb.loadUrl("http://qkhl-api.com/123/answer.html");
					xljiantou
							.setBackgroundResource(R.drawable.expandablelist_donw);
					isxllayout = true;
					
				} else {
					xljiantou.setBackgroundResource(R.drawable.blue_jiantou_bg);
					xweb.setVisibility(View.GONE);
					isxllayout = false;
				}
			}
		});
		yglayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isyglayout == false) {
					yweb.setVisibility(View.VISIBLE);
					WebSettings webse = yweb.getSettings();
					webse.setJavaScriptEnabled(true);
					webse.setCacheMode(WebSettings.LOAD_NO_CACHE);
					webse.setUseWideViewPort(true);
					webse.setLoadWithOverviewMode(true);
					yweb.loadUrl("http://qkhl-api.com/123/answer.html");
					ygjiantou
							.setBackgroundResource(R.drawable.expandablelist_donw);
					Log.e("tag", yglayout.getTop()+":ygjiantougetTop");
					isyglayout = true;
				} else {
					ygjiantou.setBackgroundResource(R.drawable.blue_jiantou_bg);
					yweb.setVisibility(View.GONE);
					isyglayout = false;
				}
			}
		});

	}

}
