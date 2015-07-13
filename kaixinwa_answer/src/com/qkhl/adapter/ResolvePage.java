package com.qkhl.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qkhl.kaixinwa_android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.JsResult;

import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//答案显示页面的Adapter；
@SuppressLint("SetJavaScriptEnabled")
public class ResolvePage extends BaseExpandableListAdapter {

	// public static List<Map<String,Object>> typelist;
	private LayoutInflater type_Inflater = null;
	private LayoutInflater content_Inflater = null;
	private Context mcontext;
	private String conurl;
	private int page;
	private List<String> list;
	
	

	private ItemView iv = null;

	private int[] tu = { R.drawable.zsylq_bg, R.drawable.xllht_bg,
			R.drawable.yglyz_bg };;
private int mheight;
	public ResolvePage(Context context, int type, String conurl,int height) {
		this.mcontext=context;
		this.mheight=height;
		type_Inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		content_Inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// typelist=new ArrayList<Map<String,Object>>();
		// this.page=page;
		this.conurl = conurl;
		list = new ArrayList<String>();
		list.add("知识月亮泉");
		list.add("心路浪花摊");
		list.add("阳光绿野洲");
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View webviewlist,
			ViewGroup arg4) {
		iv = new ItemView();
		if (webviewlist == null) {
			
			webviewlist = content_Inflater.inflate(R.layout.weblist, null);
			iv.webanswer = (WebView) webviewlist.findViewById(R.id.webanswer);
			// iv.ceshi=(TextView) arg3.findViewById(R.id.ceshi);

			webviewlist.setTag(iv);
		} else {
			iv = (ItemView) webviewlist.getTag();
		}
		
		WebSettings websett = iv.webanswer.getSettings();
//		iv.webanswer.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		Log.e("tag", conurl);

		
		websett.setJavaScriptEnabled(true);
		websett.setCacheMode(WebSettings.LOAD_NO_CACHE);
//		websett.setJavaScriptCanOpenWindowsAutomatically(true);

		iv.webanswer.loadUrl(conurl);
		iv.webanswer.addJavascriptInterface(new Object() {
			@JavascriptInterface
			public void get_height(String answer) {
				Log.e("tag", answer+":ergao");
			
			
				LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) iv.webanswer.getLayoutParams(); 
		        linearParams.height =  (int) (Integer.parseInt(answer )/ mheight + 0.5f);
		      
		        iv.webanswer.setLayoutParams(linearParams);
		        Log.e("tag", linearParams.height+":linearParams.height"+linearParams+":linearParams");
				
			}

		}, "demo");

	
		
//		iv.webanswer.setWebChromeClient(new WebChromeClient() {
//
//			@Override
//			public boolean onJsAlert(WebView view, String url, String message,
//					JsResult result) {
//				// TODO Auto-generated method stub
//
//				return super.onJsAlert(view, url, message, result);
//
//			}
//
//			@Override
//			public void onProgressChanged(WebView view, int newProgress) {
//
//				super.onProgressChanged(view, newProgress);
//
//			}
//
//		});

//		iv.webanswer.setWebViewClient(new WebViewClient() {
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//				view.loadUrl(url);
//				return true;
//			}
//
//		});
		
		return webviewlist;
	}

	

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		TypeView tview = null;
		if (arg2 == null) {
			arg2 = type_Inflater.inflate(R.layout.title_type, null);
			tview = new TypeView();
			tview.leixingname = (TextView) arg2
					.findViewById(R.id.zhishiyueliang);
			tview.jiant = (ImageView) arg2.findViewById(R.id.jiantou);
			tview.tubiao = (ImageView) arg2.findViewById(R.id.tixingtubiao);
			arg2.setTag(tview);

		} else {
			tview = (TypeView) arg2.getTag();
		}
		tview.leixingname.setText(list.get(arg0).toString());
		tview.tubiao.setBackgroundResource(tu[arg0]);

		if (arg1) {
			tview.jiant.setBackgroundResource(R.drawable.expandablelist_donw);
		} else {
			tview.jiant.setBackgroundResource(R.drawable.blue_jiantou_bg);
		}

		return arg2;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	public class ItemView {
		private WebView webanswer;
		// private TextView ceshi;
	}

	
	
	 
	public class TypeView {
		private TextView leixingname;
		private ImageView jiant, tubiao;
	}

	 
}
