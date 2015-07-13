package com.qkhl.activity;

import com.qkhl.kaixinwa_android.R;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class happystoreActivity extends Activity {

	private RelativeLayout rlTopBar;
	private ImageView popbutton;
	private PopupWindow popRight;
	private View layoutRight;
	private ImageView menulistRight;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_happystore);

		rlTopBar = (RelativeLayout) findViewById(R.id.gongjulan);
		popbutton = (ImageView) findViewById(R.id.sandianbutton);

		popbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (popRight != null && popRight.isShowing()) {
					popRight.dismiss();
				} else {

					layoutRight = getLayoutInflater().inflate(R.layout.popwindow,
							null);

					menulistRight = (ImageView) layoutRight
							.findViewById(R.id.popimgger);
					menulistRight.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Toast.makeText(happystoreActivity.this, "请关闭",
									Toast.LENGTH_SHORT).show();
						}
					});

					popRight = new PopupWindow(layoutRight,LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					ColorDrawable cd = new ColorDrawable(-0000);
					popRight.setBackgroundDrawable(cd);
					popRight.setAnimationStyle(R.style.PopupAnimation);
					popRight.update();
					popRight.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
					popRight.setTouchable(true); // 设置popupwindow可点击
					popRight.setOutsideTouchable(true); // 设置popupwindow外部可点击
					popRight.setFocusable(true); // 获取焦
					// 设置popupwindow的位置
					int topBarHeight = rlTopBar.getBottom();
					popRight.showAsDropDown(popbutton, 0,
							(topBarHeight - popbutton.getHeight()) / 2);

					popRight.setTouchInterceptor(new View.OnTouchListener() {

						@Override
						public boolean onTouch(View v, MotionEvent event) {
							// 如果点击了popupwindow的外部，popupwindow也会消失
							if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
								popRight.dismiss();
								return true;
							}
							return false;
						}
					});

				}
			}
		});
	}

}
