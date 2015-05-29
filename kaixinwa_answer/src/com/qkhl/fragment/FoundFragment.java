package com.qkhl.fragment;



import com.qkhl.activity.happyfroggamesActivity;
import com.qkhl.activity.happyfroghomeworkActivity;
import com.qkhl.activity.happymicroclassActivity;
import com.qkhl.activity.happyradioActivity;
import com.qkhl.activity.happystoreActivity;
import com.qkhl.activity.happytravelstudyActivity;
import com.qkhl.activity.happywisdomclassActivity;
import com.qkhl.kaixinwa_android.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FoundFragment extends Fragment {
	private Button btnRadio;
	private Button btnMicroClass;
	private Button btnStore;
	private Button btnTravelStudy;
	private Button btnWisdomClass;
	private Button btnHomeWork;
	private Button btnGames;
	private Intent intent;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View firstpagelayout = inflater.inflate(R.layout.faxian_fragment, container, false);
		return firstpagelayout;
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		btnRadio=(Button) getActivity().findViewById(R.id.imgBtnRadio);
		btnMicroClass=(Button) getActivity().findViewById(R.id.imgBtnMicroClass);
		btnStore=(Button) getActivity().findViewById(R.id.imgBtnStore);
		btnTravelStudy=(Button) getActivity().findViewById(R.id.imgBtnTravelStudy);
		btnWisdomClass=(Button) getActivity().findViewById(R.id.imgBtnWisdomClass);
		btnHomeWork=(Button) getActivity().findViewById(R.id.imgBtnHomeWork);
		btnGames=(Button) getActivity().findViewById(R.id.imgBtnGames);
		btnTravelStudy.setOnClickListener(new onClicke());
		btnRadio.setOnClickListener(new onClicke());
		btnMicroClass.setOnClickListener(new onClicke());
		btnStore.setOnClickListener(new onClicke());
		btnWisdomClass.setOnClickListener(new onClicke());
		btnHomeWork.setOnClickListener(new onClicke());
		btnGames.setOnClickListener(new onClicke());
	}
public class onClicke implements OnClickListener {
		
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id){
		case R.id.imgBtnRadio:
		    intent=new Intent();
		    intent.setClass(getActivity(), happyradioActivity.class);
		    getActivity().startActivity(intent);
			break;
		case R.id.imgBtnMicroClass:
			 intent=new Intent();
			    intent.setClass(getActivity(), happymicroclassActivity.class);
			    getActivity().startActivity(intent);
			    
			break;
		case R.id.imgBtnTravelStudy:
			  intent=new Intent();
			    intent.setClass(getActivity(), happytravelstudyActivity.class);
			    getActivity().startActivity(intent);
			break;
		case R.id.imgBtnStore:
			  intent=new Intent();
			    intent.setClass(getActivity(), happystoreActivity.class);
			    getActivity().startActivity(intent);
			break;
		case R.id.imgBtnWisdomClass:
			  intent=new Intent();
			    intent.setClass(getActivity(), happywisdomclassActivity.class);
			    getActivity().startActivity(intent);
			break;
		case R.id.imgBtnHomeWork:
			  intent=new Intent();
			    intent.setClass(getActivity(), happyfroghomeworkActivity.class);
			    getActivity().startActivity(intent);
			break;
		case R.id.imgBtnGames:
			  intent=new Intent();
			    intent.setClass(getActivity(), happyfroggamesActivity.class);
			    getActivity().startActivity(intent);
			break;
		}
	}
	
		
}
   

}





