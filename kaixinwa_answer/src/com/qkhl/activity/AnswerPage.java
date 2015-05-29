package com.qkhl.activity;


import java.io.File;
import java.util.Calendar;

import com.qkhl.adapter.ResolvePage;
import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AnswerPage extends Activity {

	private ImageView back,share;
	private ExpandableListView answerlist;
	private TextView xiaoshu,save;
	private String path="/sdcard/answer/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.answerpage);
		back=(ImageView) findViewById(R.id.back);
		share=(ImageView) findViewById(R.id.Share);
		answerlist=(ExpandableListView) findViewById(R.id.answerlist);
		xiaoshu=(TextView) findViewById(R.id.xiaoshuname);
		save=(TextView) findViewById(R.id.save);
		Intent intent=getIntent();
		String lianjie = intent.getStringExtra("lianjie");
		Log.e("TAG", lianjie);
//		answerlist.expandGroup(1);//设置第一组张开
		answerlist.setGroupIndicator(null);//除去自带的箭头
		ResolvePage tta=new ResolvePage(AnswerPage.this,0,lianjie);
		answerlist.setAdapter(tta);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AnswerPage.this.finish();
			}
		});
		
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(AnswerPage.this, "已保存至答案", Toast.LENGTH_LONG).show();
				
				// 文件存储路径
				File lf = new File(path);
				// Make sure the Pictures directory exists.
				lf.mkdirs();
				String name = "";
				
			}
		});
	}
	
	
}
