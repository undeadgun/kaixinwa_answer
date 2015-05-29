package com.qkhl.fragment;

import com.qkhl.activity.my_codeActivity;
import com.qkhl.activity.my_friendsActivity;
import com.qkhl.activity.my_goodsActivity;
import com.qkhl.activity.my_messageActivity;
import com.qkhl.activity.my_missionActivity;
import com.qkhl.activity.my_shareActivity;
import com.qkhl.activity.setup_meActivity;
import com.qkhl.kaixinwa_android.R;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class myFragment extends Fragment implements OnClickListener {
	private View view;
	private View my_mission, my_share, my_goods, my_code, my_friends,
			my_message, my_setup;

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
		my_setup = view.findViewById(R.id.my_setup_layout);

		my_mission.setOnClickListener(this);
		my_share.setOnClickListener(this);
		my_goods.setOnClickListener(this);
		my_code.setOnClickListener(this);
		my_friends.setOnClickListener(this);
		my_message.setOnClickListener(this);
		my_setup.setOnClickListener(this);

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
		case R.id.my_setup_layout:
			setTabSelection(6);

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

			Intent intent3=new Intent(getActivity(),my_goodsActivity.class);
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
			Intent intent = new Intent(getActivity(), setup_meActivity.class);
			startActivity(intent);
			break;

		default:
			break;

		}
	}

}
