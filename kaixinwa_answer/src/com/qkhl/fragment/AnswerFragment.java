package com.qkhl.fragment;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Camera;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qkhl.activity.AnswerPage;
import com.qkhl.kaixinwa_android.R;
import com.zbar.lib.CameraManager;
import com.zbar.lib.CaptureActivityHandler;
import com.zbar.lib.InactivityTimer;

public class AnswerFragment extends Fragment implements Callback {

	private TextView dkaimen;
	private LinearLayout leftLayout = null;
	private LinearLayout rightLayout = null;
	private LinearLayout feikaimen = null;
	private static RelativeLayout yuantu, saomiao;
	private RelativeLayout mContainer = null;
	private RelativeLayout mCropLayout = null;
	private ImageView kuang;
	private CaptureActivityHandler handler;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private boolean vibrate;
	private boolean hasSurface;

	private int x = 0;
	private int y = 0;
	private int cropWidth = 0;
	private int cropHeight = 0;
	public static SurfaceHolder surfaceHolder;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCropWidth() {
		return cropWidth;
	}

	public void setCropWidth(int cropWidth) {
		this.cropWidth = cropWidth;
	}

	public int getCropHeight() {
		return cropHeight;
	}

	public void setCropHeight(int cropHeight) {
		this.cropHeight = cropHeight;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View firstpagelayout = inflater.inflate(R.layout.ceshi, container,
				false);

		return firstpagelayout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		xiangji();
		feikaimen = (LinearLayout) getActivity().findViewById(R.id.fenkai);
		dkaimen = (TextView) getActivity().findViewById(R.id.kaiemnanniu);
		leftLayout = (LinearLayout) getActivity().findViewById(R.id.left);
		rightLayout = (LinearLayout) getActivity().findViewById(R.id.right);
		yuantu = (RelativeLayout) getActivity().findViewById(R.id.kaimen);
		yuantu.setVisibility(View.VISIBLE);

		kuang = (ImageView) getActivity().findViewById(R.id.capture_scan_line);
		saomiao = (RelativeLayout) getActivity().findViewById(
				R.id.capture_containter);
		dkaimen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				yuantu.setVisibility(View.GONE);
				leftLayout.setVisibility(View.GONE);
				rightLayout.setVisibility(View.GONE);
				

				leftLayout.setAnimation(AnimationUtils.loadAnimation(
						getActivity(), R.anim.translate_left));
				rightLayout.setAnimation(AnimationUtils.loadAnimation(
						getActivity(), R.anim.translate_right));

			}
		});

		// tv1=(TextView) getActivity().findViewById(R.id.tv1);
	}

	public void xiangji() {
		
		CameraManager.init(getActivity().getApplicationContext());
		hasSurface = false;
		inactivityTimer = new InactivityTimer(getActivity());
		mContainer = (RelativeLayout) getActivity().findViewById(
				R.id.capture_containter);
		mCropLayout = (RelativeLayout) getActivity().findViewById(
				R.id.capture_crop_layout);
		ImageView mQrLineView = (ImageView) getActivity().findViewById(
				R.id.capture_scan_line);
		ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
		animation.setRepeatCount(-1);
		animation.setRepeatMode(Animation.RESTART);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(1200);
		mQrLineView.startAnimation(animation);

	}
	public void xiangji_open(){
		
		SurfaceView surfaceView = (SurfaceView) getActivity().findViewById(
				R.id.capture_preview);
		surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		playBeep = true;
		AudioManager audioService = (AudioManager) getActivity()
				.getSystemService(Context.AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	// boolean flag = true;
	// protected void light() {
	//
	// if (flag == true) {
	// flag = false;
	// // 开闪光灯
	// CameraManager.get().openLight();
	// } else {
	// flag = true;
	// // 关闪光灯
	// CameraManager.get().offLight();
	// }
	//
	// }

	public Handler getHandler() {
		return handler;
	}

	@Override
	public void onDestroy() {
		Log.e("tag", "onDestroy");
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	@Override
	public void onPause() {
		Log.e("tag", "onPause");
		yuantu.setVisibility(View.VISIBLE);
		leftLayout.setVisibility(View.VISIBLE);
		rightLayout.setVisibility(View.VISIBLE);
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
		Camera c=new Camera();
		

	}

	@Override
	public void onResume() {
		Log.e("tag", "onResume");
		super.onResume();

		
		
	}

	@Override
	public void onStart() {
		Log.e("tag", "onStart");
		super.onStart();
		yuantu.setVisibility(View.VISIBLE);
		leftLayout.setVisibility(View.VISIBLE);
		rightLayout.setVisibility(View.VISIBLE);
		xiangji_open();
	}

	@Override
	public void onStop() {
		Log.e("tag", "onStop");
		super.onStop();
	}

	public void handleDecode(final String result) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		// Toast.makeText(getApplicationContext(), result+"是这个吗",
		// Toast.LENGTH_SHORT)
		// .show();

		// 连续扫描，不发送此消息扫描一次结束后就不能再次扫描
		// handler.sendEmptyMessage(R.id.restart_preview);
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		// if (barcode == null)
		// {
		// dialog.setIcon(null);
		// }
		// else
		// {
		//
		// Drawable drawable = new BitmapDrawable(barcode);
		// dialog.setIcon(drawable);
		// }
		dialog.setTitle("是否查看内容");
		// dialog.setMessage(result);
		dialog.setNegativeButton("查看", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				 Intent intent = new Intent(getActivity(),AnswerPage.class);
//				Intent intent = new Intent();
//				intent.setAction("android.intent.action.VIEW");
//				Uri content_url = Uri.parse(result
//						+ "?appinfo=9e81037e81254512aa2f5ebba79635d2");
				 intent.putExtra("lianjie",
						 result);
//				intent.setData(content_url);
				startActivity(intent);
				// getActivity().finish();
				// web.setVisibility(View.VISIBLE);
				// surfaceView.setVisibility(View.GONE);
				// viewfinderView.setVisibility(View.GONE);

				

				// intent.setAction("android.intent.action.VIEW");

				// Uri content_url = Uri.parse(obj.getText());
				// Log.e("TAG", content_url+"连接lianjie");
				// WebSettings webSettings = web.getSettings();
				// webSettings.setBuiltInZoomControls(true);
				// web.loadUrl(obj.getText()+"aa");
				// Log.e("TAG", obj.getText()+"aa"+"连接lianjie");
				
				// // intent.setData(content_url);
				// startActivity(intent);
				// finish();
			}
		});
		dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// getActivity().finish();
				dialog.dismiss();
				xiangji_open();
			}
		});
		dialog.create().show();
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getActivity().getSystemService(
					Context.VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		hasSurface = false;

	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);

			Point point = CameraManager.get().getCameraResolution();
			int width = point.y;
			int height = point.x;

			int x = mCropLayout.getLeft() * width / mContainer.getWidth();
			int y = mCropLayout.getTop() * height / mContainer.getHeight();

			int cropWidth = mCropLayout.getWidth() * width
					/ mContainer.getWidth();
			int cropHeight = mCropLayout.getHeight() * height
					/ mContainer.getHeight();

			setX(x);
			setY(y);
			setCropWidth(cropWidth);
			setCropHeight(cropHeight);

		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(AnswerFragment.this);
		}
	}

	private static final float BEEP_VOLUME = 0.50f;

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

}
