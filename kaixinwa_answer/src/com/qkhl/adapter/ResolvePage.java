package com.qkhl.adapter;

import java.util.ArrayList;
import java.util.List;

import com.qkhl.kaixinwa_android.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResolvePage extends BaseExpandableListAdapter{

//	 public static List<Map<String,Object>> typelist;
	 private LayoutInflater type_Inflater = null;
	 private LayoutInflater content_Inflater = null;
	 private Context context;
	 private String conurl;
	 private int page;
	 List<String> list;
	 
	
	
	
	public ResolvePage(Context context,int type,String conurl) {
		type_Inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		content_Inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		typelist=new ArrayList<Map<String,Object>>();
//		this.page=page;
		this.conurl=conurl;
		
		  list=new ArrayList<String>();
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
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		ItemView iv=null;
		
		if(arg3 == null) {
			iv = new ItemView();
			arg3=content_Inflater.inflate(R.layout.weblist, null);
			iv.webanswer=(WebView) arg3.findViewById(R.id.webanswer);
			iv.ceshi=(TextView) arg3.findViewById(R.id.ceshi);
			
			arg3.setTag(iv);
		}else {
			iv = (ItemView)arg3.getTag();
		}
		iv.webanswer.loadUrl(conurl);
		Log.e("tag", conurl);
		iv.webanswer.setWebViewClient(new WebViewClient(){
			   public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
		
			    
					view.loadUrl(url);
			         return true;  
			   }
			  });
		iv.webanswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "准备就绪。。。", Toast.LENGTH_SHORT).show();
			}
		});
		
		return arg3;
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
		TypeView tview=null;
		if(arg2==null){
			arg2=type_Inflater.inflate(R.layout.title_type, null);
			tview=new TypeView();
			tview.leixingname=(TextView) arg2.findViewById(R.id.zhishiyueliang);
			tview.jiant=(ImageView) arg2.findViewById(R.id.jiantou);
			arg2.setTag(tview);
			
		}else
        {
			tview=(TypeView) arg2.getTag();
        }
		tview.leixingname.setText(list.get(arg0).toString());
		
		 if(arg1)
	        {
			 tview.jiant.setBackgroundResource(R.drawable.ic_launcher);
	        }
	        else
	        {
	        	tview.jiant.setBackgroundResource(R.drawable.ic_launcher);
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
		private TextView ceshi;
	}
	public class TypeView {
		private TextView leixingname;
		private ImageView jiant;
	}

}
