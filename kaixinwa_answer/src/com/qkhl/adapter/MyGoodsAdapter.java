package com.qkhl.adapter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qkhl.kaixinwa_android.R;

import android.R.raw;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyGoodsAdapter extends BaseExpandableListAdapter{

//	 public static List<Map<String,Object>> typelist;
	 private LayoutInflater type_Inflater = null;
	 private LayoutInflater content_Inflater = null;
	 private Context context;
	 
	 private int page;
	 List<String> list;
	 
	
	
	
	public MyGoodsAdapter(Context context,int type) {
		type_Inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		content_Inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		typelist=new ArrayList<Map<String,Object>>();
//		this.page=page;
		
		
		  
			 
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
		
		
		if(arg3 == null) {
			
			arg3=content_Inflater.inflate(R.layout.goods_detailed_info, null);
			
			

		}
		
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
			arg2=type_Inflater.inflate(R.layout.my_goods_listview_items, null);
			tview=new TypeView();
			tview.goodsSent=(ImageView) arg2.findViewById(R.id.goods_sending_stats);
			tview.goodsTestImg=(ImageView) arg2.findViewById(R.id.my_goods_img);
			tview.happyBeanCount=(TextView) arg2.findViewById(R.id.happy_bean_count);
			tview.myGoodsTitle=(TextView) arg2.findViewById(R.id.my_goods_title);
			arg2.setTag(tview);
			
		}else
       {
			tview=(TypeView) arg2.getTag();
       }
		tview.myGoodsTitle.setText(list.get(arg0).toString());
		tview.happyBeanCount.setText(list.get(arg0).toString());
		
		 if(arg1)
	        {
			 tview.goodsTestImg.setBackgroundResource(R.drawable.my_goods_test_img);
			 tview.goodsSent.setBackgroundResource(R.drawable.goods_sent);
	        }
	        else
	        {
	        	tview.goodsTestImg.setBackgroundResource(R.drawable.my_goods_test_img);
	        	tview.goodsSent.setBackgroundResource(R.drawable.goods_sent);
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
	
	public class TypeView {
		private TextView myGoodsTitle;
		private TextView happyBeanCount;
		
		private ImageView goodsTestImg;
		private ImageView goodsSent;
	}

}
