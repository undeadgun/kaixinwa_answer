package com.qkhl.adapter;



import java.util.List;

import com.qkhl.activity.LoginPageAcitivity;
import com.qkhl.activity.MainActivity;
import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class viewPagerAdapter extends PagerAdapter{

	private List<View> views;
	private Activity activity;

	private static final String SHAREDPREFERENCES_NAME = "first_pref";

	public viewPagerAdapter(List<View> views, Activity activity) {
		this.views = views;
		this.activity = activity;
	}
	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView(views.get(arg1));
	}

	@Override
	public void finishUpdate(View arg0) {
	}
	
	

	@Override
	public int getCount() {
		if (views != null) {
			return views.size();
		}
		return 0;
	}
	@Override
	public Object instantiateItem(View arg0, int arg1) {
		((ViewPager) arg0).addView(views.get(arg1), 0);
		if (arg1 == views.size() - 1) {
			ImageView mStartWeiboImageButton = (ImageView) arg0
					.findViewById(R.id.iv_start_weibo);
			mStartWeiboImageButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 设置已经引导
					setGuided();
					goHome();

				}

			});
		}
		return views.get(arg1);
	}
	private void goHome() {
		// 跳转
		Intent intent = new Intent(activity, LoginPageAcitivity.class);
		activity.startActivity(intent);
		activity.finish();
	}
	private void setGuided() {
		SharedPreferences preferences = activity.getSharedPreferences(
				SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		// 存入数据
		editor.putBoolean("isFirstIn", false);
		// 提交修改
		editor.commit();
	}


	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0 == arg1);
	}
	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
	}

}
