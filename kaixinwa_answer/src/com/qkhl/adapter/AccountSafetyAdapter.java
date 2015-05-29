package com.qkhl.adapter;


import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AccountSafetyAdapter extends BaseAdapter{
	private Context mContext;
	  
    public AccountSafetyAdapter(Context context) {  
        this.mContext=context;  
    }  

	@Override
	public int getCount() {
		
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		// TODO Auto-generated method stub
		 if(convertView==null){  
             convertView=LayoutInflater.from(mContext).inflate(R.layout.account_safety_listview_items, null);  
             ViewHolder viewHolder=new ViewHolder();  
             viewHolder.title=(TextView)convertView.findViewById(R.id.tvTitles);  
             viewHolder.details=(TextView)convertView.findViewById(R.id.tvAttribute);  
             convertView.setTag(viewHolder);  
         }  
		 ViewHolder cache=(ViewHolder)convertView.getTag();  
         //设置文本和图片，然后返回这个View，用于ListView的Item的展示  
         cache.title.setText(titles[position]);  
         cache.details.setText(detailInfo[position]); 
         return convertView;  
     }  
  
		
		
		
		
	
	
	public final class ViewHolder{
		public TextView title;
	    public TextView details;
		
	}
	private  String[] titles=new String[]{"账号","密码","微信","QQ"}; 
	private String[] detailInfo=new String[]{"18201005692","123123123","未绑定","未绑定"};
	
	

}

