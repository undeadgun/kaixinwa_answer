package com.qkhl.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.TokenGenerator;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import com.qkhl.common.GetAndUloadImg;
import com.qkhl.kaixinwa_android.R;
import com.qkhl.util.MD5Utils;
import com.qkhl.util.SharePreferUtil;
import com.qkhl.util.Tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class setup_meActivity extends Activity implements OnClickListener {
	private View setname, setmood, setweixin, setqq, setaddress, setschool,
			setheadpic, name_dialog;
	private ImageButton return_imgbutton;
	private TextView setnametext, setmoodtext, setweixintext, setqqtext,
			setaddresstext, setschooltext, mysetuptextview;
	private TextView name_textview, mood_textview, weixin_textview,
			qq_textview, address_textview, school_textview;
	private String str;
	private ImageView setheadpic_img;
	private String[] items = new String[] { "选择本地图片", "拍照" };
	/* 头像名称 */
	private static final String IMAGE_FILE_NAME = "setheadpic_img.jpg";

	/* 请求码 */
	private static final int IMAGE_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;
    public static Handler handler;
    private ProgressDialog MyDialog;

	String imgname=MD5Utils.stringToMD5(SharePreferUtil.getString("userID", ""))+".png";//pic name
    File f = new File(Environment.getExternalStorageDirectory()+ "/kaixinwa_answer/avatar/",imgname);
    String imgpath=f.getPath().toString();//pic path


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_setup);
		initViews();
		loadavatar();


	}
	

    
	
	//加載頭像
	public void loadavatar(){
		String img = imgpath;    
        BitmapFactory.Options options = new BitmapFactory.Options();  
        options.inSampleSize = 2;  
        Log.i("chen", "============options======="+options);  
        Bitmap bm = BitmapFactory.decodeFile(img, options);  
        setheadpic_img.setImageBitmap(bm);  

	}
 
	
	private void initViews() {
		return_imgbutton = (ImageButton) findViewById(R.id.setup_return_imgbutton);
		setname = findViewById(R.id.setup_name);
		setmood = findViewById(R.id.setup_mood);
		setweixin = findViewById(R.id.setup_weixin);
		setqq = findViewById(R.id.setup_qq);
		setaddress = findViewById(R.id.setup_address);
		setschool = findViewById(R.id.setup_school);
		setheadpic = findViewById(R.id.setup_headpic);
		name_textview = (TextView) findViewById(R.id.name_textview);
		mood_textview = (TextView) findViewById(R.id.mood_textview);
		weixin_textview = (TextView) findViewById(R.id.weixin_textview);
		qq_textview = (TextView) findViewById(R.id.qq_textview);
		address_textview = (TextView) findViewById(R.id.address_textview);
		school_textview = (TextView) findViewById(R.id.school_textview);
		mysetuptextview = (TextView) findViewById(R.id.mysetup_textview);
		setheadpic_img = (ImageView) findViewById(R.id.setup_headpic_imgview);

		setnametext = (TextView) findViewById(R.id.setup_name_textview);
		setmoodtext = (TextView) findViewById(R.id.setup_mood_textview);
		setweixintext = (TextView) findViewById(R.id.setup_weixin_textview);
		setqqtext = (TextView) findViewById(R.id.setup_qq_textview);
		setaddresstext = (TextView) findViewById(R.id.setup_address_textview);
		setschooltext = (TextView) findViewById(R.id.setup_school_textview);

		return_imgbutton.setOnClickListener(this);
		setname.setOnClickListener(this);
		setmood.setOnClickListener(this);
		setweixin.setOnClickListener(this);
		setqq.setOnClickListener(this);
		setaddress.setOnClickListener(this);
		setschool.setOnClickListener(this);
		setheadpic.setOnClickListener(this);
		mysetuptextview.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.setup_return_imgbutton:
			setTabSelection(0);
			break;
		case R.id.setup_name:
			setTabSelection(1);

			break;
		case R.id.setup_mood:
			setTabSelection(2);
			break;
		case R.id.setup_weixin:
			setTabSelection(3);
			break;
		case R.id.setup_qq:
			setTabSelection(4);
			break;
		case R.id.setup_address:
			setTabSelection(5);
			break;
		case R.id.setup_school:
			setTabSelection(6);
			break;
		case R.id.mysetup_textview:
			setTabSelection(7);
			break;
		case R.id.setup_headpic:
			setTabSelection(8);
			break;

		default:
			break;
		}

	}

	private void setTabSelection(int index) {
		Intent intent = new Intent();
		switch (index) {
		case 0:
			finish();
			break;
		case 1:
			intent.setClass(setup_meActivity.this,
					update_userinfoActivity.class);
			str = name_textview.getText().toString();
			intent.putExtra("name_textview", str);
			intent.putExtra("url",
					"http://123.57.209.98/hlwh_android/update_name.php");
			startActivity(intent);
			updateMessage(setnametext);

			break;
		case 2:
			intent.setClass(setup_meActivity.this,
					update_userinfoActivity.class);
			str = mood_textview.getText().toString();
			intent.putExtra("url",
					"http://123.57.209.98/hlwh_android/update_signature.php");
			intent.putExtra("name_textview", str);
			startActivity(intent);
			updateMessage(setmoodtext);

			break;
		case 3:
			intent.setClass(setup_meActivity.this,
					update_userinfoActivity.class);
			str = weixin_textview.getText().toString();
			intent.putExtra("url",
					"http://123.57.209.98/hlwh_android/update_weixin.php");
			intent.putExtra("name_textview", str);
			startActivity(intent);
			updateMessage(setweixintext);

			break;
		case 4:
			intent.setClass(setup_meActivity.this,
					update_userinfoActivity.class);
			str = qq_textview.getText().toString();
			intent.putExtra("url",
					"http://123.57.209.98/hlwh_android/update_qq.php");
			intent.putExtra("name_textview", str);
			startActivity(intent);
			updateMessage(setqqtext);

			break;
		case 5:
			intent.setClass(setup_meActivity.this,
					update_userinfoActivity.class);
			str = address_textview.getText().toString();
			intent.putExtra("url",
					"http://123.57.209.98/hlwh_android/update_adress.php");
			intent.putExtra("name_textview", str);
			startActivity(intent);
			updateMessage(setaddresstext);

			break;
		case 6:
			intent.setClass(setup_meActivity.this,
					update_userinfoActivity.class);
			str = school_textview.getText().toString();
			intent.putExtra("url",
					"http://123.57.209.98/hlwh_android/update_school.php");
			intent.putExtra("name_textview", str);
			startActivity(intent);
			updateMessage(setschooltext);

			break;
		case 7:
			finish();
			break;
		case 8:
			showDialog();
			break;

		default:
			break;
		}
	}

	private void updateMessage(final TextView tv) {
		update_userinfoActivity.renametext = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.obj != null) {

					tv.setText(msg.obj.toString() + "");
				}
			}

		};

	}

	/**
	 * 显示选择对话框
	 */
	private void showDialog() {

		new AlertDialog.Builder(this)
				.setTitle("设置头像")
				.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						

						switch (which) {
						case 0:

							Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
							startActivityForResult(picture, IMAGE_REQUEST_CODE);
							break;
						case 1:

							Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							// 判断存储卡是否可以用，可用进行存储
							if (Tools.hasSdcard()) {

								intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME)));
							}

							startActivityForResult(intentFromCapture,CAMERA_REQUEST_CODE);
							break;
						}
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_CANCELED) {
			switch (requestCode) {
			case IMAGE_REQUEST_CODE:

				startPhotoZoom(data.getData());
				break;
			case CAMERA_REQUEST_CODE:
				if (Tools.hasSdcard()) {
					File tempFile = new File(
							Environment.getExternalStorageDirectory()

							+ "/" + IMAGE_FILE_NAME);
					startPhotoZoom(Uri.fromFile(tempFile));
				} else {
					Toast.makeText(setup_meActivity.this, "未找到存储卡，无法存储照片！",
							Toast.LENGTH_LONG).show();
				}

				break;
			case RESULT_REQUEST_CODE:
				if (data != null) {
					getImageToView(data);

				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 2);
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	private void getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Log.e("tag", "保存图片");
			
			

			try {
				f.createNewFile();
				FileOutputStream out = new FileOutputStream(f);
				toRoundBitmap(photo).compress(Bitmap.CompressFormat.PNG, 90, out);
				out.flush();
				out.close();
				Log.i("tag", "已经保存"+imgname);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						new GetAndUloadImg().show(imgpath, imgname);

					}
				}).start();
				loadavatar();
				 MyDialog = ProgressDialog.show(setup_meActivity.this, "上传头像" , "正在上传", true);
				 handler = new Handler() {
						public void handleMessage(android.os.Message msg) {
							if (msg.what == 1) {
								MyDialog.dismiss();

							} else {
							}
						};
					};
 
		      

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 保存裁剪之后的图片为圆形
	 * 
	 * @param bitmap
	 */
	public Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

}
