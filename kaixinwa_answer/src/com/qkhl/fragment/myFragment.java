package com.qkhl.fragment;

import java.io.File;

import com.qkhl.activity.my_codeActivity;
import com.qkhl.activity.my_friendsActivity;
import com.qkhl.activity.my_goodsActivity;
import com.qkhl.activity.my_messageActivity;
import com.qkhl.activity.my_missionActivity;
import com.qkhl.activity.my_shareActivity;
import com.qkhl.activity.setup_meActivity;
import com.qkhl.kaixinwa_android.R;
import com.qkhl.util.MD5Utils;
import com.qkhl.util.SharePreferUtil;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class myFragment extends Fragment implements OnClickListener {
	private View view;
	private View my_mission, my_share, my_goods, my_code, my_friends,
			my_message;
	private TextView my_setup_name, my_setup_mood, my_setup_address;

	private ImageView my_setup_pichead;
	String imgname = MD5Utils.stringToMD5(SharePreferUtil.getString("userID",
			"")) + ".png";// pic name
	File f = new File(Environment.getExternalStorageDirectory()
			+ "/kaixinwa_answer/avatar/", imgname);
	String imgpath = f.getPath().toString();// pic path
	private Boolean wenzi = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.me, null);

		initView();
		return view;

	}

	private void initView() {

		my_mission = view.findViewById(R.id.my_mission);
		my_share = view.findViewById(R.id.my_share);
		my_goods = view.findViewById(R.id.my_goods);
		my_code = view.findViewById(R.id.my_code);
		my_friends = view.findViewById(R.id.my_friends);
		my_message = view.findViewById(R.id.my_message);
		my_setup_pichead = (ImageView) view
				.findViewById(R.id.head_picture_imageview);
		my_setup_name = (TextView) view.findViewById(R.id.name_textview);
		my_setup_mood = (TextView) view.findViewById(R.id.mood_textview);
		my_setup_address = (TextView) view.findViewById(R.id.address_textview);

		my_mission.setOnClickListener(this);
		my_share.setOnClickListener(this);
		my_goods.setOnClickListener(this);
		my_code.setOnClickListener(this);
		my_friends.setOnClickListener(this);
		my_message.setOnClickListener(this);
		my_setup_pichead.setOnClickListener(this);
		my_setup_name.setOnClickListener(this);
		my_setup_mood.setOnClickListener(this);
		my_setup_address.setOnClickListener(this);

	}

	@Override
	public void onStart() {
		super.onStart();
		if (f == null) {
			my_setup_pichead.setBackgroundResource(R.drawable.my_touxiang);
		} else {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			Bitmap bm = BitmapFactory.decodeFile(imgpath, options);
			my_setup_pichead.setImageBitmap(bm);
		}
		if (SharePreferUtil.getString("setname", "").equals("")) {

			my_setup_name.setText("请设置昵称");
			my_setup_name.setTextSize(12);

		} else {
			my_setup_name.setText(SharePreferUtil.getString("setname", ""));
		}

		if (SharePreferUtil.getString("setmood", "").equals("")) {
			my_setup_mood.setText("个性签名");
			my_setup_mood.setTextSize(10);

		} else {
			my_setup_mood.setText(SharePreferUtil.getString("setmood", ""));
		}
		if (SharePreferUtil.getString("setaddress", "").equals("")) {
			my_setup_address.setText("地址");
			my_setup_address.setTextSize(10);

		} else {
			my_setup_address.setText(SharePreferUtil.getString("setaddress", ""));
					
		}

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wenzi = true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.my_mission:
			setTabSelection(0);

			break;
		case R.id.my_share:
			setTabSelection(1);

			break;
		case R.id.my_goods:
			setTabSelection(2);
			break;
		case R.id.my_code:
			setTabSelection(3);

			break;
		case R.id.my_friends:
			setTabSelection(4);

			break;
		case R.id.my_message:
			setTabSelection(5);

			break;
		case R.id.head_picture_imageview:
			setTabSelection(6);

			break;
		case R.id.name_textview:
			setTabSelection(7);

			break;
		case R.id.mood_textview:
			setTabSelection(8);

			break;
		case R.id.address_textview:
			setTabSelection(9);

			break;

		default:
			break;
		}

	}

	private void setTabSelection(int index) {
		switch (index) {

		case 0:
			Intent intent1 = new Intent(getActivity(), my_missionActivity.class);
			startActivity(intent1);

			break;
		case 1:
			Intent intent2 = new Intent(getActivity(), my_shareActivity.class);
			startActivity(intent2);
			break;
		case 2:

			Intent intent3 = new Intent(getActivity(), my_goodsActivity.class);
			startActivity(intent3);
			break;
		case 3:
			Intent intent4 = new Intent(getActivity(), my_codeActivity.class);
			startActivity(intent4);
			break;
		case 4:
			Intent intent5 = new Intent(getActivity(), my_friendsActivity.class);
			startActivity(intent5);
			break;
		case 5:
			Intent intent6 = new Intent(getActivity(), my_messageActivity.class);
			startActivity(intent6);
			break;
		case 6:
			Intent intent7 = new Intent(getActivity(), setup_meActivity.class);
			startActivity(intent7);
			break;
		case 7:
			Intent intent8 = new Intent(getActivity(), setup_meActivity.class);
			startActivity(intent8);
			break;
		case 8:
			Intent intent9 = new Intent(getActivity(), setup_meActivity.class);
			startActivity(intent9);
			break;
		case 9:
			Intent intent10 = new Intent(getActivity(), setup_meActivity.class);
			startActivity(intent10);
			break;

		default:
			break;

		}
	}

}
