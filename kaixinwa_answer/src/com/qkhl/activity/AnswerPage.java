package com.qkhl.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkhl.adapter.MyFragmentPagerAdapter;
import com.qkhl.adapter.ZSYYFragment;
import com.qkhl.kaixinwa_android.R;

public class AnswerPage extends FragmentActivity {

	private ImageView share;
	private ImageView back;
	// private ImageView save;
	// private ExpandableListView answerlist;
	// private TextView xiaoshu;
	// /**
	// *当前打开的父节点
	// */
	// private int the_group_expand_position=-1;
	// /**
	// * 打开的父节点所与的子节点数
	// */
	// private int position_child_count=0;
	// /**
	// * 是否有打开的父节点
	// */
	// private boolean isExpanding=false;
	// private RelativeLayout titlegone;
	// private ImageView xianshitu,xianshijiantou;
	// private TextView xianshizi;
	private List<String> list;
	private ViewPager vp;
	private String lianjie;
	// private int[] tu = { R.drawable.zsylq_bg, R.drawable.xllht_bg,
	// R.drawable.yglyz_bg };;

	private View zsyl;
	private View xllh;
	private View ygly;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.answerpage);
		back = (ImageView) findViewById(R.id.back);
		share = (ImageView) findViewById(R.id.Share);
		zsyl = findViewById(R.id.firstpage_layout);
		xllh = findViewById(R.id.market_layout);
		;
		ygly = findViewById(R.id.shoppingcart_layout);
		;
		// answerlist=(ExpandableListView) findViewById(R.id.answerlist);
		// titlegone=(RelativeLayout) findViewById(R.id.xianshiyingcang);
		// xiaoshu=(TextView) findViewById(R.id.xiaoshuname);
		// xianshitu=(ImageView) findViewById(R.id.xianshi);
		// xianshijiantou=(ImageView) findViewById(R.id.xianshijiantou);
		// xianshizi=(TextView) findViewById(R.id.xianshiitem);
		// save=(ImageView) findViewById(R.id.save);

		Intent intent = getIntent();
		lianjie = intent.getStringExtra("lianjie");
		// DisplayMetrics dm = new DisplayMetrics();
		// getWindowManager().getDefaultDisplay().getMetrics(dm);
		// int height=dm.heightPixels;
		zsyl.setOnClickListener(new ViewClickListener(0));
		xllh.setOnClickListener(new ViewClickListener(1));
		ygly.setOnClickListener(new ViewClickListener(2));
		// String lianjie = intent.getStringExtra("lianjie").substring(20);

		Log.e("TAG", lianjie);
		InitViewPager();
		// answerlist.expandGroup(1);//设置第一组张开
		// answerlist.setGroupIndicator(null);//除去自带的箭头
		// ResolvePage tta=new ResolvePage(AnswerPage.this,0,lianjie,height);
		// answerlist.setAdapter(tta);

		list = new ArrayList<String>();
		list.add("知识月亮泉");
		list.add("心路浪花摊");
		list.add("阳光绿野洲");
		// /**
		// * 监听父节点打开的事件
		// */
		// answerlist.setOnGroupExpandListener(new OnGroupExpandListener() {
		//
		// @Override
		// public void onGroupExpand(int arg0) {
		//
		// the_group_expand_position=arg0;
		// position_child_count=1;
		// isExpanding=true;
		//
		// }
		// });
		//
		// /**
		// * 监听父节点关闭的事件
		// */
		// answerlist.setOnGroupCollapseListener(new OnGroupCollapseListener() {
		//
		// @Override
		// public void onGroupCollapse(int arg0) {
		// // TODO Auto-generated method stub
		// if(titlegone.getVisibility()==View.VISIBLE){
		// titlegone.setVisibility(View.GONE);
		// }
		// isExpanding=false;
		//
		// }
		// });
		//
		// titlegone.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// titlegone.setVisibility(View.GONE);
		// answerlist.collapseGroup(the_group_expand_position);
		// }
		// });
		//
		//
		// /**
		// * 通过setOnScrollListener来监听列表上下滑动时item显示和消失的事件
		// */
		// answerlist.setOnScrollListener(new OnScrollListener() {
		//
		// @Override
		// public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3)
		// {
		// // TODO Auto-generated method stub
		//
		//
		// if(isExpanding){
		// // 当当前第一个item id小于打开的父节点id 或大于打开的父节点id和它的子节点总数之和时
		// if(arg1<the_group_expand_position
		// ||arg1>(the_group_expand_position+position_child_count)){
		// titlegone.setVisibility(View.GONE);
		// }else{
		// titlegone.setVisibility(View.VISIBLE);
		//
		// xianshitu.setBackgroundResource(tu[the_group_expand_position]);
		// xianshijiantou.setBackgroundResource(R.drawable.expandablelist_donw);
		// xianshizi.setText(list.get(the_group_expand_position).toString()+"");
		// //
		// tv.setText(((Map)parentData.get(the_group_expand_position)).get("parend").toString());
		// }
		// }
		//
		// }
		// });
		//
		//

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AnswerPage.this.finish();
			}
		});

		// save.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// String sdState = Environment.getExternalStorageState(); // 判断sd卡是否存在
		// // 检查SD卡是否可用
		// if (!sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
		// Toast.makeText(AnswerPage.this, "SD卡未准备好！",
		// Toast.LENGTH_SHORT).show();
		// }
		//
		//
		//
		// // 根据当前时间生成文件名称
		// Calendar c = Calendar.getInstance();
		// String name = "" + c.get(Calendar.YEAR) + c.get(Calendar.MONTH)
		// + c.get(Calendar.DAY_OF_MONTH)
		// + c.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MINUTE)
		// + c.get(Calendar.SECOND) + ".html";
		//
		// // 文件存储路径
		// File ring = new File("/sdcard/answer/" + name);
		// if (initDownPath("/sdcard/answer/")==true) {
		// File path = new File("/sdcard/answer/");
		// path.mkdirs();
		// try {
		// ring.createNewFile();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }else {
		// // 合成完整路径，注意 / 分隔符
		//
		// try {
		// ring.createNewFile();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		//
		//
		// // .saveToFile(string);
		//
		// View toastRoot = getLayoutInflater().inflate(R.layout.toast, null);
		// TextView message = (TextView) toastRoot.findViewById(R.id.message);
		// message.setText("已保存至答案");
		//
		// Toast toastStart = new Toast(AnswerPage.this);
		// toastStart.setGravity(Gravity.CENTER, 0, 0);
		// toastStart.setDuration(Toast.LENGTH_LONG);
		// toastStart.setView(toastRoot);
		// toastStart.show();
		//
		//
		// }
		// });
	}

	private ArrayList<Fragment> fragmentList;

	public void InitViewPager() {
		vp = (ViewPager) findViewById(R.id.viewpager);
		fragmentList = new ArrayList<Fragment>();
		Fragment zsyyFragment = new ZSYYFragment(
				"http://qkhl-api.com/qkhl_api/index.php/AnswerPage/get_a");
		Fragment yyFragment = new ZSYYFragment(
				"http://qkhl-api.com/qkhl_api/index.php/AnswerPage/get_b");
		Fragment yFragment = new ZSYYFragment(
				"http://qkhl-api.com/qkhl_api/index.php/AnswerPage/get_c");
		fragmentList.add(zsyyFragment);
		fragmentList.add(yyFragment);
		fragmentList.add(yFragment);
		vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),
				fragmentList));
		vp.setCurrentItem(0);
		vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

				Toast.makeText(AnswerPage.this, list.get(arg0).toString(),
						Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	// 三个课时按钮事件
	public class ViewClickListener implements View.OnClickListener {

		private int index = 0;

		public ViewClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			vp.setCurrentItem(index);
			switch (index) {
			case 0:

				break;
			case 1:

				break;
			case 2:

				break;
			default:
				break;
			}

		}
	}

//	public boolean initDownPath(String path) {
//		if (Environment.getExternalStorageState().equals(
//				android.os.Environment.MEDIA_MOUNTED)) {
//			File file = new File(path);
//			if (!file.exists()) {
//				// file.mkdirs();
//				return true;
//			}
//		}
//		return false;
//	}

}
