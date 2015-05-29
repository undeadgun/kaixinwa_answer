package com.qkhl.activity;


import com.qkhl.adapter.AccountSafetyAdapter;
import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

public class accountSafetyActivity extends Activity{
    private ListView accountSafetyLv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_safety);
		accountSafetyLv=(ListView) findViewById(R.id.account_safety_listview);
		AccountSafetyAdapter mAdapter = new AccountSafetyAdapter(this);
		accountSafetyLv.setAdapter(mAdapter);
	}

}
