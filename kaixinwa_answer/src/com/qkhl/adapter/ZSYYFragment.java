package com.qkhl.adapter;


import com.qkhl.kaixinwa_android.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
 @SuppressLint("ValidFragment") public class ZSYYFragment extends Fragment{

	
	private View nh;
	private WebView wv;
	private String url;
	

	public ZSYYFragment(String url){
		this.url=url;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		nh=inflater.inflate(R.layout.weblist, container , false);
		wv=(WebView) nh.findViewById(R.id.webanswer);
		WebSettings websett = wv.getSettings();
		websett.setJavaScriptEnabled(true);
		websett.setCacheMode(WebSettings.LOAD_NO_CACHE);
		wv.loadUrl(url);
		return nh;
	
	}
	
	
	
	
}
