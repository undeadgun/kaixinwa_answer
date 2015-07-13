//package com.qkhl.fragment;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//
//
//import com.qkhl.kaixinwa_android.R;
//import com.qkhl.view.ScrollListviewDelete;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
////本地存储答案文件夹页面
//public class FileLocal extends Fragment implements OnItemClickListener {
//
//	private Toast mToast;
//	private ScrollListviewDelete listviewDelete;
//	private DeleteAdapter adapter;
//	private List<Object> list;
//	private String name;
//	private File path;
////	private Handler mHandler;
////	private int start = 0;
////	private static int refreshCnt = 0;
////	private ArrayList<String> items = new ArrayList<String>();
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View filelayout = inflater.inflate(R.layout.file_listview, container,
//				false);
//
//		return filelayout;
//	}
//
//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//		list = new ArrayList<Object>();
//
////		mHandler = new Handler();
//		String sDStateString = Environment.getExternalStorageState();
//		if (sDStateString.equals(Environment.MEDIA_MOUNTED)) {
//			try {
//				// SD卡下面
//				// File SDFile = Environment.getExternalStorageDirectory();
//				// "/sdcard/"指定目录 "/sdcard/answer/"
//				File sdPath = new File("/sdcard/answer/");
//				if (sdPath.listFiles().length > 0) {
//					for (File file : sdPath.listFiles()) {
//						list.add(file.getName());
//					}
//				}
//			} catch (Exception e) {
//			}
//		}
//
//		listviewDelete = (ScrollListviewDelete) getActivity().findViewById(
//				R.id.filelist);
//		adapter = new DeleteAdapter();
//		listviewDelete.setAdapter(adapter);
//
//		listviewDelete.setOnItemClickListener(this);
//
//	}
//
//	@Override
//	public void onPause() {
//		// TODO Auto-generated method stub
//		super.onPause();
//		listviewDelete.setAdapter(adapter);
//	}
//	
//
//	// 删除
//	public void delete(final int position) {
//
//		list.remove(position);
//
//		listviewDelete.setAdapter(adapter);
//
//	}
//
//	// 增加
//	public void addfile(final int position) {
//
//		listviewDelete.setAdapter(adapter);
//	}
//
//	class DeleteAdapter extends BaseAdapter {
//
//		@Override
//		public int getCount() {
//			return list.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			return list.get(position);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		@Override
//		public View getView(final int position, View convertView,
//				ViewGroup parent) {
//			ViewHolder holder;
//			if (convertView == null) {
//				holder = new ViewHolder();
//				convertView = LayoutInflater.from(getActivity()).inflate(
//						R.layout.item_huadong, null);
//				holder.itemData = (TextView) convertView
//						.findViewById(R.id.itemData);
//				holder.btnDelete = (Button) convertView
//						.findViewById(R.id.btnDelete);
//				holder.btnNao = (Button) convertView.findViewById(R.id.btnNao);
//				convertView.setTag(holder);
//			} else {
//				holder = (ViewHolder) convertView.getTag();
//			}
//			// 删除按钮
//			holder.btnDelete.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//
//					File dfile = new File("/sdcard/answer/"
//							+ list.get(position).toString());
//					dfile.delete();
//					delete(position);
//				}
//			});
//			//
//			holder.itemData.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					showInfo("点击了文件： " + list.get(position));
//				}
//			});
//			// 添加文件夹
//			holder.btnNao.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Calendar c = Calendar.getInstance();
//					 name = "" + c.get(Calendar.YEAR)
//							+ c.get(Calendar.MONTH)
//							+ c.get(Calendar.DAY_OF_MONTH)
//							+ c.get(Calendar.HOUR_OF_DAY)
//							+ c.get(Calendar.MINUTE) + c.get(Calendar.SECOND)
//							+ ".html";
//					 path = new File("/sdcard/answer/" + name);
//					list.add(name);
//					// 创建文件夹
//					path.mkdirs();
//					// try {
//					// //创建文件
//					// // path.createNewFile();
//					// } catch (IOException e) {
//					// // TODO Auto-generated catch block
//					// e.printStackTrace();
//					// }
//					addfile(position);
//					showInfo("点击了添加文件");
//
//				}
//			});
//			holder.itemData.setText(list.get(position).toString());
//			return convertView;
//		}
//
//		class ViewHolder {
//			TextView itemData;
//			Button btnDelete;
//			Button btnNao;
//		}
//	}
//
//	// item监听器
//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position,
//			long id) {
////		Intent intent = new Intent();
////		intent.setAction(android.content.Intent.ACTION_VIEW);
////		intent.setDataAndType(Uri.fromFile(path), "webtext/*");
////		startActivity(intent);
////		 Intent intent= new Intent();        
////		    intent.setAction("android.intent.action.VIEW");    
////		    Uri content_url = Uri.parse();   
////		    intent.setData(content_url);  
////		    startActivity(intent);
//		    Intent intent= new Intent();        
//            intent.setAction("android.intent.action.VIEW");    
//            Uri content_url = Uri.parse(path+"");   
//            intent.setData(content_url);           
//            intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");   
//            startActivity(intent);
////		showInfo("onItemClick : " + position);
//	}
//
//	public void showInfo(String text) {
//		if (mToast == null) {
//			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
//		} else {
//			mToast.setText(text);
//			mToast.setDuration(Toast.LENGTH_SHORT);
//		}
//		mToast.show();
//	}
//
////	private void geneItems() {
////		for (int i = 0; i != 20; ++i) {
////			items.add("refresh cnt " + (++start));
////		}
////	}
////	private void onLoad() {
////		listviewDelete.stopRefresh();
////		listviewDelete.stopLoadMore();
////		listviewDelete.setRefreshTime("刚刚");
////	}
////	@Override
////	public void onRefresh() {
////		// TODO Auto-generated method stub
////		mHandler.postDelayed(new Runnable() {
////			@Override
////			public void run() {
////				start = ++refreshCnt;
////				items.clear();
////				geneItems();
////				// mAdapter.notifyDataSetChanged();
//////				adapter.setData(items);
//////				mListView.setAdapter(adapter);
////				onLoad();
////			}
////		}, 2000);
////	}
////
////	@Override
////	public void onLoadMore() {
////		// TODO Auto-generated method stub
////		mHandler.postDelayed(new Runnable() {
////			@Override
////			public void run() {
////				geneItems();
////				adapter.notifyDataSetChanged();
////				onLoad();
////			}
////		}, 2000);
////	}
//
//	
//	
//}
