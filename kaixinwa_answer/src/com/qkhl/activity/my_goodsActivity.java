package com.qkhl.activity;


import java.util.List;
import java.util.Map;

import com.qkhl.adapter.MyGoodsAdapter;

import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class my_goodsActivity extends Activity{
    
      
    private ImageButton goBack;
    private ExpandableListView goodsList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mygoods);
       goodsList=(ExpandableListView) findViewById(R.layout.my_goods_listview_items);
       goBack=(ImageButton) findViewById(R.id.return_button);
//       goodsList.setGroupIndicator(null);
//       Intent intent=getIntent();
//		String lianjie = intent.getStringExtra("lianjie");
//		Log.e("TAG", lianjie);
       MyGoodsAdapter myGoodsAdapter=new MyGoodsAdapter(my_goodsActivity.this,0);
       goodsList.setAdapter(myGoodsAdapter);
       goBack.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(my_goodsActivity.this, "返回上页", Toast.LENGTH_SHORT).show();
		}
	});
	}

}
